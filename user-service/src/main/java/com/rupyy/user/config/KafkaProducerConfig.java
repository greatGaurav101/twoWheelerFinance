package com.rupyy.user.config;

import com.rupyy.customer_events.data.CustomerCreatedEvent;
import com.rupyy.user.constants.AppConstants;
import com.rupyy.user.dto.CustomerRequestDTO;
import com.rupyy.user.entity.Customer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, CustomerCreatedEvent> producerFactory(){  //built-in classes from kafa

        Map<String, Object> KafkaProperties = new HashMap<>();
        KafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST);
        KafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        KafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(KafkaProperties);
    }

    //this bean has the details i.e to which kafka url i have to connect as it contains producerFactory
    @Bean
    public KafkaTemplate<String, CustomerCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
