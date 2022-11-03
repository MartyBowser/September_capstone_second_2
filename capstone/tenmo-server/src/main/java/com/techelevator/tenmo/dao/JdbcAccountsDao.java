package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountsDao implements AccountsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccountsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account getAccountByUserId(int userId){
        //Step 1 - declare the return type
        Account account = null;
        //Step 2 - create the sql
        String sql = "SELECT account_id, user_id, balance " +
                "FROM account " +
                "WHERE user_id = ?;";
        //Step 3 - send sql to database
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        //Step 4 - convert to return type

        // Create a method for mapAccount???
        if(results.next()){
            account = mapAccount(results);
        //Step 5 - return

    }

        return account;

        }

    public BigDecimal getBalance(int user_id) {
        String sql = "SELECT balance " +
                "FROM account " +
                "WHERE user_id = ?;";

        SqlRowSet results = null;
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, user_id);

        return balance;

    }

    public BigDecimal addToBalance(BigDecimal amountToAdd, int id) {

        String sql = "UPDATE account " +
                "SET balance = ? " +
                "WHERE user_id = ? RETURNING *;";

        BigDecimal newBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class, amountToAdd.add(getBalance(id)) , id);

        return newBalance;
    }

    public BigDecimal subtractFromBalance(BigDecimal amountToSubtract, int id) {

        String sql = "UPDATE account " +
                "SET balance = ? " +
                "WHERE user_id = ? RETURNING *;";

        BigDecimal newBalance = jdbcTemplate.queryForObject(sql, BigDecimal.class, getBalance(id).subtract(amountToSubtract), id);

        return newBalance;
    }

    @Override
    public BigDecimal getBalanceByUserId(int accountId) {
        return null;
    }

    @Override
    public void updateBalances(Transfer transfer) {

    }


    //public Account updateBalances(BigDecimal balance, int user_id){
       //Account result = account;
        //boolean finished = false;
        //List<>

     //}

    private Account mapAccount(SqlRowSet results) {
        Account a = new Account();

        BigDecimal balance = results.getBigDecimal("balance");
        a.setBalance(balance);

        int userId = results.getInt("user_id");
        a.setUserId(userId);

        int accountId = results.getInt("account_id");
        a.setAccountId(accountId);

        return a;
    }


}


