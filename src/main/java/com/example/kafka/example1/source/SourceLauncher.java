package com.example.kafka.example1.source;

import com.example.kafka.example1.Command;
import com.example.kafka.example1.sink.Sink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.messaging.Message;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@SpringBootApplication
@EnableBinding(Source.class)
public class SourceLauncher {
    public static void main(String[] args) {
        SpringApplication.run(SourceLauncher.class, args);
    }

    private AtomicInteger count = new AtomicInteger(0);

    @StreamListener(Source.OUTPUT)
    public void log(Message<Command> message) {
        log.info("received from kafka {}, {}", message.getPayload().toString(), count.addAndGet(1));
    }
}
