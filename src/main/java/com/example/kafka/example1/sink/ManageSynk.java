package com.example.kafka.example1.sink;

import com.example.kafka.example1.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@EnableBinding(Sink.class)
public class ManageSynk {

    private AtomicInteger count = new AtomicInteger(0);

    @StreamListener(Sink.INPUT)
    public void log(Message<Command> message) {
        log.info("received from kafka {}, {}", message.getPayload().toString(), count.addAndGet(1));
    }
}
