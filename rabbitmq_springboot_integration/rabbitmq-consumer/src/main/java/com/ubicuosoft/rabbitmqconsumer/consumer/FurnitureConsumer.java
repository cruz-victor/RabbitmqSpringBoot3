package com.ubicuosoft.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class FurnitureConsumer {

    @RabbitListener(queues = {"q.promotion.discount","q.promotion.free-delivery"})
    public void listener(Message message){
        String jsonMessage=new String(message.getBody());
        log.info("Consumer--->");
        log.info("jsonMessage {} - queue {}",jsonMessage, message.getMessageProperties().getConsumerQueue());
        //log.info("Message {}",message);
        //log.info("jsonMessage {}",jsonMessage);
        //log.info("Header {}", message.getMessageProperties().getHeaders());
        //log.info(message.getMessageProperties().getConsumerQueue());
    }
}
