# Logger Service LLD Homework

A small Java design for logging checkout activities from `UserService`, `OrderService`, and `PaymentService` in this business order:

```text
User logged in → Order placed → Payment completed
```

## Design patterns

### Singleton — `Logger`

`Logger` has a private constructor, so a service cannot instantiate it with `new Logger()`. The composition root (`Demo`) calls `Logger.getInstance()` and passes the shared instance to each service. The current implementation uses an eagerly initialized `static final` instance. Java class initialization is thread-safe, so `getInstance()` does not need to be synchronized.

This is the eager initialization form of the Singleton pattern. It creates the logger when the `Logger` class is initialized, rather than waiting for the first `getInstance()` call.

### Facade / workflow coordinator — `CheckoutWorkflow`

`CheckoutWorkflow` provides one operation, `checkout`, that coordinates the three services in the required order. The services focus on their own activity and emit a log entry when they complete it.

`checkout` is synchronized. The demo shares one `CheckoutWorkflow` instance between threads, so one checkout finishes its User, Order, and Payment steps before the other checkout starts. This keeps each workflow's entries contiguous while preserving the required order.

### Constructor injection

The services receive a `Logger` through their constructors. They neither call its constructor nor create their own logger instances. `Demo` owns the wiring and shares the Singleton.

## Synchronization choices

- `Logger.getInstance()` relies on Java's thread-safe class initialization for the eager Singleton.
- `Logger.log(...)` is synchronized so two threads cannot interleave the characters of separate log messages.
- `CheckoutWorkflow.checkout(...)` is synchronized to serialize whole checkout flows and keep each flow's log entries together.
- Service methods are not synchronized: they contain no shared mutable state in this example. Synchronizing them individually would not enforce the overall User → Order → Payment order.

The workflow lock serializes all checkouts made through the same workflow object. If the system needs parallel checkout processing, remove that global serialization and instead persist events with a workflow ID and sequence/stage, then have a log writer order events per workflow. A synchronized logger alone guarantees atomic writes, but cannot infer business order from independently arriving events.

## Package layout

```text
src/main/java/com/company/
├── Demo.java
├── logging/
│   └── Logger.java
├── service/
│   ├── UserService.java
│   ├── OrderService.java
│   └── PaymentService.java
└── workflow/
    └── CheckoutWorkflow.java
```

## Run

From this directory with a Java 17 JDK:

```sh
mkdir -p out
javac -d out $(find src/main/java -name '*.java')
java -cp out com.company.Demo
```

The demo starts two checkout threads. Each workflow appears as login, order, payment, and the synchronized checkout method keeps the two workflows' messages from interleaving. Log lines do not currently have sequence numbers.
