package com.mtulkanov.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.mtulkanov.kafka.KafkaConfig.PERSONS;

@Service
@Slf4j
public class Sender {

    @Autowired
    private KafkaTemplate<String, Person> kafkaTemplate;

    public void sendPerson(Person person) {
        var future = kafkaTemplate.send(PERSONS, person);
        future.addCallback(
                result -> log.info(result.toString()),
                ex -> log.error("Sending Person to Kafka failed with exception", ex)
        );
        log.info("Sent Person \"{}\" to Kafka", person);
    }
}
