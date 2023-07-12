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
public class RetryImageConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    private DlxProcessingErrorHandler dlxProcessingErrorHandler;

    private static final String DLX_EXCHANGE_NAME="x.guideline.dead";

    public RetryImageConsumer(){
        dlxProcessingErrorHandler=new DlxProcessingErrorHandler(DLX_EXCHANGE_NAME);
    }

    @RabbitListener(queues = "q.guideline.image.work")
    public void listener(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            var picture=objectMapper.readValue(message.getBody(), Picture.class);
            if (picture.getSize()>9000){
                throw new IOException("The size of file is long");
            }else{
                channel.basicAck(tag,false);
            }
        }catch (IOException e){
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message,channel,tag);
        }
    }
}
