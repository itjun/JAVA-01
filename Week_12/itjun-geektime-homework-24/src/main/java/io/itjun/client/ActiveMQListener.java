package io.itjun.client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ActiveMQListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(System.currentTimeMillis() + "Get Message -> " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
