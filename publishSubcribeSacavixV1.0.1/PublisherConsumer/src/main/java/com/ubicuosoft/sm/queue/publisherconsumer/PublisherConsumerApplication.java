package com.ubicuosoft.sm.queue.publisherconsumer;

import com.ubicuosoft.sm.queue.publisherconsumer.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PublisherConsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(PublisherConsumerApplication.class, args);
    }
}
