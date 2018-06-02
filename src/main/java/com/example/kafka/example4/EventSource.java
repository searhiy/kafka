package com.example.kafka.example4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class EventSource implements ApplicationRunner {

    private final List<String> commands = Arrays.asList("create", "get", "update", "delete");
    private final List<String> states = Arrays.asList("in-progress", "deleted");

    private final MessageChannel out;

    public EventSource(EventsBidings binding) {
        this.out = binding.eventOut();
    }

    private AtomicInteger count = new AtomicInteger(0);

    public void run(ApplicationArguments args) throws Exception {

            Runnable runnable = () -> {
                Command command = Command.builder()
                        .id(UUID.randomUUID().toString())
                        .count(count.addAndGet(1))
                        .name(random(commands))
                        .state(random(states))
                        .build();
                Message<Command> message = MessageBuilder
                        .withPayload(command)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, command.getName().getBytes())
                        .build();
                try {
                    out.send(message);
                    log.info("sent " + command);
                }
                catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            };
            Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
    private static <T> T random(List<T> ts) {
        return ts.get(new Random().nextInt(ts.size()));
    }
}
