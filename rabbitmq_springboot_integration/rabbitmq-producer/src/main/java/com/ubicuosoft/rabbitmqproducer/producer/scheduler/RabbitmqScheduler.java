package com.ubicuosoft.rabbitmqproducer.producer.scheduler;

import com.ubicuosoft.rabbitmqproducer.producer.client.RabbitmqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class RabbitmqScheduler {
    @Autowired
    private RabbitmqClient rabbitmqClient;

    @Scheduled(fixedRate = 30000)
    public void sweepDirtyQueues(){
        try
        {
            var dirtyQueues= rabbitmqClient.getAllQueues()
                    .stream()
                    .filter(q->q.isDirty())
                    .collect(Collectors.toList());

            dirtyQueues.forEach(q->log.info("Queue {} has {} unprocessed messages. Message bytes: {}",q.getName(), q.getMessages(), q.getMessage_bytes()));
        }catch (Exception e){
            log.warn("Cannot sweep queue:{}", e.getMessage());
        }


    }
}
