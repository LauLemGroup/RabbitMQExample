package com.laulem.receiver;

import lombok.Getter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@Getter
public class Receiver {

    @RabbitListener(queues = "spring-boot")
    public void receiveMessage1(String message) {
        System.out.println("Received 1 <" + message + ">");
    }
/*
    @RabbitListener(queues = "spring-boot-topic-exchange")
    public void receiveMessage2(String message) {
        System.out.println("Received 2 <" + message + ">");
    }

    @RabbitListener(queues = "spring-boot-fanout-exchange")
    public void receiveMessageFanout(String message) {
        System.out.println("Received fanout 1 <" + message + ">");
    }

    @RabbitListener(queues = "spring-boot-fanout-exchange")
    public void receiveMessageFanout2(String message) {
        System.out.println("Received fanout 2 <" + message + ">");
    }*/
}
