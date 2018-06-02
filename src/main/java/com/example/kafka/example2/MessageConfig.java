package com.example.kafka.example2;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;

@IntegrationComponentScan
@EnableBinding(PreAdmitChannel.class)
public class MessageConfig {
}
