package com.ubicuosoft.rabbitmqconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class FixedRateConsumer {

    @RabbitListener(queues = "course.fixedrate", concurrency = "3")
    public void listener(String message){
      log.info("Consumer--->i={}",message);
      log.info(Thread.currentThread().getName());
    }
}
