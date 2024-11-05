package ru.safiullina.money;

/**
 * Бонусы можно пополнять
 * Бонусами можно рассчитываться (один к десяти)
 * Бонусы нельзя вывести
 */
public class Bonus extends Wallet implements TopUp{

    public int BONUS_COST = 10;

    public Bonus(int balance, String name) {
        super(balance, name);
    }

    @Override
    public boolean topUp(int amount) {

        // if amount less than 0, return false or Error
        if (amount < 0) {
            return false;
        } else {
            // get current balance
            int curBalance = getBalance();
            // set a new balance as summa current balance and amount
            setBalance(curBalance + amount);
            // if the balance becomes equal summa previous balance and amount,
            // the operation is Ok and it returns true
            return getBalance() == curBalance + amount;
        }

    }

    @Override
    public boolean spend(int amount) {

        // 1 бонус
        amount *= BONUS_COST;

        // get current balance
        int curBalance = getBalance();

        if (amount < 0) {
            // if amount less than 0, return false or Error
            return false;
        } else if (curBalance - amount < 0) {
            // if current balance minus amount less than 0, return false or Error
            return false;
        } else {
            // set a new balance as differance current Balance and amount
            setBalance(curBalance - amount);
            // if the balance becomes equal previous balance minus amount,
            // the operation is Ok and it returns true
            return getBalance() == curBalance - amount;
        }

    }
}
