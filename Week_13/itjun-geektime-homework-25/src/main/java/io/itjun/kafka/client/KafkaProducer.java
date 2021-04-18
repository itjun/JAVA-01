package io.itjun.kafka.client;

public interface KafkaProducer {

    void send(String topic, String message);

}
