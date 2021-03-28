package com.nrkt.springbootkafkalog.dispatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrkt.springbootkafkalog.model.LogModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.TreeSet;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

@Component
@Log4j2
@RequiredArgsConstructor
public class LogSender {

    @Value("${kafka.topic}")
    private String topicName;

    @Value("${log.folder.path}")
    private String path;

    @Value("${log.file.name}")
    private String logFileName;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Async
    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        log.debug("kafka producer job running");

        File logFile = new File(path.concat(logFileName));

        if (!logFile.exists()){
            log.warn("log file not found ");
            return;
        }

        try {
            try (ReversedLinesFileReader reader = new ReversedLinesFileReader(logFile, StandardCharsets.UTF_8)) {

                Set<LogModel> sentLogs = new TreeSet<>(Comparator.comparing(LogModel::getId));
                Set<LogModel> pendingLogs = new TreeSet<>(Comparator.comparing(LogModel::getId));

                String data;
                while ((data = reader.readLine()) != null) {
                    var logModel = objectMapper.readValue(data, LogModel.class);
                    if (logModel.getKafkaStatus().equals(false)) pendingLogs.add(logModel);
                    else sentLogs.add(logModel);
                }

                if (pendingLogs.isEmpty()) return;

                for (Iterator<LogModel> iterator = pendingLogs.iterator(); iterator.hasNext(); ) {
                    LogModel pendingLog = iterator.next();
                    String toBeSent = StringUtils.replace(pendingLog.toString(), ",\"kafkaStatus\":false", "");

                    String uuid = objectMapper.readTree(toBeSent).path("id").asText();

                    var record = new ProducerRecord<>(topicName, uuid, toBeSent);
                    record.headers().add(KafkaHeaders.MESSAGE_KEY, uuid.getBytes(StandardCharsets.UTF_8));

                    ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(record);

                    sendResult.addCallback(new ListenableFutureCallback<>() {

                        @Override
                        public void onSuccess(SendResult<String, String> sendResult) {
                            log.info("Message Sent: " + sendResult.getProducerRecord().value()
                                    + " for topic: " + sendResult.getProducerRecord().topic()
                                    + " with key: " + sendResult.getProducerRecord().key()
                                    + " partition: " + sendResult.getRecordMetadata().partition()
                                    + " time: " + sendResult.getRecordMetadata().timestamp()
                                    + " offset: " + sendResult.getRecordMetadata().offset()
                            );
                        }

                        @Override
                        public void onFailure(Throwable ex) {
                            log.error(ex.getMessage());
                            throw new KafkaException(ex.getMessage());
                        }
                    });

                    pendingLog.setKafkaStatus(true);
                    sentLogs.add(pendingLog);
                    iterator.remove();
                }

                FileUtils.writeLines(logFile, sentLogs, false);
                if (!pendingLogs.isEmpty()) FileUtils.writeLines(logFile, pendingLogs, true);
            }
        } catch (JsonProcessingException exception) {
            log.warn(exception.getMessage());
        } catch (IOException | KafkaException exception) {
            log.error(exception.getMessage());
        } finally {
            log.debug("kafka producer job completed");
        }
    }
}
