package bankapp.cis254_partner_lab5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

public class BankAccountController
{


    ObservableList<BankAccount> bankAccountArrayList = FXCollections.observableArrayList();

    @FXML
    private Rectangle headerRectangle;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button transactionLogButton;

    @FXML
    private Label accountBalance;

    @FXML
    private ComboBox<BankAccount> bankAccountsDropDown;


    @FXML
    private void initialize()
    {
        bankAccountsDropDown.setItems(bankAccountArrayList);
        bankAccountsDropDown.setOnAction(actionEvent ->
        {
            accountBalance.setText(Double.toString(bankAccountsDropDown.getSelectionModel().getSelectedItem().getBalance()));
        });

    }

    @FXML
    private void onCreateAccountButtonClick()
    {
        generateConformation("Account Created",
                    "Succeed!",
                    "Your Bank Account is Successfully created");

        bankAccountArrayList.add(new BankAccount());
    }

    @FXML
    private void onDepositButtonClick()
    {
        BankAccount account = bankAccountsDropDown.getSelectionModel().getSelectedItem();

        if(bankAccountArrayList.isEmpty())
        {
            generateAlert("No Account Find",
                        "Must create an account first!!!",
                        "please create an account before deposit!!!");
        }
        else if (account == null) {
            generateAlert("Must Select an Account",
                    "Must select an account",
                    "Please select an account before deposit!!!");
        }
        else
        {
            TextInputDialog inputDialog = new TextInputDialog("0");
            inputDialog.setTitle("Deposit");
            inputDialog.setHeaderText("How much money would you like to deposit?");
            inputDialog.setContentText("amount:");
            Optional<String> result = inputDialog.showAndWait();
            result.ifPresent(input->
            {
                double amount = Double.parseDouble(input);
                // bankAccountsDropDown.getSelectionModel().getSelectedItem().deposit(amount);
                accountBalance.setText(Double.toString(bankAccountsDropDown.getSelectionModel().getSelectedItem().getBalance()));
            });
        }
    }

    @FXML
    private void onWithdrawButtonClick()
    {
        if(bankAccountArrayList.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Account Find");
            alert.setHeaderText("Must create an account first!!!");
            alert.setContentText("Please create an account before deposit!!!");
            alert.showAndWait();
        }
        else
        {
            if(bankAccountsDropDown.getSelectionModel().getSelectedItem() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Must Select an Account");
                alert.setHeaderText("Must select an account");
                alert.setContentText("Please select an account before deposit!!!");
                alert.showAndWait();
            }
            else
            {
                TextInputDialog inputDialog = new TextInputDialog("0");
                inputDialog.setTitle("Deposit");
                inputDialog.setHeaderText("How much money would you like to deposit?");
                inputDialog.setContentText("amount:");
                Optional<String> result = inputDialog.showAndWait();
                result.ifPresent(input->
                {
                    double amount = Double.parseDouble(input);
                    //bankAccountsDropDown.getSelectionModel().getSelectedItem().withdraw(amount);
                    accountBalance.setText(Double.toString(bankAccountsDropDown.getSelectionModel().getSelectedItem().getBalance()));
                });
            }
        }
    }

    @FXML
    private void onTransactionLogButtonClick()
    {
        System.out.println("hello");
    }

    private void generateAlert(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void generateConformation(String title, String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(new Label("✓"));
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private double generateInputBox(String title, String header, String content)
    {
        TextInputDialog inputDialog = new TextInputDialog("0");

        inputDialog.setTitle(title);
        inputDialog.setHeaderText(header);
        inputDialog.setContentText(content);
        Optional<String> result = inputDialog.showAndWait();

        if(result.isEmpty())
        {
            generateAlert("Error",
                        "Invalid input: Empty input",
                        "Must enter an valid value");
            return 0.0;
        }
        else
        {
//            try
//            {
//                return Double.parseDouble(result.get());
//            }
//            catch (Exception)
        }


//        result.ifPresent(input->
//        {
////            double amount = Double.parseDouble(input);
////            // bankAccountsDropDown.getSelectionModel().getSelectedItem().deposit(amount);
////            accountBalance.setText(Double.toString(bankAccountsDropDown.getSelectionModel().getSelectedItem().getBalance()));
//        });
    }

}