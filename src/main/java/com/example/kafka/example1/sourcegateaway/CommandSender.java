package com.example.kafka.example1.sourcegateaway;

import com.example.kafka.example1.Command;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface CommandSender {

    @Gateway(replyChannel = Source.OUTPUT)
    void write(Command pa);

}
