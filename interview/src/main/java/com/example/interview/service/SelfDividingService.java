package com.example.interview.service;

import org.springframework.stereotype.Service;

@Service
public class SelfDividingService {
    public boolean isSelfDividing(int number) {
        int original = number;
        while (number > 0) {
            int digit = number % 10;
            if (digit == 0 || original % digit != 0) {
                return false;
            }
            number /= 10;
        }
        return true;
    }
}
