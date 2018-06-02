package com.example.kafka.example5.util;

import com.example.kafka.example5.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class DataGenerator {

    @Bean
    public CommandLineRunner initMongo(MongoOperations mongo) {
        return (String... args) -> {
            mongo.dropCollection(Command.class);
            mongo.createCollection(Command.class, CollectionOptions.empty().size(100_000).capped());

            mongo.save(Command.builder().name("InitCommand").count(-1).localDate(LocalDate.now()).build());
            log.debug("Generated PreAdmit");

        };
    }


}
