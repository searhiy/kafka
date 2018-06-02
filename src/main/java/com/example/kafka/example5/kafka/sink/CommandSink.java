package com.example.kafka.example5.kafka.sink;

import com.example.kafka.example5.Command;
import com.example.kafka.example5.kafka.EventsBindings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class CommandSink {
    private AtomicInteger count = new AtomicInteger(0);

    private final CommandRepository commandRepository;

    public CommandSink(CommandRepository commandRepository) {
        this.commandRepository = commandRepository;
    }

    @StreamListener(EventsBindings.INPUT)
    public void process(Message<Command> message) {
        log.info("received from kafka {}, {}", message.getPayload().toString(), count.addAndGet(1));
        Command savedCommand = commandRepository.save(message.getPayload());
        log.info("saved in mongo {}, {}", savedCommand.toString());
    }
}

@Repository
interface CommandRepository extends MongoRepository<Command, String> {

}
