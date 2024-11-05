package ru.safiullina.buyer;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    protected Map<Integer, Integer> pairs = new HashMap<>();


    public void put(Integer key, Integer value) {
        pairs.put(key, value);
    }

    public Map<Integer, Integer> get() {
        return pairs;
    }

}
