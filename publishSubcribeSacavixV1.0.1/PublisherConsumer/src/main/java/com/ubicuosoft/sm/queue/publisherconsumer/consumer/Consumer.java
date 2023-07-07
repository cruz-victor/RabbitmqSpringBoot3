package com.ubicuosoft.sm.queue.publisherconsumer.consumer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
    //TODO: Thera are problems about cast Data objetc publisher and Data object Consumer

//    @RabbitListener(queues = {"${ubicuosoft.queue.name}"})
    @RabbitListener(queues = {"readings"})
    //public void receive(@Payload Data message) {
    public void receive(Object message) {
    //public void receive(@Payload String message){
        try{
            //Data data=new Data(10,"fdf");
            //data=(Data) message;
            log.info("Consumer recibed message {}",message);
            //log.info("Consumer recibed message data {}",data);
            makeSlow();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private void makeSlow(){
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
