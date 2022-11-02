package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.JdbcAccountsDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;

@RequestMapping("/accounts")
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

}
