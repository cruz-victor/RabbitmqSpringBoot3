package com.ubicuosoft.rabbitmqproducer.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloRabbimqProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        log.info("Producer--->{}",message);
        rabbitTemplate.convertAndSend("course.hello",message);
    }

}
