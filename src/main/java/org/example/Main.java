package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        WalletService walletService = new WalletService();

        walletService.registerPlayer("admin", "admin");

        String sessionToken = walletService.loginPlayer("admin", "admin");
        if (sessionToken != null) {
            System.out.println("Успешная авторизация.\nТокен сессии: " + sessionToken);

//            walletService.performCredit(sessionToken, 60.0);
//            walletService.performDebit(sessionToken, 50.0);

            List<String> transactionHistory = walletService.getTransactionHistory(sessionToken);
            if (transactionHistory != null) {
                System.out.println("История транзакций: ");
                for (String transaction : transactionHistory) {
                    System.out.println(transaction);
                }
            }

            walletService.logoutPlayer(sessionToken);
            System.out.println("Успешный выход.");
        } else {
            System.out.println("Ошибка авторизации.");
        }
    }
}