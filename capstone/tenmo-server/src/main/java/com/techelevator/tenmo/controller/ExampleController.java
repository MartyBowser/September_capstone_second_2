package com.techelevator.tenmo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("isAuthenticated()")
@RestController
public class ExampleController {

@RequestMapping(path="/test", method = RequestMethod.GET)
    public String hello(){
    return "hello";

    @RequestMapping(path="/test", method = )
}
}
