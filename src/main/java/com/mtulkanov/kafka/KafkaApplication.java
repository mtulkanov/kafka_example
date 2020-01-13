package com.mtulkanov.kafka;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@SpringBootApplication
public class KafkaApplication {
    @Autowired
    private Faker faker;

    @Autowired
    private Sender sender;

    private Person generateRandomPerson() {
        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        int age = faker.number().numberBetween(0, 110);
        boolean isMarried = faker.bool().bool();
        return new Person(name, surname, age, isMarried);
    }

    private void run() {
        Flux.interval(Duration.ofSeconds(5))
                .doOnEach(s -> sender.sendPerson(generateRandomPerson()))
                .blockLast();
    }

    public static void main(String[] args) {
        BeanFactory beanFactory = new SpringApplication(KafkaApplication.class).run();
        KafkaApplication kafkaApplication = beanFactory.getBean(KafkaApplication.class);
        kafkaApplication.run();
    }
}
