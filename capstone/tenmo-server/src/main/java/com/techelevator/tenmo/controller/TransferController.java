package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountsDao;
import com.techelevator.tenmo.dao.JdbcAccountsDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    private AccountsDao accountDao;
    private TransferDao transferDao;
    private UserDao userDao;
    private static final int APPROVED = 1;
    private static final int SEND = 2;



    public TransferController(TransferDao transferDao, AccountsDao accountsDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.accountDao = accountsDao;
        this.userDao = userDao;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer sendTransfer(@Valid @RequestBody Transfer transfer) {
        //transfer.setTransferStatusId(APPROVED);
        //transfer.setTransferTypeId(SEND);
        BigDecimal accountFrom = accountDao.getBalanceByAccountId(transfer.getAccountFrom());
        //Account accountTo = accountDao.getAccountByUserId(transfer.getAccountTo());
        int compare = accountFrom.compareTo(transfer.getAmount());
        Transfer updatedTransfer = null;
        if (compare >= 0) {
            updatedTransfer = transferDao.insertTransfer(transfer);
        }
        if (updatedTransfer != null) {
            accountDao.addToBalance(transfer.getAmount(), transfer.getAccountTo());
            accountDao.subtractFromBalance(transfer.getAmount(), transfer.getAccountTo());

        }
        return updatedTransfer;
    }

    @RequestMapping(path = "/transfer/{id}", method = RequestMethod.GET)
    public Transfer getTransferFromTransferId(@PathVariable int id) {
        Transfer transfer = null;
        transfer = transferDao.getTransferByTransferId(id);
        return transferDao.getTransferByTransferId(id);


    }

    @RequestMapping(path = "/mytransfers", method = RequestMethod.GET)
    public List<Transfer> getTransfersForUser(Principal principal){
        return transferDao.getTransfersByUserId(userDao.findIdByUsername(principal.getName()));


}
    @RequestMapping(path ="/getstatus", method = RequestMethod.POST)
    public Transfer getStatusFromStatusId(@RequestBody @Valid @PathVariable int statusId){
        //string results = ?? return results
        return transferDao.getStatusByStatusId(statusId);
    }

    @RequestMapping(path = "/gettype", method = RequestMethod.GET)
    public String getTypeFromTypeId (@Valid @RequestBody int typeId){
        return transferDao.getTypeByTypeId(typeId);
    }
    @RequestMapping(path ="/transfer", method = RequestMethod.GET)
    public int getTransfer(@PathVariable int transfer){
        transfer = transferDao.getTransfer(transfer);
        return transfer;
    }


}

