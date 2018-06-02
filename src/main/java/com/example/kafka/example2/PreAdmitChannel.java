package com.example.kafka.example2;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PreAdmitChannel {

    @Output(value = "mybean")
    MessageChannel output(); // pre-admits
}
