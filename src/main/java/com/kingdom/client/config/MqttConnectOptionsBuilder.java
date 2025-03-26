package com.kingdom.client.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class MqttConnectOptionsBuilder {
    private final MqttConnectOptions options;

    public MqttConnectOptionsBuilder() {
        this.options = new MqttConnectOptions();
    }

    public MqttConnectOptionsBuilder serverURIs(String[] uris) {
        options.setServerURIs(uris);
        return this;
    }

    public MqttConnectOptionsBuilder userName(String userName) {
        options.setUserName(userName);
        return this;
    }

    public MqttConnectOptionsBuilder password(char[] password) {
        options.setPassword(password);
        return this;
    }

    public MqttConnectOptionsBuilder cleanSession(boolean cleanSession) {
        options.setCleanSession(cleanSession);
        return this;
    }

    public MqttConnectOptionsBuilder automaticReconnect(boolean automaticReconnect) {
        options.setAutomaticReconnect(automaticReconnect);
        return this;
    }

    public MqttConnectOptionsBuilder keepAliveInterval(int keepAliveInterval) {
        options.setKeepAliveInterval(keepAliveInterval);
        return this;
    }

    public MqttConnectOptionsBuilder connectionTimeout(int connectionTimeout) {
        options.setConnectionTimeout(connectionTimeout);
        return this;
    }

    public MqttConnectOptionsBuilder maxReconnectDelay(int maxReconnectDelay) {
        options.setMaxReconnectDelay(maxReconnectDelay);
        return this;
    }

    public MqttConnectOptions build() {
        return options;
    }
}