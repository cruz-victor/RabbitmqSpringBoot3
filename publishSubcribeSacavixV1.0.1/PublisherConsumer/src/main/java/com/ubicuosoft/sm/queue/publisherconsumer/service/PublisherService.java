package com.ubicuosoft.sm.queue.publisherconsumer.service;

import com.ubicuosoft.sm.queue.publisherconsumer.publisher.Publisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PublisherService {

    @Autowired
    private Publisher publisher;

    public void sendToRabbit(String message){
        Data data=new Data(10,message);
        log.info("PublisherServicce - Data will be send {}",data);
        //this.publisher.send(message);
        this.publisher.send(data);
    }
}
