package com.ubicuosoft.rabbitmqproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubicuosoft.rabbitmqproducer.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HumanResourceProducer {
    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private ObjectMapper objectMapper;

    public void send(Employee employee) throws JsonProcessingException {
        var json=objectMapper.writeValueAsString(employee);
        log.info("Producer--->{}",json);
        rabbitTemplate.convertAndSend("x.hr","",json);
    }
}
