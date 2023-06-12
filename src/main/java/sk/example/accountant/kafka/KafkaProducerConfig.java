package sk.example.accountant.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import java.util.Map;
@Configuration
public class KafkaProducerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
//        return new KafkaTemplate<>(producerFactory);
//    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        Map<String, Object> producerProperties = kafkaProperties.buildProducerProperties();
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerProperties);
        return new KafkaTemplate<>(producerFactory);
    }
}