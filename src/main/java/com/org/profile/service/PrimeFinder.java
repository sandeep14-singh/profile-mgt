package com.org.profile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrimeFinder {
    private PrimeChecker primeChecker;

    public PrimeFinder(PrimeChecker primeChecker) {
        this.primeChecker = primeChecker;
    }
    public Integer find(Integer number) {
        Integer numberToCheck = number + 1;
        while (!primeChecker.isPrime(numberToCheck)) {
            numberToCheck++;
        }
        return numberToCheck;
    }
}
