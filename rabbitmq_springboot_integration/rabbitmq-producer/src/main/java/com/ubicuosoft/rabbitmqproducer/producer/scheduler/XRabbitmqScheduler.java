package com.ubicuosoft.rabbitmqproducer.producer.scheduler;

import com.ubicuosoft.rabbitmqproducer.producer.client.XRabbitmqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

//@Service
@Slf4j
public class XRabbitmqScheduler {
    @Autowired
    private XRabbitmqClient rabbitmqClient;

    @Scheduled(fixedDelay = 30000)
    public void sweepDirtyQueues(){
        try{
            var dirtyQueues=rabbitmqClient.getAllQueues()
                    .stream()
                    .filter(p->p.isDirty())
                    .collect(Collectors.toList());

            dirtyQueues.forEach(q->log.info("Queue {} has {} unprocessed messages", q.getName(), q.getMessages()));
        }catch (Exception e){
            log.warn("Cannot sweep queue: {}", e.getMessage());
        }




    }
}
