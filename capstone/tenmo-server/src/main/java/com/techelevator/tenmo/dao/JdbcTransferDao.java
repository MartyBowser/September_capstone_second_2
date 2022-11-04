package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public class JdbcTransferDao implements TransferDao{

    private final JdbcTemplate jdbcTemplate;
    private final JdbcAccountsDao jdbcAccountsDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate, JdbcAccountsDao jdbcAccountsDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcAccountsDao = jdbcAccountsDao;
    }

    public Transfer insertTransfer(Transfer transfer) {
        return null;
    }

    public List<Transfer> getTransfersByUserId(int userId) {
        return null;
    }

    @Override
    public Transfer updateBalances() {
        return null;
    }

    @Override
    public Transfer getTransferByTransferId(int id) {
        return null;
    }

    @Override
    public Transfer getStatusByStatusId(int statusId) {
        return null;
    }

    @Override
    public String getTypeByTypeId(int typeId) {
        return null;
    }

    @Override
    public int getTransfer(int transfer) {
        return 0;
    }


    public Transfer resultToTransfer(int transferId) {
        return null;
    }

    @Override
    public List<Integer> getAllTransferIds() {
        return null;
    }

    public void createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }



    public Transfer transferByTransferId(int transferId) {
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
        return transfer;
    }


    public String sendTransfer(int accountFrom, int accountTo, BigDecimal amount) {
        if(accountFrom == accountTo) {
            return "You can not send money to yourself.";
        }
         if ((amount.compareTo(jdbcAccountsDao.getBalance(accountFrom)) == 1 || amount.compareTo(jdbcAccountsDao.getBalance(accountFrom)) == 0) && amount.compareTo(new BigDecimal(0)) == -1) {

             jdbcAccountsDao.addToBalance(amount, accountTo);
             jdbcAccountsDao.subtractFromBalance(amount, accountFrom);
             Transfer transferToTrack = new Transfer();
             transferToTrack.setAccountFrom(accountFrom);
             transferToTrack.setAccountTo(accountTo);
             transferToTrack.setAmount(amount);
             transferToTrack.setTransferStatusId(2);
             transferToTrack.setTransferTypeId(1);

             createTransfer(transferToTrack);

             return "Your transfer is complete";
         } else {

             return "Transfer failed";
         }

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