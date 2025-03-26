package com.kingdom.client.config;

import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;


public class MqttInboundBuilder {
    private final MqttPahoMessageDrivenChannelAdapter adapter;

    public MqttInboundBuilder(String clientId, MqttPahoClientFactory clientFactory, String... topics) {
        this.adapter = new MqttPahoMessageDrivenChannelAdapter(clientId, clientFactory, topics);
    }

    public MqttInboundBuilder completionTimeout(int completionTimeout) {
        adapter.setCompletionTimeout(completionTimeout);
        return this;
    }

    public MqttInboundBuilder converter(DefaultPahoMessageConverter converter) {
        adapter.setConverter(converter);
        return this;
    }

    public MqttInboundBuilder qos(int qos) {
        adapter.setQos(qos);
        return this;
    }

    public MqttInboundBuilder outputChannel(MessageChannel channel) {
        adapter.setOutputChannel(channel);
        return this;
    }

    public MqttInboundBuilder errorChannel(MessageChannel channel) {
        adapter.setErrorChannel(channel);
        return this;
    }

    public MqttPahoMessageDrivenChannelAdapter build() {
        return adapter;
    }
}