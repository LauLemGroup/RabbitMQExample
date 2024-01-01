# RabbitMQExample - LauLem.com
Demo of RabbitMQ usage: Producing/Consuming messages with a Topic Exchange and a Fanout Exchange. Using a Dead Letter Queue (DLQ)

## Getting Started

### Reference Documentation
* RabbitMQ launch : https://www.rabbitmq.com/ / https://spring.io/guides/gs/messaging-rabbitmq/

### RabbitMQ launch
```
#  Start server
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
```

### Terminology
- An **Exchange** is a message routing agent, responsible for receiving messages from producers and routing them to the appropriate queues based on the exchange type and rules defined. Exchanges take a message and route it into zero or more queues. The routing algorithm used depends on the exchange type and rules called bindings.
  - **Types of Exchanges**:
    - **Direct Exchange**: Routes messages to queues based on a message routing key. The message is routed to the queue whose binding key exactly matches the routing key of the message.
    - **Topic Exchange**: Mentioned earlier, it routes messages to queues based on wildcard matches between the routing key and the routing pattern specified in the binding.
    - **Fanout Exchange**: Routes messages to all of the queues that are bound to it, ignoring the routing key. It's ideal for broadcasting messages.
    - **Headers Exchange**: Routes messages based on header values instead of the routing key. It's very flexible and allows for a more complex routing logic.
  - **Bindings**: A binding is a link between a queue and an exchange. Bindings can have additional routing keys or arguments that the exchange uses to make routing decisions.
  - **Durability and Other Properties**: Exchanges can be durable or transient. Durable exchanges survive broker restarts, whereas transient exchanges do not.
- A **Queue** is a buffer that stores messages. Consumers connect to the queue to receive messages. Queues are bound to an exchange and will receive messages from it based on the binding configuration, which can include a routing key or pattern in the case of TopicExchanges.
- A **Routing Key** is a string that the exchange uses to route messages to queues. In a TopicExchange, the routing key is a pattern with words separated by dots (e.g., "user.created", "user.updated"). The routing logic of the exchange uses this key to determine which queues should receive the message.
- A **Dead letter queue (DLQ)** is a service implementation to store messages that the messaging system cannot or should not deliver

### URL
* Cluster (not in this tutorial): https://www.rabbitmq.com/clustering.html
* Authentication, Authorisation, Access Control: https://www.rabbitmq.com/access-control.html
