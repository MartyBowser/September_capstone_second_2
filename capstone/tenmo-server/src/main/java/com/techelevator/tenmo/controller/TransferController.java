package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountsDao;
import com.techelevator.tenmo.dao.JdbcAccountsDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@PreAuthorize("isAuthenticated()")
@RestController
public class TransferController {

    private JdbcAccountsDao accountDao;
    private TransferDao transferDao;
    private UserDao userDao;
    private static final int APPROVED = 1;
    private static final int SEND = 2;


    // This method below is not needed - Autowire TransferDao and UserDao instead
    public TransferController(AccountsDao accountsDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.transferDao = transferDao;
    }



    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public Transfer sendTransfer(@Valid @RequestBody @PathVariable Transfer transfer) {
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

