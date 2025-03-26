package com.kingdom.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {
    private Broker broker;
    private Publisher publisher;
    private Subscriber subscriber;

    @Data
    public static class Broker {
        private String url;
        private String clientId;
        private String username;
        private String password;
        private int timeout;
        private int keepAliveInterval;
    }

    @Data
    public static class Publisher {
        private String defaultTopic;
        private int defaultQos;
        private boolean defaultRetained;
    }

    @Data
    public static class Subscriber {
        private String[] topics;
        private int qos;
    }
}