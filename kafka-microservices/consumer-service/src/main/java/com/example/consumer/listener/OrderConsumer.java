package com.example.consumer.listener;

import com.example.consumer.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    /**
     * Listens for messages on the "orders" topic and logs them.
     *
     * @param order the received order
     */
    @KafkaListener(topics = "orders", groupId = "my-group")
    public void consume(Order order) {
        LOGGER.info(String.format("Received order -> %s", order));
    }
}
