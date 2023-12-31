package com.laulem.controller;

import com.laulem.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RabbitMQController {
    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/count/{word}")
    public Long getWordCount(@PathVariable final String word) {
        return 1L;
    }

    @GetMapping("/log")
    public void getWordCount() {
    }

    @PostMapping(value = "/message")
    public void addMessage(@RequestBody final String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "foo.bar.baz", message);
        //rabbitTemplate.convertAndSend(RabbitMQConfig.fanoutExchangeName, "ffffff", message);
    }
}
