package com.nrkt.springbootkafkalog.repository;

import com.nrkt.springbootkafkalog.model.LogModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<LogModel,String> {
}
