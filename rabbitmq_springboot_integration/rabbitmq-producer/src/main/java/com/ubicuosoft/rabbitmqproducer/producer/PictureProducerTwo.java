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
public class PictureProducerTwo {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public void send(Picture picture) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(picture);
        //TODO: implement routing key ---------
        String routingKeyPicture=getRoutingkey(picture);
        log.info("Routing key ={}",routingKeyPicture);
        //------------------------------------
        rabbitTemplate.convertAndSend("x.picture2",routingKeyPicture,json);
        log.info("Producer --->");
        log.info("Json object ={}",json);
    }

    private String getRoutingkey(Picture picture) {
        //format routing key = source.size.type
        StringBuilder sb=new StringBuilder();
        sb.append(picture.getSource());
        sb.append(".");
        if (picture.getSize()>1000){
            sb.append("large");
        }else{
            sb.append("small");
        }
        sb.append(".");
        sb.append(picture.getType());
        return sb.toString();
    }
}
