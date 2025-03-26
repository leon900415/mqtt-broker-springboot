package com.kingdom.client.config;

import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

public class MqttOutboundBuilder {
    private final MqttPahoMessageHandler handler;

    public MqttOutboundBuilder(String clientId, MqttPahoClientFactory clientFactory) {
        this.handler = new MqttPahoMessageHandler(clientId, clientFactory);
    }

    public MqttOutboundBuilder async(boolean async) {
        handler.setAsync(async);
        return this;
    }

    public MqttOutboundBuilder defaultQos(int qos) {
        handler.setDefaultQos(qos);
        return this;
    }

    public MqttOutboundBuilder defaultRetained(boolean retained) {
        handler.setDefaultRetained(retained);
        return this;
    }

    public MqttOutboundBuilder defaultTopic(String topic) {
        handler.setDefaultTopic(topic);
        return this;
    }

    public MqttPahoMessageHandler build() {
        return handler;
    }
}