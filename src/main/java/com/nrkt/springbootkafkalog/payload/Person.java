package com.nrkt.springbootkafkalog.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Person implements Serializable {
    @JsonProperty("name")
    @Schema(description = "Your Name", example = "Nurkut")
    String name;
    @JsonProperty("surname")
    @Schema(description = "Your Surname", example = "Sll")
    String surname;
}
