package com.techelevator;

public class SavingsAccount extends BankAccount {

    private final int SERVICECHARGE = 2;

    public SavingsAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public SavingsAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {

        if(getBalance() - amountToWithdraw >= 2){
            super.withdraw(amountToWithdraw);
            if (getBalance() < 150){
                super.withdraw(SERVICECHARGE);
            }
        } return super.getBalance();
    }
}
