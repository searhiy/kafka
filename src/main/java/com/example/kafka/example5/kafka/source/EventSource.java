package com.example.kafka.example5.kafka.source;

import com.example.kafka.example5.Command;
import com.example.kafka.example5.kafka.EventsBindings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class EventSource implements ApplicationRunner {

    private final List<String> commands = Arrays.asList("create", "get", "update", "delete");
    private final List<String> states = Arrays.asList("in-progress", "deleted");

    private final MessageChannel out;

    public EventSource(EventsBindings binding) {
        this.out = binding.eventOut();
    }

    private AtomicInteger count = new AtomicInteger(0);

    public void run(ApplicationArguments args) throws Exception {

        Stream<UUID> objectIdStream = Stream.generate(UUID::randomUUID);
        List<String> uuids = objectIdStream.limit(100).map(UUID::toString).collect(Collectors.toList());

        Runnable runnable = () -> {

            String uuid = UUID.randomUUID().toString();
            Command command = Command.builder()
                    .uuid(random(uuids))
                    .count(count.addAndGet(1))
                    .name(random(commands))
                    .state(random(states))
                    .localDate(createRandomDate(2012, 2017))
                    .build();
            Message<Command> message = MessageBuilder
                    .withPayload(command)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, uuid.getBytes())
                    .build();
            try {
                out.send(message);
                log.info("sent " + command);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        };
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(runnable, 1, 100, TimeUnit.MILLISECONDS);
    }

    private static <T> T random(List<T> ts) {
        return ts.get(new Random().nextInt(ts.size()));
    }

    private static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
