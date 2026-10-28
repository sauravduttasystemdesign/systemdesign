package com.pizzacompany.centres;

import com.pizzacompany.api.Pizza;
import com.pizzacompany.internal.PizzaFactory;

public class BangalorePizzaCentre {

    public void orderPizza(String type) {
        Pizza pizza = PizzaFactory.createPizza(type);
        pizza.prepareAndBake();
    }
}