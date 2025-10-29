package com.example.producer.service;

import com.example.producer.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final String TOPIC = "orders";
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends an order message to the Kafka topic.
     *
     * @param order the order to be sent
     */
    public void sendMessage(Order order) {
        LOGGER.info(String.format("Producing message -> %s", order));
        this.kafkaTemplate.send(TOPIC, order);
    }
}
