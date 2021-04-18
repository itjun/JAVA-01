package io.itjun.kafka.client;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaConsumer {

    void listen(ConsumerRecord<?, ?> record);

}
