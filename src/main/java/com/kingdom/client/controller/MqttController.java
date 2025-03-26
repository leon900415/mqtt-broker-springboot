package com.kingdom.client.controller;

import com.kingdom.client.service.MqttService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/mqtt")
@RequiredArgsConstructor
public class MqttController {

    private final MqttService mqttService;

    @PostMapping("/publish")
    public ResponseEntity<String> publish(
            @RequestParam(value = "topic", required = false) String topic,
            @RequestParam(value = "qos", required = false, defaultValue = "1") int qos,
            @RequestParam(value = "retained", required = false, defaultValue = "false") boolean retained,
            @RequestBody String message) {
        if (topic != null) {
            mqttService.publish(topic, message, qos, retained);
        } else {
            mqttService.publish(message);
        }

        return ResponseEntity.ok("Message published successfully");
    }
}