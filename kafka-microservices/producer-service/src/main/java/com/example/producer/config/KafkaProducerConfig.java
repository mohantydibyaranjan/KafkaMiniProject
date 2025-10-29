package com.example.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaProducerConfig {

    /**
     * Creates a new Kafka topic named "orders".
     *
     * @return a NewTopic object
     */
    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder.name("orders")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
