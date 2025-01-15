package com.example.demo;

public class BankAccount {
    private double balance;


    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to withdraw money
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            // Throw InsufficientFundsException if balance is insufficient
            throw new InsufficientFundsException("Insufficient funds. Available balance: $" + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful! New balance: $" + balance);
    }


    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}

