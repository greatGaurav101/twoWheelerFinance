package com.rupyy.config;

import com.rupyy.twf.constants.AppConstants;
import com.rupyy.twf.entity.Customer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
   ConsumerFactory<String, Customer> consumerFactory(){  //built-in classes from kafa

       Map<String, Object> KafkaProperties = new HashMap<>();
       KafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConstants.KAFKA_HOST);
       KafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
       KafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);

       return new DefaultKafkaProducerFactory<>(KafkaProperties);
   }

   //this bean has the details i.e to which kafka url i have to connect as it contains producerFactory
    @Bean
    public KafkaTemplate<String, Customer> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
