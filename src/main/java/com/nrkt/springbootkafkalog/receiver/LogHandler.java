package com.nrkt.springbootkafkalog.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrkt.springbootkafkalog.model.LogModel;
import com.nrkt.springbootkafkalog.repository.LogRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Log4j2
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Component
public class LogHandler {

    LogRepository logRepository;

    ObjectMapper objectMapper;

    @KafkaListener(topics = "${kafka.topic}")
    public void listener(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            log.info(record.topic() + "topic : " + (record.value()));
            LogModel logMessage = objectMapper.readValue(record.value(), LogModel.class);
            logRepository.save(logMessage);
        } catch (JsonProcessingException exception) {
            log.warn(exception.getMessage());
        } catch (KafkaException exception) {
            log.error(exception.getMessage());
        } finally {
            ack.acknowledge();
        }
    }
}
