package com.company.workflow;

import com.company.service.OrderService;
import com.company.service.PaymentService;
import com.company.service.UserService;

/** Facade coordinating a checkout in the required business order. */
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

    /**
     * One shared workflow instance serializes checkouts, keeping each workflow's
     * three log entries contiguous and ordered, even when callers are concurrent.
     */
    public synchronized void checkout(String workflowId, String userId, String orderId) {
        userService.login(workflowId, userId);
        orderService.placeOrder(workflowId, orderId);
        paymentService.makePayment(workflowId, orderId);
    }
}
