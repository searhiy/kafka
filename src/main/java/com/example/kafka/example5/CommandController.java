package com.example.kafka.example5;

import com.example.kafka.example5.kafka.EventsBindings;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.QueryableStoreRegistry;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/manage")
public class CommandController {

    private int count = 1;
    private final MessageChannel out;
    private final QueryableStoreRegistry registry;


    public CommandController(EventsBindings binding, QueryableStoreRegistry registry) {
        this.out = binding.eventOut();
        this.registry = registry;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody Command command) {
        for (int i = 0; i < 7; i++) {
            command.setCount(count++);
            Message<Command> message = MessageBuilder
                    .withPayload(command)
//                    .setHeader(KafkaHeaders.MESSAGE_KEY, command.getName().getBytes())
                    .build();
            out.send(message);
            log.info("sent to kafka {}", command.toString());
        }
    }

    @GetMapping("/counts")
    Map<String, Long> counts() {
        ReadOnlyKeyValueStore<String, Long> store = registry.getQueryableStoreType(EventsBindings.COMMAND_STATUS_MV, QueryableStoreTypes.keyValueStore());

        Map<String, Long> m = new HashMap<>();
        KeyValueIterator<String, Long> iterator = store.all();
        while (iterator.hasNext()) {
            KeyValue<String, Long> next = iterator.next();
            m.put(next.key, next.value);
        }
        return m;
    }

}
