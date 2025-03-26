package com.kingdom.client.config;

import com.kingdom.client.handle.MqttMessageHandler;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;


@Configuration
@RequiredArgsConstructor
public class MqttConfig {
    private final MqttProperties mqttProperties;
    private final MqttMessageHandler mqttMessageHandler;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        MqttConnectOptions options = new MqttConnectOptionsBuilder()
                .serverURIs(new String[]{mqttProperties.getBroker().getUrl()})
                .userName(mqttProperties.getBroker().getUsername())
                .password(mqttProperties.getBroker().getPassword().toCharArray())
                .cleanSession(true)
                .automaticReconnect(true)
                .keepAliveInterval(mqttProperties.getBroker().getKeepAliveInterval())
                .connectionTimeout(mqttProperties.getBroker().getTimeout())
                .maxReconnectDelay(6000)
                .build();

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageProducer inbound() {
        return new MqttInboundBuilder(
                mqttProperties.getBroker().getClientId() + "_inbound",
                mqttClientFactory(), 
                "+")    // 使用更简单的通配符
                .completionTimeout(5000)
                .converter(new DefaultPahoMessageConverter())
                .qos(mqttProperties.getSubscriber().getQos())    // 明确设置 QoS 级别
                .outputChannel(mqttInputChannel())
                .errorChannel(new DirectChannel())
                .build();
    }

    @Bean
    public MessageHandler outbound() {
        return new MqttOutboundBuilder(
                mqttProperties.getBroker().getClientId(), 
                mqttClientFactory())
                .async(true)
                .defaultQos(mqttProperties.getPublisher().getDefaultQos())
                .defaultRetained(mqttProperties.getPublisher().isDefaultRetained())
                .defaultTopic(mqttProperties.getPublisher().getDefaultTopic())
                .build();
    }

    @Bean
    public IntegrationFlow mqttOutboundFlow() {
        return IntegrationFlow.from(mqttOutboundChannel())
                .handle(outbound())
                .get();
    }

    @Bean
    public IntegrationFlow mqttInboundFlow() {
        return IntegrationFlow.from(mqttInputChannel())
                .handle(mqttMessageHandler)
                .get();
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }
}