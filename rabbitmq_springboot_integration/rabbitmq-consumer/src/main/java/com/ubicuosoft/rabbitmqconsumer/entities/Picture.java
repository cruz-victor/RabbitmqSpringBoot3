package com.ubicuosoft.rabbitmqconsumer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    private String name;
    private String type;
    private String source;
    private long size;
}
