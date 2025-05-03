package bankapp.cis254_partner_lab5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Scanner;

/**
 * This class is the Controller of the BankInterface.fxml
 */
public class BankAccountController
{

    //create an ObservableList to store BankAccount
    ObservableList<BankAccount> bankAccountArrayList = FXCollections.observableArrayList();

    /**
     * This method will generate a window that for user's input
     * @param title String, title of the window
     * @param header String, header of the window
     * @param content String, content of the window
     * @return a double variable
     */
    private double generateInputBox(String title, String header, String content) {

        TextInputDialog inputDialog = new TextInputDialog("0");//create a window for input with the textField of 0

        inputDialog.setTitle(title);//set title
        inputDialog.setHeaderText(header);//set header
        inputDialog.setContentText(content);//set content
        Optional<String> result = inputDialog.showAndWait();//receive input with an Optional container

        if (result.isEmpty())//if the container is empty
        {
            generateAlert("Error",
                    "Invalid input: Empty input",
                    "Must enter an valid value");//pop alert
            return 0.0;//return 0
        }
        else {
            try {
                return Double.parseDouble(result.get());//try to convert result to double and return it
            }
            catch (Exception e)
            {
                return 0.0;//return 0 if not nontransferable
            }
        }
    }

    /**
     * This method will generate a Conformation window
     * @param title String, title of the window
     * @param header String, header of the window
     * @param content String, content of the window
     */
    private void generateConformation(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//create an Information window
        alert.setGraphic(new Label("✓"));//replace the ugly icon to an ugly check mark
        alert.setTitle(title);//set title
        alert.setHeaderText(header);//set header
        alert.setContentText(content);//set content
        alert.showAndWait();//show and wait
    }

    /**
     * This method will generate an alert window
     * @param title String, title of the alert window
     * @param header String, header of the alert window
     * @param content String, content of the alert window
     */
    private void generateAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);//create an Alert window
        alert.setTitle(title);//set title
        alert.setHeaderText(header);//set header
        alert.setContentText(content);//set content
        alert.showAndWait();//show and wait
    }

    @FXML
    private Rectangle headerRectangle;//create a rectangle

    @FXML
    private Button createAccountButton;//create a create account button

    @FXML
    private Button depositButton;//create a deposit button

    @FXML
    private Button withdrawButton;//create a withdrawal button

    @FXML
    private Button transactionLogButton;//create a transaction Log Button

    @FXML
    private Label accountBalance;//create a label to display balance

    @FXML
    private ComboBox<BankAccount> bankAccountsDropDown;//create a ComboBox for account selection

    /**
     * Initialize ComboBox -> when an item was selected
     * in the ComboBox, display the corresponding balance
     */
    @FXML
    private void initialize()
    {

        bankAccountsDropDown.setItems(bankAccountArrayList);//adding and display ObservableList through ComboBox

        bankAccountsDropDown.setOnAction(actionEvent ->//when items were selected in the ComboBox
        {
            BankAccount account = bankAccountsDropDown.getSelectionModel().getSelectedItem();//hold set User's selection with account

            accountBalance.setText(Double.toString(account.getBalance()));//display the balance of the selected account
        });
    }

    /**
     * This method will add a BankAccount to ObservableList then pop up a conformation window
     */
    @FXML
    private void onCreateAccountButtonClick()
    {
        bankAccountArrayList.add(new BankAccount());//add a new BankAccount to ObservableList
        generateConformation("Account Created",
                "Succeed!",
                "Your Bank Account is Successfully created");//conformation
    }

    /**
     * This method will make a deposit when the button is clicked
     * @throws Exception Invalid input(negative number, zeros, or letters)
     */
    @FXML
    private void onDepositButtonClick() throws Exception
    {
        BankAccount account = bankAccountsDropDown.getSelectionModel().getSelectedItem();//hold set User's selection with account

        if(bankAccountArrayList.isEmpty())//if no account was added to the list
        {
            generateAlert("No Account Find",
                        "Must create an account first!!!",
                        "please create an account before deposit!!!");//pop up an alert
        }
        else if (account == null)//if no account was selected
        {
            generateAlert("Must Select an Account",
                    "Must select an account",
                    "Please select an account before deposit!!!");//pop up an alert
        }
        else
        {
            try
            {
                account.deposit(generateInputBox("Deposit","How much you want to deposit?", "Please enter a number"));//deposit
                accountBalance.setText(Double.toString(account.getBalance()));//display updated balance
                generateConformation("Transaction Complete",
                                    "Success",
                                    "Your deposit is now added to your balance");//conformation
            }
            catch (Exception e)//if user input(negative, zeros, or letters)
            {
                generateAlert("Error","Invalid Input","please enter a positive number");//pop up an alert
            }
        }
    }

    /**
     * This method will make a withdrawal when the button is clicked
     * @throws IllegalArgumentException when input is negative, zeros, or letter
     * @throws IllegalStateException when the account has insufficient Funds
     */
    @FXML
    private void onWithdrawButtonClick() throws Exception
    {

        BankAccount account = bankAccountsDropDown.getSelectionModel().getSelectedItem();//hold set User's selection with account

        if(bankAccountArrayList.isEmpty())//if no account was added to the list
        {
            generateAlert("No Account Find",
                    "Must create an account first!!!",
                    "please create an account before deposit!!!");//pop up an alert
        }
        else if (account == null)//if no account was selected
        {
            generateAlert("Must Select an Account",
                    "Must select an account",
                    "Please select an account before deposit!!!");//pop up an alert
        }
        else
        {
            try
            {
                account.withdraw(generateInputBox("Withdraw","How much you want to withdraw?", "Please enter a number"));//withdrawal
                accountBalance.setText(Double.toString(account.getBalance()));//display updated balance
                generateConformation("Transaction Complete",
                        "Success",
                        "the amount is now withdraw from your balance");
            }
            catch (IllegalArgumentException e)//if user input(negative, zeros, or letters)
            {
                generateAlert("Error","Invalid Input", "please enter a positive number");//pop an alert
            }
            catch (IllegalStateException e)//if user input(negative, zeros, or letters)
            {
                generateAlert("Error","Invalid input", "Insufficient Funds");//pop an alert
            }
        }
    }

    /**
     * This method open a statement window when the button is clicked
     */
    @FXML
    private void onTransactionLogButtonClick()
    {
        BankAccount account = bankAccountsDropDown.getSelectionModel().getSelectedItem();//hold set User's selection with account

        if(bankAccountArrayList.isEmpty())//if no account was added to the list
        {
            generateAlert("No Account Find",
                    "Must create an account first!!!",
                    "please create an account before deposit!!!");
        }
        else if (account == null)//if no account was selected
        {
            generateAlert("Must Select an Account",
                    "Must select an account",
                    "Please select an account before deposit!!!");
        }
        else
        {
            Stage statementWindow = new Stage();//create new window

            Label statement = new Label(account.getStatement().toString());//create a label to display account's statement

            VBox box = new VBox(statement);//create a vbox to hold the label

            Scene scene = new Scene(box,1000,600);//create a new scene

            statementWindow.setScene(scene);//pass scene to statement window
            statementWindow.setTitle("Statement");
            statementWindow.show();//display statement window
        }
    }
}