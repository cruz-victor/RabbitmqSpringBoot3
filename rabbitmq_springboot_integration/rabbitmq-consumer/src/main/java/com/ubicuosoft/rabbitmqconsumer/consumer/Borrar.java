package com.ubicuosoft.rabbitmqconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Borrar {
    //@RabbitListener(queues = {"q.picture.image","q.picture.vector","q.picture.filter","q.picture.log"})
    @RabbitListener(queues = {"q.picture.image","q.picture.vector"})
    public void listener(Message messageAmqp) {
        String jsonMessage=new String(messageAmqp.getBody());
        log.info("Consumer--->");
        log.info("Routing key ={}",messageAmqp.getMessageProperties().getReceivedRoutingKey());
        log.info("Picture Two - Json message ={}",jsonMessage);
    }
}
