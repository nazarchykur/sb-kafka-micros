package com.example.orderservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

//    @Bean
//    public NewTopic orderTopics() {
//        return TopicBuilder.name(topicName)
//                .build();

//        return TopicBuilder.name(topicName)
//                .partitions(2)
//                .replicas(3)
//                .build();
//    }
}
