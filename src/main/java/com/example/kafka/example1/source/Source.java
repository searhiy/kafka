package com.example.kafka.example1.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source {

    String OUTPUT = "events";

    @Output(Source.OUTPUT)
    MessageChannel output();

//    @Input(Source.OUTPUT)
//    MessageChannel input();
}
