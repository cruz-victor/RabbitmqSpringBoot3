package com.ubicuosoft.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.ubicuosoft.rabbitmqconsumer.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class RetryAccountingConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    private DlxProcessingErrorHandler dlxProcessingErrorHandler;
    private static final String DLX_NAME_EXCHANGE="x.guideline2.dead";

    public RetryAccountingConsumer(){
        dlxProcessingErrorHandler=new DlxProcessingErrorHandler(DLX_NAME_EXCHANGE);
    }

    @RabbitListener(queues = "q.guideline2.accounting.work")
    public void listener(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            log.info("Consumer Accounting--->");
            log.info("Message object--->{}",new String(message.getBody()));
            var employee=objectMapper.readValue(message.getBody(), Employee.class);
            if (StringUtils.isEmpty(employee.getName())){
                throw new IOException("The name of employe is null");
            }else{
                channel.basicAck(tag,false);
            }
        }catch (Exception e) {
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message,channel,tag);
        }
    }
}
