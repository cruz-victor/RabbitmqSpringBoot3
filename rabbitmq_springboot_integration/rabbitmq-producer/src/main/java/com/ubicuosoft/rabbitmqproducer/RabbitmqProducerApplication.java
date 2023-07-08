package com.ubicuosoft.rabbitmqproducer;

import com.ubicuosoft.rabbitmqproducer.entities.Employee;
import com.ubicuosoft.rabbitmqproducer.producer.EmployeeJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

//    @Autowired
//    private HelloRabbimqProducer helloRabbimqProducer;

    @Autowired
    private EmployeeJsonProducer employeeJsonProducer;



    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        helloRabbimqProducer.send("Hi Vic222");

        for (int i = 0; i < 5; i++) {
            Employee employee=new Employee(i,"Emp"+i, LocalDate.now());
            employeeJsonProducer.send(employee);
        }

    }
}
