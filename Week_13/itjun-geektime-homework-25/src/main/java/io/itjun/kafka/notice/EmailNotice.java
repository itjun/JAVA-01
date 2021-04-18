package io.itjun.kafka.notice;

import io.itjun.kafka.client.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotice implements KafkaConsumer {

    @KafkaListener(id = "myId2", topics = {RegisterNotice.registerTopic})
    public void listen(ConsumerRecord<?, ?> record) {
        System.out.println("------------------------------");
        System.out.println("发送欢迎邮件至->" + record.value());
        System.out.println("------------------------------");
    }
}
