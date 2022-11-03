package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountsDao;
import com.techelevator.tenmo.dao.JdbcAccountsDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping ("/transfer")
@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    private JdbcAccountsDao accountDao;
    private TransferDao transferDao;
    private UserDao userDao;
    private static final int APPROVED = 1;
    private static final int SEND = 2;

    public TransferController(AccountsDao accountsDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Transfer sendTransfer(@Valid @RequestBody Transfer transfer) {
        transfer.setTransferStatusId(APPROVED);
        transfer.setTransferTypeId(SEND);
        Account accountFrom = accountDao.getAccountByUserId(transfer.getAccountFrom());
        Account accountTo = accountDao.getAccountByUserId(transfer.getAccountTo());
        int compare = accountTo.getBalance().compareTo(transfer.getAmount());
        Transfer updatedTransfer = null;
        if (compare == APPROVED || compare == SEND) {
            updatedTransfer = transferDao.insertTransfer(transfer);
        }
        if (updatedTransfer != null) {
            accountDao.updateBalances(updatedTransfer);

        }
        return updatedTransfer;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Transfer getTransferFromTransferId(@PathVariable int id) {
        Transfer transfer = null;
        transfer = transferDao.getTransferByTransferId(id);
        return transfer;


    }

}

