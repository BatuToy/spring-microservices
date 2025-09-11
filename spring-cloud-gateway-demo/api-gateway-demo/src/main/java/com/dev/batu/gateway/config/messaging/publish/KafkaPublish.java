package com.dev.batu.gateway.config.messaging.publish;

import java.io.Serializable;
import java.util.List;

public interface KafkaPublish<K extends Serializable, V> {
    void send(K key, V message, String topic, Integer partition);
}
