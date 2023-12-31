package com.laulem.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConfig {
    public static final String topicExchangeName = "spring-boot-topic-exchange1";
    public static final String fanoutExchangeName = "spring-boot-topic-exchange2";

    private static final String topicQueueName1 = "spring-boot-topic1";
    private static final String topicQueueName2 = "spring-boot-topic2";

    private static final String fanoutQueueName1 = "spring-boot-fanout1";
    private static final String fanoutQueueName2 = "spring-boot-fanout2";

    @Bean("topicQueue1")
    Queue topicQueue1() {
        return new Queue(topicQueueName1, false);
    }

    @Bean("topicQueue2")
    Queue topicQueue2() {
        return new Queue(topicQueueName2, false);
    }

    @Bean("fanoutQueue1")
    Queue fanoutQueue1() {
        return new Queue(fanoutQueueName1, false);
    }

    @Bean("fanoutQueue2")
    Queue fanoutQueue2() {
        return new Queue(fanoutQueueName2, false);
    }

    @Bean("topicExchange")
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean("fanoutExchange")
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

    @Bean
    Binding bindingTopic1(@Qualifier("topicQueue1") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.baz");
    }

    @Bean
    Binding bindingTopic2(@Qualifier("topicQueue2") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.baz");
    }

    @Bean
    Binding bindingFanout1(@Qualifier("fanoutQueue1") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    Binding bindingFanout2(@Qualifier("fanoutQueue2") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }
}
