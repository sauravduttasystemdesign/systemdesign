package com.company.service;

import com.company.logging.Logger;

public final class OrderService {
    private final Logger logger;

    public OrderService(Logger logger) {
        this.logger = logger;
    }

    public void placeOrder(String workflowId, String orderId) {
        // Order validation and persistence would run here.
        logger.log("workflow=" + workflowId + " | OrderService | Order " + orderId + " placed");
    }
}
