package com.example.producer.service;

import com.example.producer.model.Order;
import com.example.producer.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProducer {

    private static final String TOPIC = "orders";
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final OrderRepository orderRepository;

    public OrderProducer(KafkaTemplate<String, Order> kafkaTemplate, OrderRepository orderRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderRepository = orderRepository;
    }

    /**
     * Saves an order to the database and sends it to the Kafka topic.
     * This method is transactional, ensuring that the database save and
     * the Kafka message send are treated as a single unit of work.
     *
     * @param order the order to be sent
     */
    @Transactional
    public void sendMessage(Order order) {
        // Save the order to the database
        Order savedOrder = orderRepository.save(order);
        LOGGER.info(String.format("Saved order to database -> %s", savedOrder));

        // Send the order to the Kafka topic
        LOGGER.info(String.format("Producing message -> %s", savedOrder));
        this.kafkaTemplate.send(TOPIC, savedOrder);
    }
}
