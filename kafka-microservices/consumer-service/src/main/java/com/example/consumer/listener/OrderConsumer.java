package com.example.consumer.listener;

import com.example.consumer.model.Order;
import com.example.consumer.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    private final OrderRepository orderRepository;

    public OrderConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Listens for messages on the "orders" topic, saves the received order
     * to the database, and logs the information.
     *
     * @param order the received order
     */
    @KafkaListener(topics = "orders", groupId = "my-group")
    public void consume(Order order) {
        LOGGER.info(String.format("Received order -> %s", order));

        // Save the received order to the database
        Order savedOrder = orderRepository.save(order);
        LOGGER.info(String.format("Saved consumed order to database -> %s", savedOrder));
    }
}
