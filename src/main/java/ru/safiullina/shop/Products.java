package ru.safiullina.shop;

import java.util.ArrayList;
import java.util.List;

import static ru.safiullina.Main.prices;

public class Products {
    protected int id;
    protected String name;

    public Products(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName() + ", " + prices.get(getId()).getPrice() + " " + "руб";
    }
}
