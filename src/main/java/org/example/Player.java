package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String username;
    private final String password;
    private BigDecimal balance = BigDecimal.ZERO;
    private final List<String> transactionHistory = new ArrayList<>();

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String get_username() {
        return this.username;
    }

    public String get_password() {
        return this.password;
    }

    public BigDecimal get_balance() {
        return balance;
    }

    public List<String> get_transactionHistory() {
        return transactionHistory;
    }


    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            transactionHistory.add("Снятие: " + amount);
            System.out.println("Вы сняли "+ amount + "\nВаш баланс " + this.balance);
        } else {
            System.out.println("Недостаточно средств.");
        }
    }

    public void credit(BigDecimal amount) {
        balance = balance.add(amount);
        this.transactionHistory.add("Кредит: " + amount);
        System.out.println("Пополнение счета на " + amount);
    }
}