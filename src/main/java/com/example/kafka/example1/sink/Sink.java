package com.example.kafka.example1.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface Sink {

    String INPUT = "events";

    @Input(Sink.INPUT)
    MessageChannel input();
}
