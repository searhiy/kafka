package com.example.kafka.example3;

//import com.example.kafka.example1.EventsChannel;
//import com.example.kafka.example1.Source;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
//@EnableBinding(value = {Source.class, EventsChannel.class})
public class Launcher1 {

    public static void main(String[] args) {
        SpringApplication.run(Launcher1.class, args);
    }
}
