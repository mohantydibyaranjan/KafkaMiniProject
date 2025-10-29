package com.example.consumer.config;

import com.example.consumer.model.Order;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    /**
     * Creates a Kafka consumer factory with a correctly configured JsonDeserializer.
     * This method ensures that the deserializer ignores type information in message headers
     * and uses the consumer's local Order class, which prevents ClassNotFoundException.
     *
     * @param kafkaProperties The Kafka properties from application.yml.
     * @return A configured ConsumerFactory for Order objects.
     */
    @Bean
    public ConsumerFactory<String, Order> consumerFactory(KafkaProperties kafkaProperties) {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();

        // Configure the JsonDeserializer
        JsonDeserializer<Order> deserializer = new JsonDeserializer<>(Order.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeHeaders(false); // Ignore type headers from producer

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    /**
     * Creates a concurrent Kafka listener container factory.
     * This factory uses the programmatically configured consumerFactory to ensure
     * messages are deserialized correctly.
     *
     * @param consumerFactory The consumer factory to use.
     * @return A configured ConcurrentKafkaListenerContainerFactory.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory(
            ConsumerFactory<String, Order> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Order> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
