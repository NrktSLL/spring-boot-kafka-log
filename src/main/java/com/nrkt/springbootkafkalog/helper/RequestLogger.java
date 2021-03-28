package com.nrkt.springbootkafkalog.helper;

import com.nrkt.springbootkafkalog.model.LogModel;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@UtilityClass
@Log4j2
public class RequestLogger {

    public void info(HttpServletRequest request, Long startTime) {
        Long duration = System.currentTimeMillis() - startTime;

        LogModel information = LogModel.builder()
                .id(UUID.randomUUID().toString())
                .methodType(request.getMethod())
                .currentTime(LocalDateTime.now(ZoneOffset.UTC).withNano(0))
                .duration(duration)
                .kafkaStatus(false)
                .build();

        log.info(information);
    }
}
