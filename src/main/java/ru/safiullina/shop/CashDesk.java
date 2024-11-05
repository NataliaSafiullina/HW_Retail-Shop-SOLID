package ru.safiullina.shop;

import ru.safiullina.money.Wallet;

public class CashDesk {

    public static boolean pay(Wallet wallet, int amount) {

        System.out.printf("Списываем с %s сумму %d \n", wallet.getName(), amount);
        return wallet.spend(amount);

    }
}
