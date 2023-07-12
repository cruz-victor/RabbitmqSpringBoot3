package com.ubicuosoft.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.ubicuosoft.rabbitmqconsumer.entities.Picture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;


//@Service
@Slf4j
public class xRetryImageConsumer {
    private static final String DEAD_EXCHANGE_NAME="x.guideline.dead";

    private DlxProcessingErrorHandler dlxProcessingErrorHandler;

    @Autowired
    private ObjectMapper objectMapper;

    public xRetryImageConsumer(){
        this.dlxProcessingErrorHandler=new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
    }

    @RabbitListener(queues = "q.guideline.image.work")
    public void listerner(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        try {
            var picture=objectMapper.readValue(message.getBody(), Picture.class);
            if (picture.getSize()>9000){
                throw new IOException("Size of image is too large");
            }else{
                log.info("Creating thumbnail and publishing: {}",picture);
                channel.basicAck(deliveryTag,false);
            }
        }catch (IOException e){
            log.warn("Error procesing image:{}",new String(message.getBody()+" : "+e.getMessage()));
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message,channel,deliveryTag);
         }
    }
}
