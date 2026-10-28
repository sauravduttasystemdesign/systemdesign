package com.pizzacompany.internal;

import com.pizzacompany.api.Pizza;

final class MargheritaPizza implements Pizza {

    @Override
    public void prepareAndBake() {
        addDough();
        addTomatoSauce();
        addCheese();
        bake();
    }

    private void addDough() {
        System.out.println("Adding proprietary dough");
    }

    private void addTomatoSauce() {
        System.out.println("Adding signature sauce");
    }

    private void addCheese() {
        System.out.println("Adding cheese");
    }

    private void bake() {
        System.out.println("Baking Margherita Pizza");
    }
}
