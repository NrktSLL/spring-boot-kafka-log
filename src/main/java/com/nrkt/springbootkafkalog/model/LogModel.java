package com.nrkt.springbootkafkalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Document(indexName = "kafka_log_demo")
public class LogModel implements Serializable {

    @Id
    @Field
    @EqualsAndHashCode.Include
    @JsonProperty("id")
    String id;
    @JsonProperty("methodType")
    @Field
    String methodType;
    @JsonProperty("duration")
    @Field
    Long duration;
    @JsonProperty("currentTime")
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSS")
    LocalDateTime currentTime;
    @JsonProperty("kafkaStatus")
    @Field(enabled = false)
    Boolean kafkaStatus;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + "\"" + id.concat("\",") +
                "\"methodType\":" + "\"" + methodType.concat("\",") +
                "\"duration\":" + "\"" + duration.toString().concat("\",") +
                "\"currentTime\":" + "\"" + currentTime.toString().concat("\",") +
                "\"kafkaStatus\":" + kafkaStatus +
                "}";
    }
}
