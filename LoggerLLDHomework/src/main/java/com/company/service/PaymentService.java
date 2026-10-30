package com.company.service;

import com.company.logging.Logger;

public final class PaymentService {
    private final Logger logger;

    public PaymentService(Logger logger) {
        this.logger = logger;
    }

    public void makePayment(String workflowId, String orderId) {
        // Payment authorization and capture would run here.
        logger.log("workflow=" + workflowId + " | PaymentService | Payment completed for order " + orderId);
    }
}
