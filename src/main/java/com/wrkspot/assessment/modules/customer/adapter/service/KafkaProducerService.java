package com.wrkspot.assessment.modules.customer.adapter.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);

    @Value("${spring.kafka.topic}")
    private String topic = "NEW_CUSTOMER";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String key, Object message) {
        logger.info("sending message {}  to topic {} ", message, topic);
        try {
            kafkaTemplate.send(topic, key, message);

        } catch (Exception e) {
            logger.info("error while sending message {}  to topic {} ", message, topic);
        }
    }
}
