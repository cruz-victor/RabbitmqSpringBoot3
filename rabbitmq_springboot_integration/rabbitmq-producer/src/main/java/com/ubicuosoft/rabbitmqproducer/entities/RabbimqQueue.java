package com.ubicuosoft.rabbitmqproducer.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RabbimqQueue {
    private long messages;
    private String name;
    private String message_bytes;

    public boolean isDirty(){
        return this.messages>0;
    }
}
