package com.example.finalprojectjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddTransactionController implements Initializable {

    ObservableList<String> typeList= FXCollections.observableArrayList("Expense","Income");
    ObservableList<String> currencyList= FXCollections.observableArrayList("RON","EURO","DOLLAR");

    @FXML
    private Button backButton;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TextField amountTextField;

    @FXML
    private ChoiceBox currencyBox;

    @FXML
    private ChoiceBox typeBox;

    @FXML
   private Label confirmationMessageLabel;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeBox.setItems(typeList);
        currencyBox.setItems(currencyList);

    }
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
    }

    public void addTransactionButtonOnAction(ActionEvent e) throws SQLException, AmountException, NullQueryException, InvalidAmountInputException {
        GetUserInformation getUserInformation=new GetUserInformation();
        Integer userID=getUserInformation.getID();
        addTransactionUser(userID);
        confirmationMessageLabel.setText("All Done!");

    }

    public void addTransactionUser(Integer userID) throws SQLException, AmountException, NullQueryException, InvalidAmountInputException {
        DatabaseConnection connection= new DatabaseConnection();
        Connection connectDB= connection.getConnection();


        String currency= currencyBox.getValue().toString();
        String type = typeBox.getValue().toString();
        String description= descriptionTextField.getText();
        String amount=amountTextField.getText();

        try {
            double d = Double.parseDouble(amount);
        } catch (NumberFormatException nfe) {
            if(amount.contains(","))
                throw new InvalidAmountInputException("Use . instead of ,");

        }


        String insertFields="INSERT INTO usertransactions(UserID,TransactionType,Amount,Currency,Description) VALUES ('";
        String insertValues=userID + "' , '" + type + "' , '"+ amount + "' , '"+currency+ "' , '"+ description + "')";
        String insertToRegister= insertFields+insertValues;

        updateAccountBalance(amount,type,currency);

        try{
            Statement statement=connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }

    void updateAccountBalance(String amount, String type,String currency) throws SQLException, AmountException, NullQueryException {

        Double newamount=Double.valueOf(amount);
        if(currency.equals("EURO"))
            newamount=newamount*4.94;
        if(currency.equals("DOLLAR"))
            newamount=newamount*4.35;

        GetUserInformation getUserInformation=new GetUserInformation();
        if(type.equals("Expense")){


               if(newamount<=getUserInformation.getAccountBalance())
                   newamount=getUserInformation.getAccountBalance()- newamount;
               else
                   throw new AmountException("The Expense is too much, not enough money");


        }
        else{

            newamount=getUserInformation.getAccountBalance() + newamount;

        }
        DatabaseConnection connection= new DatabaseConnection();
        Connection connectDB= connection.getConnection();
        String updateField="UPDATE`useraccounts` SET AccountBalance="+newamount+" WHERE UserID="+getUserInformation.getID();

        try{
            Statement statement=connectDB.createStatement();
            statement.executeUpdate(updateField);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }



    }

}


