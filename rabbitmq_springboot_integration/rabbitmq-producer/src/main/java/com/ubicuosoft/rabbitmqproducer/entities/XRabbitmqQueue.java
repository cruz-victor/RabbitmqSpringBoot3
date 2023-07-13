package com.ubicuosoft.rabbitmqproducer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class XRabbitmqQueue {
    private long messages;
    private String name;

    public boolean isDirty(){
        return messages>0;
    }

}
