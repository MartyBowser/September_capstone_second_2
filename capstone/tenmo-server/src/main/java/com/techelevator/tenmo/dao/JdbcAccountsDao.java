package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
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

     public Account updateBalances(BigDecimal balance, int user_id){
        Account result = account;
        boolean finished = false;
        List<>

     }

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


