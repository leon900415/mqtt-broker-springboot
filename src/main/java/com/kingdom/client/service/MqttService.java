package com.kingdom.client.service;

public interface MqttService {
    void publish(String topic, String message, int qos, boolean retained);
    void publish(String topic, String message);
    void publish(String message);
}
