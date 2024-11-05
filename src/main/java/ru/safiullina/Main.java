package ru.safiullina;

import ru.safiullina.buyer.Purchase;
import ru.safiullina.money.Bonus;
import ru.safiullina.money.Card;
import ru.safiullina.money.Wallet;
import ru.safiullina.shop.CashDesk;
import ru.safiullina.shop.Prices;
import ru.safiullina.shop.Products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static List<Products> products = new ArrayList<>();
    public static List<Prices> prices = new ArrayList<>();
    public static Card card = new Card(0, "MIR");
    public static Bonus bonus = new Bonus(0, "Oranges");

    public static void main(String[] args) {

        // Создаем объект пользовательского интерфейса
        ConsoleUI ui = new ConsoleUI();

        // Загружаем продукты
        addProducts(products);
        // Загружаем цены
        addPrices(prices);
        // Создадим представление Справочник: продукт и цена
        List<String> viewProducts = new ArrayList<>();
        makeProductsView(viewProducts, products, prices);

        // Создание кошелька
        List<Wallet> wallet = Arrays.asList(card, bonus);
        card.topUp(150) ; // пополним
        card.withdraw(50); // выведем
        bonus.topUp(1000); // пополним


        boolean doCycle = true;
        while (doCycle) {
            // Создаем (только в первый раз) и показываем меню
            ui.showItems(Menu.getInstance(), "ГЛАВНОЕ МЕНЮ");
            // Предлагаем пользователю выбрать пункт (в данном случае меню)
            int option = ui.enterInt("Введите номер пункта: ");
            switch (option) {
                case 0:
                    doCycle = false;
                    break;
                case 1:
                    ui.showItems(products.stream().map(Products::toString).collect(Collectors.toList()),
                            "КАТАЛОГ");
                    ui.waitForEnter();
                    break;
                case 2:
                    while (true) {
                        ui.showItems(viewProducts,
                                "ВЫБОР ТОВАРОВ (введите отрицательное значение, чтобы выйти)");
                        int productId = ui.enterInt("Ведите номер товара: ");
                        if (productId < 0) break;
                        int productQuantity = ui.enterInt("Ведите количество товара: ");
                        Purchase.putInShoppingCart(productId, productQuantity);
                    }
                    break;
                case 3:
                    ui.showItems(Purchase.getShoppingCart(products, prices),
                            "КОРЗИНА");
                    ui.waitForEnter();
                    break;
                case 4:
                    ui.showItems(wallet.stream().map(Wallet::getName).collect(Collectors.toList()),
                            "ОПЛАТА");
                    int wayPay = ui.enterInt("Выберете способ оплаты: ");
                    if (CashDesk.pay(wallet.get(wayPay), Purchase.sumShoppingCart(prices))) {
                        System.out.println("Оплачено успешно");
                        Purchase.deleteShoppingCart();
                    } else {
                        System.out.println("Оплата отклонена");
                    }
                    break;
                case 5:
                    for (Wallet item : wallet) {
                        System.out.printf("Есть %s, баланс = %d \n", item.getName(), item.getBalance());
                    }
                    break;

            }

        }

    }


    // Вот такая База данных
    private static void makeProductsView(List<String> viewProducts, List<Products> products, List<Prices> prices) {
        for (Products product : products) {
            viewProducts.add(product.getName() + ", " + prices.get(product.getId()).getPrice());
        }
    }

    // Цены на товары, ID товара равен индексу его цены
    // В реальной БД у одного товара может быть несколько цен,
    // в разных регионах и в разных периодах времени
    private static void addPrices(List<Prices> prices) {
        // Порядковый номер цены - это IP товара в массиве товаров
        prices.add(new Prices(20));
        prices.add(new Prices(30));
        prices.add(new Prices(40));
        prices.add(new Prices(50));
        prices.add(new Prices(25));
        prices.add(new Prices(15));
    }

    // Товары: наименование и id
    private static void addProducts(List<Products> products) {
        // Будем считать что ID товара - это его порядковый номер в массиве
        // Из всех параметров у товара будет только имя
        products.add(new Products("Хлеб", 0));
        products.add(new Products("Молоко", 1));
        products.add(new Products("Сыр", 2));
        products.add(new Products("Мясо", 3));
        products.add(new Products("Яблоко", 4));
        products.add(new Products("Огурец", 5));
    }
}