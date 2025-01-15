package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class BankService {
    private BankAccount account = new BankAccount(100.0); // Initial balance

    public double withdraw(double amount) throws InsufficientFundsException {
        if (amount > account.getBalance()) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: $" + account.getBalance());
        }
        account.setBalance(account.getBalance() - amount);
        return account.getBalance();
    }

    public double getBalance() {
        return account.getBalance();
    }
}
