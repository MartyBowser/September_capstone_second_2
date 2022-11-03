package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;

public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Transfer insertTransfer(Transfer transfer) {
        return null;
    }

    public Transfer getTransferByUserId(int transferId) {
        return null;
    }

    @Override
    public Transfer resultToTransfer(int transferId) {
        return null;
    }

    public void createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }



    public int transferByTransferId(int transferId) {
        //Step 1 - declare the return type

        //Step 2 - create the sql
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount ?" +
                "FROM transfer ?" +
                "WHERE transfer_id = ?;";
        //Step 3 - send sql to database
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        //Step 4 - convert to return type
        Transfer transfer = null;

        // Create a method for mapAccount???
        if (results.next()) {
            transfer = mapResultToTransfer(results);
            //Step 5 - return

        }
        return transferId;
    }

    private Transfer mapResultToTransfer(SqlRowSet results) {
        Transfer t = new Transfer();

        int transfer_id = results.getInt("transfer_id");
        t.setTransferId(transfer_id);

        int transfer_type_id = results.getInt("transfer_type_id");
        t.setTransferTypeId(transfer_type_id);

        int transfer_status_id = results.getInt("transfer_status_id");
        t.setTransferStatusId(transfer_status_id);

        int account_from = results.getInt("account_from");
        t.setAccountFrom(account_from);

        int account_to = results.getInt("account_to");
        t.setAccountTo(account_to);

        BigDecimal amount = results.getBigDecimal("amount");
        t.setAmount(amount);

        return t;
    }
}