package com.digital.userprofile.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final String TOPIC = "my-topic";

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public void sendMessage( byte[] message) {
        try
        {
            kafkaTemplate.send(TOPIC,  message);
            log.info("Message sent to kafka topic:{}  ", TOPIC);
        }
        catch (Exception e){
            log.error("Exception in sending message to kafka topic: "+e);
        }

    }
}
