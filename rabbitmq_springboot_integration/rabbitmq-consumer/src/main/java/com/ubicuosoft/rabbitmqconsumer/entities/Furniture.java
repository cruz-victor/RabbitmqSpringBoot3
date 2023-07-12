package com.ubicuosoft.rabbitmqconsumer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Furniture {
    private String color;
    private String material;
    private String name;
    private int price;
}
