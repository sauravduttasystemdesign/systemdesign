package com.company.workflow;

import com.company.service.OrderService;
import com.company.service.PaymentService;
import com.company.service.UserService;

/** Logs are in order  */
public final class CheckoutWorkflow {
    private final UserService userService;
    private final OrderService orderService;
    private final PaymentService paymentService;

    public CheckoutWorkflow(
            UserService userService,
            OrderService orderService,
            PaymentService paymentService) {
        this.userService = userService;
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

 
    public synchronized void checkout(String workflowId, String userId, String orderId) {
        userService.login(workflowId, userId);
        orderService.placeOrder(workflowId, orderId);
        paymentService.makePayment(workflowId, orderId);
    }
}
