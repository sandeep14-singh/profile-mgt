package com.org.profile.service;

import org.springframework.stereotype.Component;

@Component
public class PrimeChecker {

    public boolean isPrime(Integer number) {
        int co = 0;
        for (int i = 1; i<= number; i++) {
            if (number % i == 0) {
                co ++;
            }
        }
        if (co == 2) {
            return true;
        } else {
            return false;
        }
    }
}
