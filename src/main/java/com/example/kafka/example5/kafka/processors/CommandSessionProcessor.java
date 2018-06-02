package com.example.kafka.example5.kafka.processors;

import com.example.kafka.example5.Command;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import static com.example.kafka.example5.kafka.EventsBindings.*;

@Slf4j
@Component
public class CommandSessionProcessor {

    @StreamListener
    @SendTo(COMMAND_STATUS_COUNT)
    public KStream<String, Long> process(@Input(INPUT_STREAM) KStream<String, Command> events) {
        log.debug(events.toString());
        return events
//                .filter((key, value) -> value.getDurationSpentOnPage() > 10)
                .map((key, value) -> new KeyValue<>(value.getState(), "0"))
                .groupByKey()
                .count(Materialized.as(COMMAND_STATUS_MV))
                .toStream();
    }
}
