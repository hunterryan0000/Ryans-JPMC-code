package com.techelevator;

public class CheckingAccount extends BankAccount {

    private final int LOWERLIMIT = -100;
    private final int OVERDRAFTFEE = 10;

    public CheckingAccount(String accountHolderName, String accountNumber) {
        super(accountHolderName, accountNumber);
    }

    public CheckingAccount(String accountHolderName, String accountNumber, int balance) {
        super(accountHolderName, accountNumber, balance);
    }

    @Override
    public int withdraw(int amountToWithdraw) {

        if (getBalance() - amountToWithdraw > LOWERLIMIT){
            super.withdraw(amountToWithdraw);
            if (getBalance() < 0){
                super.withdraw(OVERDRAFTFEE);
            }
        }
        return super.getBalance();
    }
}
