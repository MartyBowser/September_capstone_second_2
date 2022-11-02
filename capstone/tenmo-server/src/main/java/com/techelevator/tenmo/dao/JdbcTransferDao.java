package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;



    }
    public Account transferByTransferId(int transferId){
        //Step 1 - declare the return type
        Account account = null;
        //Step 2 - create the sql
        String sql = "SELECT account_id, user_id, balance " +
                "FROM account " +
                "WHERE user_id = ?;";
        //Step 3 - send sql to database
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        //Step 4 - convert to return type

        // Create a method for mapAccount???
        if(results.next()){
            account = mapAccount(results);
            //Step 5 - return

        }
