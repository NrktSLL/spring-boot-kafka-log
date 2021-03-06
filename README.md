# Spring Boot Kafka Log Example

<p align="center">
<img src="https://github.com/NrktSLL/spring-boot-kafka-log/blob/master/images/spring-boot-kafka-log.png" alt="Spring Boot Kafka Log Example" width="60%" height="60%"/> 
</p>

## How is It Work ?

<p align="center">
<img src="https://github.com/NrktSLL/spring-boot-kafka-log/blob/master/images/spring-boot-kafka-log-example-how.png" alt="Spring Boot Kafka Log Example" width="50%" height="50%"/> 
</p>

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

| Method |Body| Body Optional | Response | Address
| --- | --- | --- | --- |  --- |
| POST | ✓ | ✓ | OK(200) | http://localhost:8080/api/v1/demo
| GET | ✗ | - | OK(200) | http://localhost:8080/api/v1/demo 
| PUT | ✓ | ✓ | OK(200) | http://localhost:8080/api/v1/demo 
| DELETE | ✗ | - | NO CONTENT(204) | http://localhost:8080/api/v1/demo

## Swagger
You can be accessed at this address and relevant methods can be tested
> **Access : http://localhost:8080/api/documentation/**

<img src="https://github.com/NrktSLL/spring-boot-kafka-log/blob/master/images/swagger.png" alt="# Spring Boot Kafka Log Example " width="70%" height="70%"/> 


## Kafdrop
You can see the messages in topic on kafka from this address
> **Access : http://localhost:9000/**

<img src="https://github.com/NrktSLL/spring-boot-kafka-log/blob/master/images/kafdrop.png" alt="# Spring Boot Kafka Log Example " width="70%" height="70%"/> 

## Grafana
For Http Method metrics on Grafana
> **Access : http://localhost:3000/**

<img src="https://github.com/NrktSLL/spring-boot-kafka-log/blob/master/images/grafana.png" alt="# Spring Boot Kafka Example " width="70%" height="70%"/> 

:warning: If dashboard is not loading you can sign-in Grafana and import the in project files **grafana/provisioning/dashboards/Http_Method_Duration.json** file

Grafana Login info:
```
admin
root
```


## Kibana
For dashboard and log details on Kibana
> **Access : http://localhost:5601/**

<img src="https://github.com/NrktSLL/spring-boot-kafka-log/blob/master/images/kibana-dashboard.png" alt="# Spring Boot Kafka Log Example " width="70%" height="70%"/> 

:information_source: You can import the **export.ndjson** file as follows:

<img src="https://github.com/NrktSLL/spring-boot-kafka-log/blob/master/images/kibana-import.png" alt="# Spring Boot Kafka Log Example " width="70%" height="70%"/> 

After that, charts viewable from **Analytics/Dashboard** menu

<br>

CODE:
gAAAAABgUQaKyPft_OLAqsytVHmGJOaQyI6Ddxj8THd6WLbn3o3gxffwNG-BrwlpGWHfBXfEpmmX-RDIZ1ps86_Ho4PRkGN3oReSgLFW7t2eQhTsnn94bS3HWFDH-CLc0od1nG9cp5MqbdkMfSDdovmGc69Wi_15TAZgEsRYETgNUiAAMbu4bIaBvM9J1J00Ni_09ATsvpDdqTZ-EatXAf5eN7ZdulzytA==
