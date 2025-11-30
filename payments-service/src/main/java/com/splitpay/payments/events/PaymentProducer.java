package com.splitpay.payments.events;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentEvent(String event) {
        kafkaTemplate.send("payment-created", event);
        System.out.println("EVENT SENT → " + event);
    }
}
