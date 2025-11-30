package com.splitpay.payments.controller;

import com.splitpay.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cache")
public class CacheController {

    private final PaymentService paymentService;

    @DeleteMapping("/payments")
    public String clearPaymentsCache() {
        paymentService.clearCache();
        return "Payments cache cleared!";
    }
}
