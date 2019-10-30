package com.example.demo;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemReposity extends ElasticsearchRepository<Item,Long> {
}
