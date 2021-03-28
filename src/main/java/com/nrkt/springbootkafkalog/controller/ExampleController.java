package com.nrkt.springbootkafkalog.controller;

import com.nrkt.springbootkafkalog.helper.RequestLogger;
import com.nrkt.springbootkafkalog.payload.Person;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/demo",produces = "application/json")
@Tag(name = "helloworld", description = "Hello World Service")
public class ExampleController {

    final Long startTime = System.currentTimeMillis();

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String get(HttpServletRequest httpRequest) {
        RequestLogger.info(httpRequest, startTime);
        return "Hello ";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<Person> post(@RequestBody(required = false) Optional<Person> person, HttpServletRequest httpRequest) {
        RequestLogger.info(httpRequest, startTime);
        return person;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String put(@RequestBody(required = false) Optional<String> name, HttpServletRequest httpRequest) {
        RequestLogger.info(httpRequest, startTime);
        return "Hello " + name;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(HttpServletRequest httpRequest) {
        RequestLogger.info(httpRequest, startTime);
    }
}
