package com.example.kafka.example4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@Slf4j
@EnableBinding(EventsBidings.class)
@SpringBootApplication
public class Ex4Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Ex4Launcher.class, args);
    }
}
