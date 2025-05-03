package bankapp.cis254_partner_lab5;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * Partner_Lab5：Design a BankAccount class with GUI
 *
 * @author Z Wang
 * @since 4/27/2025
 * The BankAccount class:
 *      Declare an instance variable to hold the account number.
 *      Declare an instance variable to hold the balance in the account.
 *      Declare an instance variable to accumulate the transactions in the account.
 *      Use a StringBuilder variable.
 *      Add two constructors: a default constructor that initializes the balance with zero and an overloaded constructor that receives an amount to initialize the balance.
 *      Add methods to deposit, withdraw, return the balance and return a statement with all transactions.
 *      Make sure amount to be deposited or withdrawn is not negative and you have enough funds.
 *      Use the Calendar class to record the day and time of each transaction.
 */
public class BankAccount {

    //create a Random object
    Random rand = new Random();


    //constructor
    /**
     * Default constructor
     *  Create a BankAcount object with 0 balance
     */
    public BankAccount()
    {
        this.balance = 0;//set balance to zero
        this.statement = new StringBuilder(String.format("%s\t\t\tCreating Account\t\t\tAccount number %d\t\t\t\tCurrent balance: %.2f\t\t\tAccount Created\n",getCurrentTime(),this.accountNumber,this.balance));//update statement
    }

    /**
     * overloaded constructor
     *  Create a Bank accountant object with the balance passed in param
     * @param balance is an int variable
     */
    public BankAccount(double balance)
    {
        this.balance = balance;//set the balance to param value
        this.statement = new StringBuilder(String.format("%s\t\t\tCreating Account\t\t\t\tAccount number %d\t\t\tCurrent balance: %.2f\t\t\tAccount Created\n",getCurrentTime(),this.accountNumber,this.balance));//update statement
    }




    //variable declaration

    private final int accountNumber = Math.abs(rand.nextInt());//generate a random accountNumber

    private double balance;//balance

    private final StringBuilder statement;//statement


    //gets and sets

    //time
    /**
     * This method will get current time
     * @return a Date object
     */
    private Date getCurrentTime()
    {
        return Calendar.getInstance().getTime();
    }

    //accountNumber
    /**
     * This method will get the Account Number
     * @return the accountNumber
     */
    public int getAccountNumber()
    {
        return this.accountNumber;
    }


    //balance
    /**
     * This method will get the balance
     * @return the balance
     */
    public double getBalance()
    {
        return this.balance;
    }


    //statement
    /**
     * This method will get the statement
     * @return the bank statement
     */
    public StringBuilder getStatement()
    {
        return statement;
    }


    //method
    /**
     * This method will deposit money to the balance
     *      -if the param is negative, balance will remain unchanged, and throw an Exception
     *      -if the param is positive, it's a valid transaction, it will add
     *  the correct amount to the balance then add a transaction record to the statement
     * @param amount a double variable
     */
    public void deposit(double amount) throws Exception
    {
        //determine if the transaction is valid(positive) or not(negative)
        if (amount <= 0)//if the amount is invalid(negative)
        {
            addStatement("Deposit",amount,false);
            throw new Exception("amount can't be negative or zero");
        }
        //if the transaction is valid(positive)
        else
        {
            this.balance += amount;//add amount to the balance
            addStatement("Deposit",amount,true);
        }
    }


    /**
     * This method will withdraw money from the balance
     *  -withdraw cannot be negative(throw IllegalArgumentException)
     *  -withdraw amount cannot be greater than current balance(throw IllegalStateException)
     *  -if withdraw amount is valid, it will add a transaction to the statement
     * @param amount a double variable
     */
    public void withdraw(double amount) {
        if(amount <= 0)//if amount is negative
        {
            addStatement("Withdraw",amount,false);
            throw new IllegalArgumentException("amount can't be negative or zero");
        }
        else if (amount > this.getBalance()) {
            addStatement("Withdraw",amount,false);
            throw new IllegalStateException("Insufficient Funds");
        }
        else
        {
            this.balance -= amount;//withdraw money from balance
            addStatement("Withdraw",amount,true);
        }
    }

    /**
     * This method will add the statement information to the bankStatement
     * @param userAction a String(Deposit or Withdraw)
     * @param amount a double variable
     * @param isTransactionComplete a bool(true = transaction complete, false = incomplete)
     */
    private void addStatement(String userAction, double amount, boolean isTransactionComplete)
    {
        this.statement.append(String.format("%s\t\t\t",getCurrentTime()));//show time
        this.statement.append(String.format("%s: %.2f\t\t\t",userAction,amount));//show action text
        this.statement.append(String.format("Account Number: %d\t\t\t",this.accountNumber));//show acc number
        this.statement.append(String.format("Current Balance: %.2f\t\t\t",this.balance));//show balance
        if (isTransactionComplete)
        {
            this.statement.append("Transaction Complete\n");//show status
        }
        else
        {
            this.statement.append("Transaction Incomplete\n");//show status
        }
    }


    @Override
    /**
     * Override toString to convert memory address to account number
     */
    public String toString()
    {
        return Integer.toString(getAccountNumber());//convert memory address to account number
    }

}

