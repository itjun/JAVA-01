package io.itjun.kafka.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerImpl implements KafkaConsumer {

    @KafkaListener(id = "myId1", topics = {"topic1"})
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("----------------");
        System.out.println(record.toString());
        System.out.println("----------------");
    }
}
