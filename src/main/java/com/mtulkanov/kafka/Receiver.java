package com.mtulkanov.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.mtulkanov.kafka.KafkaConfig.PERSONS;

@Service
@Slf4j
public class Receiver {

    @KafkaListener(topics = PERSONS)
    public void listen(Person person) {
        log.info("Received \"{}\"\n", person);
    }
}
