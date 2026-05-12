package com.rupyy.twf.config;

import com.rupyy.twf.constants.AppConstants;
import com.rupyy.twf.entity.Customer;
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
public class KafkaConfig {
    @Bean
   ProducerFactory<String, Customer> producerFactory(){  //built-in classes from kafa

       Map<String, Object> KafkaProperties = new HashMap<>();
       KafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST);
       KafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
       KafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

       return new DefaultKafkaProducerFactory<>(KafkaProperties);
   }

   //this bean has the details i.e to which kafka url i have to connect as it contains producerFactory
    @Bean
    public KafkaTemplate<String, Customer> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
