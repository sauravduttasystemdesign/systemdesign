# Pizza Factory

This example uses the **Simple Factory** pattern to create Margherita, Farmhouse, or Non-Veg pizzas. A center asks for a pizza by type; the factory chooses and constructs the matching class. The center works with the common `Pizza` interface and does not need to know the concrete class or its preparation steps.

> In this implementation, “Factory Pattern” means Simple Factory: one class centralizes the creation choice. It is related to, but simpler than, the GoF Factory Method pattern, where subclasses override a creation method.

## How it works

```text
BangalorePizzaCenter
        │ asks for "farmhouse"
        ▼
PizzaFactory.createPizza(type)
        │ returns a Pizza
        ▼
Pizza.prepareAndBake()
        │ dispatched to the concrete implementation
        ▼
FarmhousePizza (its preparation methods are private)
```

The main roles are:

- **Product contract — `Pizza`:** declares the operation centers can call: `prepareAndBake()`.
- **Concrete products — `MargheritaPizza`, `FarmhousePizza`, `NonVegPizza`:** implement the preparation steps. These classes are package-private, so code outside `com.pizzacompany.internal` cannot name or construct them directly.
- **Factory — `PizzaFactory`:** maps the requested string to a concrete pizza and returns it as `Pizza`. It rejects null or unknown types with `IllegalArgumentException`.
- **Client — `BangalorePizzaCenter`:** requests a pizza, then prepares it through the interface. It does not use `new` on any concrete pizza class.

## Example usage

The center's `fulfillOrder` method performs the client side of the flow:

```java
Pizza pizza = PizzaFactory.createPizza("farmhouse");
pizza.prepareAndBake();
```

`BangalorePizzaCenter.main` demonstrates all three supported requests:

```java
center.fulfillOrder("margherita");
center.fulfillOrder("farmhouse");
center.fulfillOrder("nonveg");
```

The concrete pizza type is selected inside the factory. If a new pizza type is added, add its implementation and add a mapping in `createPizza`.

## Package structure

```text
src/main/java/com/pizzacompany/
├── api/
│   └── Pizza.java                 # common product contract
├── center/
│   └── BangalorePizzaCenter.java  # client using the factory
└── internal/
    ├── PizzaFactory.java          # creation logic
    ├── MargheritaPizza.java       # package-private product
    ├── FarmhousePizza.java        # package-private product
    └── NonVegPizza.java           # package-private product
```

## Recipe visibility

Concrete pizza classes cannot be accessed by other Java packages, and their preparation helpers are private. Centers receive only the `Pizza` contract. This is useful encapsulation inside a single application, but Java package visibility is not a security boundary: `PizzaFactory` itself is public so the center can call it, and any code with access to the application can inspect or instrument it. For independent centers where recipes must remain confidential, host the factory and preparation in a protected service and have centers submit orders and receive only a receipt or status. Do not log proprietary preparation details.

## Run

From this directory with a Java 17 JDK:

```sh
mkdir -p out
javac -d out $(find src/main/java -name '*.java')
java -cp out com.pizzacompany.center.BangalorePizzaCenter
```
