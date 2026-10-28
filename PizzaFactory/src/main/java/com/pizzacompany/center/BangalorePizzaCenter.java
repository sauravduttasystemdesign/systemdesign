package com.pizzacompany.center;

import com.pizzacompany.api.Pizza;
import com.pizzacompany.internal.PizzaFactory;

public final class BangalorePizzaCenter {
    public Pizza fulfillOrder(String type) {
        Pizza pizza = PizzaFactory.createPizza(type);
        pizza.prepareAndBake();
        return pizza;
    }

    public static void main(String[] args) {
        BangalorePizzaCenter center = new BangalorePizzaCenter();
        center.fulfillOrder("margherita");
        center.fulfillOrder("farmhouse");
        center.fulfillOrder("nonveg");
    }
}
