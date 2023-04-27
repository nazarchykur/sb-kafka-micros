package com.example.orderservice;


import com.example.orderservice.dto.OrderEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderProducer {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        log.info("Order event => %s", orderEvent.toString());

        // create Message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topicName)
//                .setHeader(KafkaHeaders.KEY, orderEvent.getOrderDto().getOrderId())
                .setHeader("customHeader", "customHeaderValue")
                .build();

        kafkaTemplate.send(message);
    }
}