package com.imavazq.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic orderTopic(){ //NewTopic es clase proporcionada por Apache Kafka
        return TopicBuilder
                .name("order-topic") //si tuvieramos mas topics mejor obtenerlo de archivo config order-service.yml
                .build();
    }
}
