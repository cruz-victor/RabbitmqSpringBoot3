package com.ubicuosoft.rabbitmqconsumer.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int employeeId;
    private String name;
    private LocalDate birthDate;
}
