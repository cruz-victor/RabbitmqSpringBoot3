package com.ubicuosoft.rabbitmqconsumer.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubicuosoft.rabbitmqconsumer.entities.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PictureVectorConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.picture.vector")
    public void listener(String message) throws JsonProcessingException {
        var picture=objectMapper.readValue(message, Picture.class);
        log.info("Consumer Picture Vector--->");
        log.info("Pictor Vector type={}",picture.getType());
        log.info("Picture Vector - Json message ={}",message);
    }
}
