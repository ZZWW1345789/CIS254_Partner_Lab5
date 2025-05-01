package bankapp.cis254_partner_lab5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class BankAccountController
{


    ObservableList<BankAccount> bankAccountArrayList = FXCollections.observableArrayList();

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setGraphic(new Label("✓"));
        alert.setTitle("Account Created");
        alert.setHeaderText("Succeed!");
        alert.setContentText("Your Bank Account is Successfully created");
        alert.showAndWait();

        bankAccountArrayList.add(new BankAccount());
    }

    @FXML
    private void onDepositButtonClick()
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
                    bankAccountsDropDown.getSelectionModel().getSelectedItem().deposit(amount);
                    accountBalance.setText(Double.toString(bankAccountsDropDown.getSelectionModel().getSelectedItem().getBalance()));
                });
            }
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
                    bankAccountsDropDown.getSelectionModel().getSelectedItem().withdraw(amount);
                    accountBalance.setText(Double.toString(bankAccountsDropDown.getSelectionModel().getSelectedItem().getBalance()));
                });
            }
        }
    }

    @FXML
    private void onTransactionLogButtonClick()
    {

    }

    private void generateAlert(String errorMsg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Malfunction");
        alert.setContentText(errorMsg);
    }

    private void generateInfoAlert(String conformation)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information");
        alert.setHeaderText("Malfunction");
        alert.setContentText(conformation);
    }
}