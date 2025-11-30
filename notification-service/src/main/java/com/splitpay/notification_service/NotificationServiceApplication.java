package com.splitpay.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "payment-created", groupId = "noti-group")
    public void listen(String message){
        System.out.println("NOTIFICATION RECEIVED → " + message);
    }
}
