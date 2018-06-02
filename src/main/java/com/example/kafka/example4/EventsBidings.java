package com.example.kafka.example4;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventsBidings {

    String OUTPUT = "eout";

    @Output(EventsBidings.OUTPUT)
    MessageChannel eventOut();

}
