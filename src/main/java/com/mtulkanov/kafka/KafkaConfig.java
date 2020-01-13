package com.mtulkanov.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String PERSONS = "persons";

    @Bean
    public NewTopic persons() {
        return new NewTopic(PERSONS, 1, (short) 1);
    }
}
