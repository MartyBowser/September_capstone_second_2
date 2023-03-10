package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.JdbcAccountsDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;


@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private JdbcAccountsDao accountsDao;
    private UserDao userDao;

    public AccountController(UserDao dao, JdbcAccountsDao accountsDao){
        this.userDao = dao;
        this.accountsDao = accountsDao;
    }

    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getAccountBalance(Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        //
        return accountsDao.getAccountByUserId(userId).getBalance();
    }
    @RequestMapping(path = "/balance/{id}", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int id) {
        BigDecimal balance = accountsDao.getBalance(id);

        return balance;
    }

}
