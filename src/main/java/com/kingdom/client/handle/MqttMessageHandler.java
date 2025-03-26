package com.kingdom.client.handle;


import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(Message message) throws MessagingException {
        String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
        Integer qos = (Integer) message.getHeaders().get(MqttHeaders.RECEIVED_QOS);
        String payload = (String) message.getPayload();

        // Print received message to console
        log.info("Received MQTT message:");
        log.info("Topic: {}", topic);
        log.info("QoS: {}", qos);
        log.info("Payload: {}", payload);

        System.out.println("=== MQTT Message Received ===");
        System.out.println("Topic: " + topic);
        System.out.println("QoS: " + qos);
        System.out.println("Message: " + payload);
        System.out.println("============================");
    }
}