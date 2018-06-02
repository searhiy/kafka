package com.example.kafka.example4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/manage")
public class CommandController {

    private int count = 1;
    private final MessageChannel out;

    public CommandController(EventsBidings binding) {
        this.out = binding.eventOut();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody Command command) {
        for (int i = 0; i < 5; i++) {
            command.setCount(count++);
            Message<Command> message = MessageBuilder
                    .withPayload(command)
//                    .setHeader(KafkaHeaders.MESSAGE_KEY, command.getName().getBytes())
                    .build();
            out.send(message);
            log.info("sent to kafka {}", command.toString());
        }
    }

}
