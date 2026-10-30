package com.company;

import com.company.logging.Logger;
import com.company.service.OrderService;
import com.company.service.PaymentService;
import com.company.service.UserService;
import com.company.workflow.CheckoutWorkflow;

public final class Demo {
    private Demo() {}

    public static void main(String[] args) throws InterruptedException {
        // Composition root obtains the Singleton once and shares it with services.
        Logger logger = Logger.getInstance();
        CheckoutWorkflow checkout = new CheckoutWorkflow(
                new UserService(logger),
                new OrderService(logger),
                new PaymentService(logger));

        Thread firstCheckout = new Thread(
                () -> checkout.checkout("flow-1", "user-101", "order-501"));
        Thread secondCheckout = new Thread(
                () -> checkout.checkout("flow-2", "user-202", "order-502"));

        firstCheckout.start();
        secondCheckout.start();
        firstCheckout.join();
        secondCheckout.join();
    }
}
