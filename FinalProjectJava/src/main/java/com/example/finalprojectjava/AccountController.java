package com.example.finalprojectjava;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountController implements Initializable{

    @FXML
    private Button logoutButton;

    @FXML
    private Button showTransactionsButton;

    @FXML
    private Button showAddTransactionButton;

    @FXML
    private Button showCurrencyButton;

    @FXML
    private Label accountBalanceMessageLabel;

    public void logoutButtonOnAction(ActionEvent e){
        Stage stage=(Stage) logoutButton.getScene().getWindow();
        stage.close();

    }
    public void showTransactionsButtonOnAction(ActionEvent e){

        showTransactionsStage();
    }
    public void showAddTransactionButtonOnAction(ActionEvent e){

        showAddTransactionStage();
    }
    public void showCurrencyButtonOnAction(ActionEvent e){

        currencyStage();
    }

    public void showTransactionsStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("transactions-view.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Search Transactions");
            registrationStage.setScene(new Scene(root,373,409));
            registrationStage.show();



        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void showAddTransactionStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("add-transaction.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Add Transaction");
            registrationStage.setScene(new Scene(root,373,409));
            registrationStage.show();



        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void currencyStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("check-currency.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Convert Currency");
            registrationStage.setScene(new Scene(root,373,409));
            registrationStage.show();

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        GetUserInformation getUserInformation=new GetUserInformation();
        try {
            accountBalanceMessageLabel.setText(getUserInformation.getAccountBalance().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}




