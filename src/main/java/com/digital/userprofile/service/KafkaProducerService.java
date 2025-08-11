package com.digital.userprofile.service;

public interface KafkaProducerService {
    void sendMessage(byte[] message);
}
