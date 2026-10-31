# Logger LLD Homework

This example logs a checkout in this order:

```text
User logs in → Order is placed → Payment is made
```

## How it works

- `Logger` is a **Singleton**. Its private constructor prevents services from creating their own logger. `Demo` gets the shared instance and passes it to each service.
- `CheckoutWorkflow` calls `UserService`, `OrderService`, and `PaymentService` in order.
- `Logger.log()` is synchronized so messages from different threads do not print over each other.
- `CheckoutWorkflow.checkout()` is synchronized so one checkout's three messages stay together before another checkout runs.

The demo starts two checkout threads to show this behavior.

## Main classes

```text
src/main/java/com/company/
├── Demo.java
├── logging/Logger.java
├── service/UserService.java
├── service/OrderService.java
├── service/PaymentService.java
└── workflow/CheckoutWorkflow.java
```

## Run

From this directory with Java 17:

```sh
mkdir -p out
javac -d out $(find src/main/java -name '*.java')
java -cp out com.company.Demo
```
