package com.nrkt.springbootkafkalog.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic}")
    private String topic;

    @Bean
    public NewTopic loggingTopic() {
        return TopicBuilder.name(topic)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
}
