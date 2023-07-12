package com.ubicuosoft.rabbitmqconsumer.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubicuosoft.rabbitmqconsumer.entities.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class EmployeeJsonConsumer {
    @Autowired
    ObjectMapper objectMapper;

    @RabbitListener(queues = "course.employee")
    public void listener(String message) throws JsonProcessingException {
        Employee employee=objectMapper.readValue(message,Employee.class);
        log.info("Consumer---> message {}", message);
        log.info("Consumer---> object {}", employee);
    }
}
