package com.example.kafka.example5;

import com.example.kafka.example5.kafka.EventsBindings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@Slf4j
@EnableBinding(EventsBindings.class)
@SpringBootApplication
public class Ex5Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Ex5Launcher.class, args);
    }
}
