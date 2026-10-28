package com.pizzacompany.internal;

import com.pizzacompany.api.Pizza;

final class NonVegPizza implements Pizza {
    @Override
    public void prepareAndBake() {
        addDough();
        addTomatoSauce();
        addCheese();
        addChicken();
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

    private void addChicken() {
        System.out.println("Adding chicken");
    }

    private void bake() {
        System.out.println("Baking Non-Veg Pizza");
    }
}
