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
public class xRetryVectorConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    private static final String DEAD_EXCHANGE_NAME="x.guideline.dead";
    private DlxProcessingErrorHandler dlxProcessingErrorHandler;

    public xRetryVectorConsumer(){
        dlxProcessingErrorHandler=new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
    }

    @RabbitListener(queues = "q.guideline.vector.work")
    public void listener(Message message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            var picture=objectMapper.readValue(message.getBody(), Picture.class);
            if (picture.getSize()>9000){
                throw new IOException("--->The picture is long");
            }else{
                log.info("--->Creating thumbnail and publishing: {}", picture);
                channel.basicAck(tag,false);
            }
        }catch (IOException e){
            log.warn("--->Error processing image: {}", new String(message.getBody()));
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message,channel,tag);
        }
    }
}
