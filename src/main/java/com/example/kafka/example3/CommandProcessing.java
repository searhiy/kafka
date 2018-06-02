package com.example.kafka.example3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

@Slf4j
//@EnableBinding(value = {Source.class, EventsChannel.class})
public class CommandProcessing {

//    @StreamListener(Source.OUTPUT)
//    public void log(Message<Command> message){
//        log.debug(message.toString());
//    }
}
