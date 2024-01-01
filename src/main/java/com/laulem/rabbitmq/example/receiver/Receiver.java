package com.laulem.rabbitmq.example.receiver;

import lombok.Getter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Receiver {

    @RabbitListener(queues = "spring-boot-topic1")
    public void receiveMessage1(String message) {
        System.out.println("Received topic 1 <" + message + ">");
    }

    @RabbitListener(queues = "spring-boot-topic2")
    public void receiveMessage2(String message) {
        System.out.println("Received topic 2 <" + message + ">");
    }

    @RabbitListener(queues = "spring-boot-fanout1")
    public void receiveMessageFanout1(String message) {
        System.out.println("Received fanout 1 <" + message + ">");
    }

    @RabbitListener(queues = "spring-boot-fanout2")
    public void receiveMessageFanout2(String message) {
        System.out.println("Received fanout 2 <" + message + ">");
    }
}
