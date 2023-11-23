package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WalletService {
    private Map<String, Player> players;
    private Map<String, String> sessionTokens;

    public WalletService() {
        this.players = new HashMap<>();
        this.sessionTokens = new HashMap<>();
    }

    public void registerPlayer(String username, String password) {
        Player player = new Player(username, password);
        this.players.put(username, player);
    }

    public String loginPlayer(String username, String password) {
        Player player = this.players.get(username);
        if (player != null && player.get_password().equals(password)) {
            String sessionToken = generateSessionToken();
            this.sessionTokens.put(sessionToken, username);
            return sessionToken;
        } else {
            return null;
        }
    }

    public void logoutPlayer(String sessionToken) {
        this.sessionTokens.remove(sessionToken);
    }

    public Player getPlayer(String sessionToken) {
        String username = this.sessionTokens.get(sessionToken);
        if (username != null) {
            return this.players.get(username);
        }
        return null;
    }

    public void performDebit(String sessionToken, BigDecimal amount) {
        Player player = getPlayer(sessionToken);
        if (player != null) {
            player.debit(amount);
        }
    }

    public void performCredit(String sessionToken, BigDecimal amount) {
        Player player = getPlayer(sessionToken);
        if (player != null) {
            player.credit(amount);
        }
    }

    public List<String> getTransactionHistory(String sessionToken) {
        Player player = getPlayer(sessionToken);
        if (player != null) {
            return player.get_transactionHistory();
        }
        return null;
    }

    private String generateSessionToken() {
        // В настоящем приложении, нужно сделать механизм генерации токена
        return "SESSION_TOKEN_" + System.currentTimeMillis();
    }
}