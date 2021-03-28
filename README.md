# Spring Boot Kafka Log Example
>**This project is an example application that records the logs written to the log file to Elasticsearch via Kafka.**


## How is It Work ?

## Prerequisites

*  Docker
*  Docker Compose

## Installation
```
docker-compose up -d
```

## Used Dependencies
* Spring Boot Web
* Spring Boot log4j2
* Spring Kafka
* Spring Boot Data Elasticsearch
* Spring Boot Actuator
* Sringdoc OpenApi
* Spring Boot Actuator
* Spring Boot Configuration Processor
* Apache Commons IO  
* Lombok

## HTTP Methods Detail

| Method |Body| Body Optional | Response | Address | Produce | 
| --- | --- | --- | --- |  --- | --- |
| POST | ✓ | ✓ | OK(200) | http://localhost:8080/api/v1/demo | application/json
| GET | ✗ | - | OK(200) | http://localhost:8080/api/v1/demo | application/json
| PUT | ✓ | ✓ | OK(200) | http://localhost:8080/api/v1/demo | application/json
| DELETE | ✗ | - | NO CONTENT(204) | http://localhost:8080/api/v1/demo | application/json

## Swagger
You can be accessed at this address and relevant methods can be tested;
> **Access : http://localhost:8080/api/documentation/**

##Kafdrop
You can see the messages in topic on kafka from this address;
> **Access : http://localhost:9000/**

##Kibana
For dashboard and log details;
> **Access : http://localhost:5601/**

<br>

CODE:
gAAAAABgUQaKyPft_OLAqsytVHmGJOaQyI6Ddxj8THd6WLbn3o3gxffwNG-BrwlpGWHfBXfEpmmX-RDIZ1ps86_Ho4PRkGN3oReSgLFW7t2eQhTsnn94bS3HWFDH-CLc0od1nG9cp5MqbdkMfSDdovmGc69Wi_15TAZgEsRYETgNUiAAMbu4bIaBvM9J1J00Ni_09ATsvpDdqTZ-EatXAf5eN7ZdulzytA==