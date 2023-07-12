package com.ubicuosoft.rabbitmqconsumer.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Service
@Slf4j
public class PictureTwoConsumer {

    @RabbitListener(queues = {"q.picture.image","q.picture.vector","q.picture.filter","q.picture.log"})
    public void listener(Message message){
        String jsonMessage=new String(message.getBody());
        log.info("Consumer--->");
        log.info("MessageAmqp {}",message);
        log.info("jsonMessage {}",jsonMessage);
        log.info("routing key {}",message.getMessageProperties().getReceivedRoutingKey());
    }
}
