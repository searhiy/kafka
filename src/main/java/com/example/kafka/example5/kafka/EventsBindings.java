package com.example.kafka.example5.kafka;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface EventsBindings {

    String OUTPUT = "eout";
    String INPUT = "ein";
    String INPUT_STREAM = "eins";

    String COMMAND_STATUS_COUNT = "cscout";
    String COMMAND_STATUS_MV = "csmv";

    @Output(EventsBindings.OUTPUT)
    MessageChannel eventOut();
    @Input(EventsBindings.INPUT)
    MessageChannel eventIn();

    @Input(EventsBindings.INPUT_STREAM)
    KStream<String, Long> eventInStream();

    @Input(EventsBindings.COMMAND_STATUS_COUNT)
    KStream<String, Long> commandStatusCount();
}
