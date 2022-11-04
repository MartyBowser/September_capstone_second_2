package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;

public interface AccountsDao {

    Account getAccountByUserId(int userId);

    BigDecimal getBalanceByAccountId(int account_id);

    BigDecimal getBalance(int userId);

    BigDecimal addToBalance(BigDecimal amountToAdd, int id);

    BigDecimal subtractFromBalance(BigDecimal amountToSubtract, int id);

    BigDecimal getBalanceByUserId(int accountId);

   //Accounts getAccountsUsingUserId(int userId);

    void updateBalances(Transfer transfer);

}
