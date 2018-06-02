package com.example.kafka.example1.sink;

import com.example.kafka.example1.source.Source;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;

//@IntegrationComponentScan
@SpringBootApplication
//@EnableBinding(value = {Source.class, EventsChannel.class})
//@EnableBinding(Source.class)
public class SynkLauncher {
    public static void main(String[] args) {
        SpringApplication.run(SynkLauncher.class, args);
    }
}
