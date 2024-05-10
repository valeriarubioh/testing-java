package org.example.testing1.payments;

public interface PaymentGateway {
    PaymentResponse requestPayment(PaymentRequest request);
}
