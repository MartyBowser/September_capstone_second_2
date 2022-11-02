package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountsDao {

    Account getAccountByUserId(int userId);

    //BigDecimal getBalanceByUserId(int accountId);

   // Accounts getAccountsUsingUserId(int userId);

}
