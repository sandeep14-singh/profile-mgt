package com.org.profile.controller;


import com.org.profile.service.PrimeFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberFinder {

    private PrimeFinder primeFinder;

    @Autowired(required = false)
    public NumberFinder(PrimeFinder primeFinder) {
        this.primeFinder = primeFinder;
    }

    @Autowired(required = false)
    public NumberFinder(PrimeFinder primeFinder, PrimeFinder primeFinder1) {
        this.primeFinder = primeFinder;
    }

    @Autowired(required = false)
    public NumberFinder() {
        System.out.println();
    }

    @RequestMapping("/find")
    Integer find(@RequestParam("after") Integer number) {
        return primeFinder.find(number);
    }
}
