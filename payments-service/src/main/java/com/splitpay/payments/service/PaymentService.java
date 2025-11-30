package com.splitpay.payments.service;

import com.splitpay.payments.entity.Payment;
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

    public Payment createPayment(Payment payment) {
        Payment saved = paymentRepository.save(payment);
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
