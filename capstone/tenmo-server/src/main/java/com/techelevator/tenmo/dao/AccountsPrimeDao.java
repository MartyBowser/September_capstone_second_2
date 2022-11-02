package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component


public class AccountsPrimeDao implements AccountsDao {

    private final JdbcTemplate jdbcTemplate;

    public AccountsPrimeDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }





}
