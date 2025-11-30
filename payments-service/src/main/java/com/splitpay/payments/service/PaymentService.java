package com.splitpay.payments.service;

import com.splitpay.payments.entity.Payment;
import com.splitpay.payments.events.PaymentProducer;
import com.splitpay.payments.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;


import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;

    public Payment createPayment(Payment payment) {
        Payment saved = paymentRepository.save(payment);

        //Kafka Event
        paymentProducer.sendPaymentEvent(
                "New payment: " + saved.getPayer() + " paid " + saved.getPayee() + " amount " + saved.getAmount()
        );

        clearCache();
        return saved;
    }

    @Cacheable("payments")
    public List<Payment> getAllPayments() {
        System.out.println("Fetching from Oracle DB...");
        return paymentRepository.findAll();
    }

    @CacheEvict(value = "payments", allEntries = true)
    public void clearCache() {
        System.out.println("Cache cleared");
    }
}
