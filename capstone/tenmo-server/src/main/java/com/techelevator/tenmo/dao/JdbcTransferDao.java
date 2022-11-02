package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(jdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getTransferId(), transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }



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
