package com.ubicuosoft.sm.queue.publisherconsumer.controller;

import com.ubicuosoft.sm.queue.publisherconsumer.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("test")
@Slf4j
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public void testSendMessage(){
        String message="Hi Victor from Proyect Maven Java:"+ ThreadLocalRandom.current().nextInt();
        log.info("Publisher send message {}", message);
        this.publisherService.sendToRabbit(message);
    }
}
