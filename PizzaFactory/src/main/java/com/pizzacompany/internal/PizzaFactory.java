package com.pizzacompany.internal;

import com.pizzacompany.api.Pizza;

public final class PizzaFactory {

    private PizzaFactory() {}

    public static Pizza createPizza(String type) {

        if (type == null) {
            throw new IllegalArgumentException("Pizza type must not be null");
        }

        return switch (type.toLowerCase()) {
            case "margherita" -> new MargheritaPizza();
            case "farmhouse"  -> new FarmhousePizza();
            case "nonveg"     -> new NonVegPizza();
            default -> throw new IllegalArgumentException(
                    "Unknown pizza type: " + type);
        };
    }
}
