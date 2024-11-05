package ru.safiullina;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<String> items = new ArrayList<>();
    private static Menu menu;

    private Menu addItem(String item){
        items.add(item);
        return this;
    }

    public static List<String> getInstance(){
        // Меню создаем только один раз, при последующих вызовах, возвращаем созданное меню
        if (menu == null) menu = new Menu()
                .addItem("Выход")
                .addItem("Каталог товаров")
                .addItem("Выбрать товары")
                .addItem("Показать корзину")
                .addItem("Оплатить счет")
                .addItem("Узнать баланс");

        return menu.items;
    }

}
