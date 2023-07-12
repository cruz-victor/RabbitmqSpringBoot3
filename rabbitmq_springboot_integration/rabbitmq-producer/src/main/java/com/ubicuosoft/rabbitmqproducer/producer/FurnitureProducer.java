package com.ubicuosoft.rabbitmqproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubicuosoft.rabbitmqproducer.entities.Furniture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

//@Service
@Slf4j
public class FurnitureProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void send(Furniture furniture) throws JsonProcessingException {
        var jsonFurniture=objectMapper.writeValueAsString(furniture);

        var messageProperties=new MessageProperties();
        messageProperties.setHeader("color",furniture.getColor());
        messageProperties.setHeader("material",furniture.getMaterial());

        var message=new Message(jsonFurniture.getBytes(StandardCharsets.UTF_8), messageProperties);
        log.info("JsonFurniture Bytes {}",jsonFurniture.getBytes(StandardCharsets.UTF_8));

        rabbitTemplate.send("x.promotion","", message);

        log.info("Producer--->");
        log.info("JsonFurniture {}", jsonFurniture);
        log.info("MessageProperties {}",messageProperties);
        log.info("Message {}",message);
    }
}
