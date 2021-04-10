package io.itjun.client;

import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;

public class TopicClient {

    private ActiveMessageQueueClient queueClient;

    public TopicClient(ActiveMessageQueueClient queueClient) {
        this.queueClient = queueClient;
    }

    public void createTopic(String topicName) {
        try {
            queueClient.getSession().createTopic(topicName);
        } catch (JMSException e) {
            System.out.println("创建主题 -> " + topicName + "失败!");
            e.printStackTrace();
        }
    }

    public void getMessage(String topicName) {
        try {
            MessageConsumer messageConsumer = queueClient.getSession().createConsumer(new ActiveMQTopic(topicName));
            messageConsumer.setMessageListener(new ActiveMQListener());
        } catch (JMSException e) {
            System.out.println("创建主题 -> " + topicName + "失败!");
            e.printStackTrace();
        }
    }

    public void putMessage(String topicName, String message) {
        try {
            TextMessage textMessage = queueClient.getSession().createTextMessage(message);
            queueClient.getSession().createProducer(new ActiveMQTopic(topicName)).send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
