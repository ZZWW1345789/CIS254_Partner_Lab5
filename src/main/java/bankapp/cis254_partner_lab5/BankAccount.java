package bankapp.cis254_partner_lab5;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
/**
 * Partner_Lab5：Design a BankAccount class with GUI
 *
 * @author Z Wang
 * @since 4/27/2025
 */

/**
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

    //Create a Calendar object
    Calendar time = Calendar.getInstance();

    //Create a method to get instant time
    private Date getInstantTime()
    {
        return Calendar.getInstance().getTime();
    }

    private void updateStatement()
    {
        String statement = String.format(
                "%s" +
                        "\t\tCreating Account" +
                        "\t\tAccount number : %d" +
                        "\t\tCurrent balance: %f" +
                        "\t\tAccount Created\n"
                ,getInstantTime(),this.accountNumber,this.balance);
    }


    //constructor
    /**
     * Default constructor
     *  Create a BankAcount object with 0 balance
     */
    public BankAccount()
    {
        this.balance = 0;//set balance to zero
        this.statement = new StringBuilder(String.format("%s\t\tCreating Account\t\tAccount number %d\t\tCurrent balance: %f\t\tAccount Created\n",getInstantTime(),accountNumber,balance));//update statement
    }

    /**
     * overloaded constructor
     *  Create a Bank accountant object with the balance passed in param
     * @param balance is an int variable
     */
    public BankAccount(double balance)
    {
        this.balance = balance;//set the balance to param value
        this.statement = new StringBuilder(String.format("%s\t\tCreating Account\t\tAccount number %d\t\tCurrent balance: %f\t\tAccount Created\n",time.getTime(),accountNumber,balance));//update statement
    }




    //variable declaration

    private final int accountNumber = Math.abs(rand.nextInt());//generate a random accountNumber

    private double balance;//balance

    private StringBuilder statement;//statement


    //gets and sets

    //accountNumber
    /**
     * This method will get the Account Number
     * @return the accountNumber
     */
    private int getAccountNumber()
    {
        return this.accountNumber;
    }


    //balance

    /**
     * This method will get the balance
     * @return the balance
     */
    private double getBalance()
    {
        return this.balance;
    }


    //statement

    /**
     * This method will get the statement
     * @return the bank statement
     */
    private StringBuilder getStatement()
    {
        return statement;
    }


    //method

    /**
     * This method will deposit money to the balance
     *      -if the param is negative, balance will remain unchanged, add an error message to statement
     *      -if the param is positive, it's a valid transaction, it will add
     *  the correct amount to the balance then add a transaction record to the statement
     * @param amount an int variable
     */
    private void deposit(double amount)
    {
        //determine if the transaction is valid(positive) or not(negative)
        if (amount < 0)//if the amount is invalid(negative)
        {
            this.statement.append(String.format("%s\t\tDeposit: %f\t\t\tAccount Number %d\t\tCurrent Balance: %f\t\tTransaction Incomplete: deposit cannot be negative\n", time.getTime(), amount, this.accountNumber, balance));//print an error message to the statement
        }
        //if the transaction is valid(positive)
        else
        {
            this.balance += amount;//add amount to the balance
            this.statement.append(String.format("%s\t\tDeposit: %f\t\t\tAccount Number %d\t\tCurrent Balance: %f\t\tTransaction Completed\n", time.getTime(), amount, this.accountNumber, balance));//add a message to the statement
        }
    }


    /**
     * This method will withdraw money from the balance
     *  -withdraw cannot be negative
     *  -withdraw amount cannot be greater than current balance
     *  -if withdraw amount is valid, it will add a transaction to the statement
     * @param amount an int variable
     */
    private void withdraw(double amount)
    {
        if(amount < 0)//if amount is negative
        {
            this.statement.append(String.format("%s\t\tWithdraw: %f\t\t\tAccount Number %d\t\tCurrent Balance: %f\t\tTransaction Incomplete: cannot withdraw negative amount\n",time.getTime(),amount,this.accountNumber,balance));//add an error message to the statement
        }
        else//if amount is positive
        {
            if(this.getBalance() > amount)//if the balance is greater than withdraw amount
            {
                this.balance -= amount;//withdraw money from balance
                this.statement.append(String.format("%s\t\tWithdraw: %f\t\t\tAccount Number %d\t\tCurrent Balance: %f\t\tTransaction Completed\n",time.getTime(),amount,this.accountNumber,balance));//transaction completed
            }
            else//if the balance is less than withdraw amount
            {
                this.statement.append(String.format("%s\t\tWithdraw: %f\t\t\tAccount Number %d\t\tCurrent Balance: %f\t\tTransaction Incomplete: Not enough balance\n",time.getTime(),amount,this.accountNumber,balance));//print an error message
            }
        }
    }

    @Override
    public String toString()
    {
        return Integer.toString(getAccountNumber());
    }

}

