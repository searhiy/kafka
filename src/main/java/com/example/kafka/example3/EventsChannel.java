package com.example.kafka.example3;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface EventsChannel {

    String Events = "events";

    @Input(EventsChannel.Events)
    SubscribableChannel input();

//    @Output(EventsChannel.Events)
//    SubscribableChannel output();
}
