package com.ubicuosoft.rabbitmqproducer;

import com.ubicuosoft.rabbitmqproducer.entities.Employee;
import com.ubicuosoft.rabbitmqproducer.entities.Picture;
import com.ubicuosoft.rabbitmqproducer.producer.EmployeeJsonProducer;
import com.ubicuosoft.rabbitmqproducer.producer.HumanResourceProducer;
import com.ubicuosoft.rabbitmqproducer.producer.PictureProducer;
import com.ubicuosoft.rabbitmqproducer.producer.PictureProducerTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
//@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

//    @Autowired
//    private HelloRabbimqProducer helloRabbimqProducer;

//    @Autowired
//    private EmployeeJsonProducer employeeJsonProducer;

//    @Autowired
//    private HumanResourceProducer humanResourceProducer;

//    @Autowired
//    private PictureProducer pictureProducer;
    @Autowired
    private PictureProducerTwo pictureProducerTwo;

    final private List<String> IMAGE_TYPES=List.of("png","jpg","svg");
    final private List<String> IMAGE_SOURCES=List.of("web","mobile");

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        helloRabbimqProducer.send("Hi Vic222");

//        for (int i = 0; i < 20; i++) {
//            Employee employee = new Employee(i, "Emp" + i, LocalDate.now());
//            humanResourceProducer.send(employee);
//        }

        for (int i = 0; i < 10; i++) {
            Picture picture=new Picture();
            picture.setName("Picture"+i);
            picture.setType(IMAGE_TYPES.get(i % IMAGE_TYPES.size()));
            picture.setSource(IMAGE_SOURCES.get(i % IMAGE_SOURCES.size()));
            picture.setSize(ThreadLocalRandom.current().nextInt(1,4000));

            pictureProducerTwo.send(picture);
        }
    }
}
