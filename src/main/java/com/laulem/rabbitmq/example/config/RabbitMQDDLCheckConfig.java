package com.laulem.rabbitmq.example.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQDDLCheckConfig {
    public static final String QUEUE_MESSAGES = "baeldung-messages-queue5";
    public static final String EXCHANGE_MESSAGES = "baeldung-messages-exchange5";
    public static final String DLX_EXCHANGE_MESSAGES = QUEUE_MESSAGES + ".dlx";
    public static final String QUEUE_MESSAGES_DLQ = QUEUE_MESSAGES + ".dlq";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE_MESSAGES)
                .build();
    }

    @Bean
    FanoutExchange deadLetterExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange(DLX_EXCHANGE_MESSAGES);
        return fanoutExchange;
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
    }

    @Bean
    DirectExchange messagesExchange() {
        DirectExchange directExchange = new DirectExchange(EXCHANGE_MESSAGES);
        return directExchange;
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_MESSAGES);
    }

    @RabbitListener(queues = RabbitMQDDLCheckConfig.QUEUE_MESSAGES)
    public void receiveMessage(Message message) throws Exception {
        throw new Exception("TEST");
    }

    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessages(Message failedMessage) {
        System.out.println("-->" + failedMessage.toString());
        Integer retriesCnt = (Integer) failedMessage.getMessageProperties()
                .getHeaders().get("HEADER_X_RETRIES_COUNT");
        if (retriesCnt == null) retriesCnt = 1;
        if (retriesCnt > 3) {
            System.out.println("Discarding message");
            return;
        }
        System.out.println("Retrying message for the {} "+ retriesCnt + " time");
        failedMessage.getMessageProperties()
                .getHeaders().put("HEADER_X_RETRIES_COUNT", ++retriesCnt);
        rabbitTemplate.send(EXCHANGE_MESSAGES,
                failedMessage.getMessageProperties().getReceivedRoutingKey(), failedMessage);
    }
}
