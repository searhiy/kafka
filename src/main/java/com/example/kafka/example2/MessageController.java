package com.example.kafka.example2;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mybean")
public class MessageController {

    private final PreAdmitWriter preAdmitWriter;

    public MessageController(PreAdmitWriter preAdmitWriter) {
        this.preAdmitWriter = preAdmitWriter;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void write(@RequestBody MyBean bean) {
        this.preAdmitWriter.write(bean);
        System.out.println("sent to kafka");
    }
}
