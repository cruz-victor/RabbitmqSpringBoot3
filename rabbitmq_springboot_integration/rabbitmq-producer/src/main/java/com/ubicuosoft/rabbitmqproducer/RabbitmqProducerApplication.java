package com.ubicuosoft.rabbitmqproducer;

import com.ubicuosoft.rabbitmqproducer.entities.Employee;
import com.ubicuosoft.rabbitmqproducer.entities.Furniture;
import com.ubicuosoft.rabbitmqproducer.entities.Picture;
import com.ubicuosoft.rabbitmqproducer.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class RabbitmqProducerApplication implements CommandLineRunner {

//    @Autowired
//    private HelloRabbimqProducer helloRabbimqProducer;

//    @Autowired
//    private EmployeeJsonProducer employeeJsonProducer;

//    @Autowired
//    private HumanResourceProducer humanResourceProducer;

//    @Autowired
//    private PictureProducer pictureProducer;

//    @Autowired
//    private PictureProducerTwo pictureProducerTwo;

//    @Autowired
//    private MyPictureProducer myPictureProducer;

//    @Autowired
//    private RetryPictureProducer retryPictureProducer;

//    @Autowired
//    private RetryEmployeeProducer retryEmployeeProducer;

//    final private List<String> IMAGE_TYPES=List.of("png","jpg","svg");
//    final private List<String> IMAGE_SOURCES=List.of("web","mobile");

//    @Autowired
//    private FurnitureProducer furnitureProducer;

//    final private List<String> COLOR=List.of("white","red","green");
//    final private List<String> MATERIALS=List.of("wood","plastic","steel");

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        helloRabbimqProducer.send("Hi Vic222");

//        for (int i = 0; i < 5; i++) {
//            Employee employee = new Employee(i, null, LocalDate.now());
//            retryEmployeeProducer.send(employee);
//        }

//        for (int i = 0; i < 10; i++) {
//            Picture picture=new Picture();
//            picture.setName("Picture"+i);
//            picture.setType(IMAGE_TYPES.get(i % IMAGE_TYPES.size()));
//            picture.setSource(IMAGE_SOURCES.get(i % IMAGE_SOURCES.size()));
//            picture.setSize(ThreadLocalRandom.current().nextInt(9000,10000));
//
//            retryPictureProducer.send(picture);
//        }

//        for (int i = 0; i < 5; i++) {
//            Furniture furniture=new Furniture();
//            furniture.setName("Furniture"+i);
//            furniture.setColor(COLOR.get(i % COLOR.size()));
//            furniture.setMaterial(MATERIALS.get(i % MATERIALS.size()));
//            furniture.setPrice(i);
//
//            furnitureProducer.send(furniture);
//        }
    }
}
