package com.ubicuosoft.rabbitmqproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubicuosoft.rabbitmqproducer.entities.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class RetryPictureProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(Picture picture) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(picture);
        rabbitTemplate.convertAndSend("x.guideline.work",picture.getType(), json);
        log.info("Consumer--->");
        log.info("jsonPicture {}"+json);
    }
}
