package com.ubicuosoft.rabbitmqproducer.producer.client;

import com.ubicuosoft.rabbitmqproducer.entities.XRabbitmqQueue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;


import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.List;

//@Service
public class XRabbitmqClient {
    public List<XRabbitmqQueue> getAllQueues(){
        var webClient= WebClient.create("http://localhost:15672/api/queues");

        var basicAuthHeader=createBasicAuthHeader("guest","guest");

        return webClient.get().header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<XRabbitmqQueue>>() {
                }).block(Duration.ofSeconds(10));
    }

    private String createBasicAuthHeader(String userName, String password) {
            var authString=userName+":"+password;
            return "Basic "+ Base64.getEncoder().encodeToString(authString.getBytes(StandardCharsets.UTF_8));
    }
}
