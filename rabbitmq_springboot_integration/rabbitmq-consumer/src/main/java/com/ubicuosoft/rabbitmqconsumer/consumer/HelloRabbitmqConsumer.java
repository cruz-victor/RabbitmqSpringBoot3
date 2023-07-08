package com.ubicuosoft.rabbitmqconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloRabbitmqConsumer {

    @RabbitListener(queues = "course.hello")
    public void listener(String message){
        log.info("Consumer--->{}",message);
    }
}
