package com.ubicuosoft.rabbitmqproducer.producer.client;

import com.ubicuosoft.rabbitmqproducer.entities.RabbimqQueue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

//@Service
public class RabbitmqClient {

    public List<RabbimqQueue> getAllQueues(){
        var webClient= WebClient.create("http://localhost:15672/api/queues");

        var headerValues=createBasicAutHeader("guest","guest");
        return webClient
                .get()
                .header(HttpHeaders.AUTHORIZATION, headerValues)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RabbimqQueue>>() {
                })
                .block(Duration.ofSeconds(10));
    }

    public String createBasicAutHeader(String user, String password){
        var concatUserPassword=user+":"+password;
        return "Basic "+ Base64.getEncoder().encodeToString(concatUserPassword.getBytes(StandardCharsets.UTF_8));
    }
}
