package com.example.kafka.example1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {
    private int count;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Command command = (Command) o;
//        return Objects.equals(name, command.name);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(name);
//    }
//
//    @Override
//    public String toString() {
//        return "Command{" +
//                "name='" + name + '\'' +
//                '}';
//    }

    private String name;
}
