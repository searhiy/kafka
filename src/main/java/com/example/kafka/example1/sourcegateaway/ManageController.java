package com.example.kafka.example1.sourcegateaway;

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

    private int count = 1;
    private final CommandSender commandSender;

    public ManageController(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody Command command) {
        for (int i = 0; i < 5; i++) {
            command.setCount(count++);
            commandSender.write(command);
            log.info("sent to kafka {}", command.toString());
        }
        System.out.println("sent to kafka");
    }

}
