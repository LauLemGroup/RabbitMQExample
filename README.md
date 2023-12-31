# RabbitMQTest - LauLem.com

## Getting Started

### Reference Documentation
* RabbitMQ launch : https://www.rabbitmq.com/ / https://spring.io/guides/gs/messaging-rabbitmq/

### RabbitMQ launch
```
#  Start server
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management
```

### Tools
```
# Describe (stat) a topic
bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092

# Resize partition
./bin/kafka-topics.sh --alter --bootstrap-server localhost:9092 --topic quickstart-events --partitions 20

# Consumer with long as value and print key
bin/kafka-console-consumer.sh --topic output-topic3 --from-beginning --bootstrap-server localhost:9092 --value-deserializer=org.apache.kafka.common.serialization.LongDeserializer -property print.key=true
```

### Terminology
- A **TopicExchange** is a category of messages.
- A **Topic** is a subdivision of a topic allowing for parallel processing.
- A **Queue** is a means of directing messages to specific partitions to ensure order or consistency for certain types of data.
- A **Routing Key** identifies a consumer group in Kafka. All consumers sharing the same Group ID are considered part of the same consumer group. Used for offset management and distributing messages among consumers. Kafka ensures that each message from a partition is consumed by only one consumer in the group.
- A **consumer ID** is a unique identifier for each consumer within a group. It is usually assigned automatically by Kafka and can be based on information such as the consumer's IP address and port. Used to individually identify consumers within a group, particularly useful during consumer rebalancing and for tracking performance or issues.

### Kafka tools
- **Apache Kafka Core**: This is the basic Kafka system, including the Kafka broker server, message producers and consumers, and topic managers.
- **Kafka Streams**: A data streaming framework for building real-time data processing applications. Kafka Streams enables the transformation, aggregation, and processing of data continuously directly from Kafka topics.
- **Kafka Connect** (not in this project): A tool for connecting Kafka with various data sources and destinations (such as databases, file systems, etc.). Kafka Connect is used for importing data into Kafka and exporting data from Kafka to other systems.

### URL
- Kafka: https://kafka.apache.org/
- Tutorials: https://www.baeldung.com/apache-kafka / https://codingharbour.com/apache-kafka/what-is-a-consumer-group-in-kafka/
