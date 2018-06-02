package com.example.kafka.example3;

import com.example.kafka.example1.Command;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface CommandSender {

    @Gateway(replyChannel = "output")
    void write(Command pa);

}
