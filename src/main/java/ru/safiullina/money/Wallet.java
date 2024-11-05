package ru.safiullina.money;

public abstract class Wallet {
    private int balance;
    private String name;

    public Wallet(int balance, String name) {
        this.balance = balance;
        this.name = name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public abstract boolean spend(int amount);

}
