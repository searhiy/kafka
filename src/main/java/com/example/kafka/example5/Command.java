package com.example.kafka.example5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Command {

    @Id
    private String id;
    private String uuid;
    private int count;
    private String name, state;
    private LocalDate localDate;
}
