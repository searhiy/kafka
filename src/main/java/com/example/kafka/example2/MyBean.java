package com.example.kafka.example2;

import java.util.Objects;

public class MyBean {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyBean myBean = (MyBean) o;
        return Objects.equals(name, myBean.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                '}';
    }

    private String name;
}
