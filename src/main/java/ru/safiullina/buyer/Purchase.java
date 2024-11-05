package ru.safiullina.buyer;

import ru.safiullina.shop.Prices;
import ru.safiullina.shop.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Purchase {

    private static ShoppingCart shoppingCart;

    public static void putInShoppingCart(int productId, int productQuantity) {

        if (shoppingCart == null) shoppingCart = new ShoppingCart();
        shoppingCart.put(productId, productQuantity);

    }

    public static List<String> getShoppingCart(List<Products> products, List<Prices> prices) {
        List<String> list = new ArrayList<>();
        int total = 0;
        if (shoppingCart != null) {
            for (Map.Entry<Integer, Integer> pair : shoppingCart.get().entrySet()) {
                int quantity = pair.getValue();
                int price = prices.get(pair.getKey()).getPrice();
                int sum = quantity * price;
                total += sum;
                list.add("Товар " + products.get(pair.getKey()).getName() +
                        ", количество = " + quantity +
                        ", цена = " + price +
                        ", cумма = " + sum);
            }
        }
        else {
            System.out.println("Корзина пуста");
        }
        list.add("Итого: " + total);

        return list;
    }

    public static int sumShoppingCart(List<Prices> prices) {
        int total = 0;
        if (shoppingCart != null) {
            for (Map.Entry<Integer, Integer> pair : shoppingCart.get().entrySet()) {
                total += pair.getValue() * prices.get(pair.getKey()).getPrice();
            }
        }

        return total;
    }

    public static void deleteShoppingCart(){
        shoppingCart = null;
    }

}
