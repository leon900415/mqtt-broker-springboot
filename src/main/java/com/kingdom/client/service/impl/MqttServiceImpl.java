package com.kingdom.client.service.impl;

import com.kingdom.client.service.MqttService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MqttServiceImpl implements MqttService {

    private final MessageChannel mqttOutboundChannel;

    @Value("${mqtt.publisher.default-topic}")
    private String defaultTopic;

    @Value("${mqtt.publisher.default-qos}")
    private int defaultQos;

    @Value("${mqtt.publisher.default-retained}")
    private boolean defaultRetained;

    @Override
    public void publish(String topic, String message, int qos, boolean retained) {
        Message mqttMessage = MessageBuilder
                .withPayload(message)
                .setHeader(MqttHeaders.TOPIC, topic)
                .setHeader(MqttHeaders.QOS, qos)
                .setHeader(MqttHeaders.RETAINED, retained)
                .build();

        mqttOutboundChannel.send(mqttMessage);
    }

    @Override
    public void publish(String topic, String message) {
        publish(topic, message, defaultQos, defaultRetained);
    }

    @Override
    public void publish(String message) {
        publish(defaultTopic, message, defaultQos, defaultRetained);
    }
}
