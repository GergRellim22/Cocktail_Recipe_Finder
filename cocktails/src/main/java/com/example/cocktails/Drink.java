package com.example.cocktails;

public class Drink {

    private String ingredient1;
    private String drinkName;

    public Drink(String ingredient1, String drinkName) {
        this.ingredient1 = ingredient1;
        this.drinkName = drinkName;
    }

    public String getIngredient1() {
        return ingredient1;
    }

    public String getDrinkName() {
        return drinkName;
    }
}
