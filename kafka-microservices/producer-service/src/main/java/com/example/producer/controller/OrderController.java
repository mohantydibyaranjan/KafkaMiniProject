package com.example.producer.controller;

import com.example.producer.model.Order;
import com.example.producer.service.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    /**
     * Endpoint to receive an order and send it to Kafka.
     *
     * @param order the order to be sent
     * @return a confirmation message
     */
    @PostMapping("/orders")
    public String sendOrder(@RequestBody Order order) {
        orderProducer.sendMessage(order);
        return "Order sent successfully!";
    }
}
