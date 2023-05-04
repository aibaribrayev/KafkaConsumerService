package com.example.kafkaconsumerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.kafkaconsumerservice")
@EnableJpaRepositories("com.example.kafkaconsumerservice")
public class KafkaConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerServiceApplication.class, args);
    }

}
