package com.ubicuosoft.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.ubicuosoft.rabbitmqconsumer.entities.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;


//@Service
@Slf4j
public class MyPictureConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.mypicture.image")
    //public void listener(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    public void listener(String message) throws IOException, InterruptedException {
        Thread.sleep(5000);
        var picture=objectMapper.readValue(message, Picture.class);
        if (picture.getSize()>9000){
            //throw new IllegalArgumentException("An error occurred -->"+picture.getSize());
            throw new AmqpRejectAndDontRequeueException("An error occurred -->"+picture.getSize());
//            channel.basicReject(tag,false);
//            return;
        }
        log.info("Consumer--->");
        log.info("message {}", message);
        log.info("picture {}",picture);
        //channel.basicAck(tag,false);
    }
}
