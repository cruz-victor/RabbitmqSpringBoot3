package com.ubicuosoft.rabbitmqproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubicuosoft.rabbitmqproducer.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class RetryEmployeeProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(Employee employee) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(employee);
        rabbitTemplate.convertAndSend("x.guideline2.work","", json);
        log.info("Producer--->");
        log.info("Json employee :{}",json);
    }
}
