package com.example.kafka.example1.source;

import com.example.kafka.example1.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/manage")
public class ManageController {

    private final Source source;

    private int count = 0;

    public ManageController(Source source) {
        this.source = source;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody Command command) {
        for (int i = 0; i < 5; i++) {
            command.setCount(count++);
            boolean send = this.source.output().send(MessageBuilder.withPayload(command).build(), 10);
            log.info("sent {} to kafka {}", send, command.toString());
        }
    }

}
