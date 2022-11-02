package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;
    private int userId;
    private int accountId;

    public BigDecimal getBalance() {
        return balance;
    }
    public int getUserId() {
        return userId;
    }
    public int getAccountId() {
        return accountId;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

}
