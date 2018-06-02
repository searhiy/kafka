package com.example.kafka.example2;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface PreAdmitWriter {

    @Gateway(replyChannel = "mybean")
    void write(MyBean pa);

}
