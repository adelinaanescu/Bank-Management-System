package com.example.finalprojectjava;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import  javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;


public class RegistrationController {
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField setpasswordPasswordField;

    @FXML
    private PasswordField confirmpasswordPasswordField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Label registrationMessageLabel;

    @FXML
    private Label confirmregistrationMessageLabel;

    public void cancelButtonOnACtion(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();

    }

    public void registrationButtonOnACtion(ActionEvent e){

        if(setpasswordPasswordField.getText().equals(confirmpasswordPasswordField.getText())){
            registrationUser();
            confirmregistrationMessageLabel.setText("");
            registrationMessageLabel.setText("Account has been created!");
        }
        else{
            confirmregistrationMessageLabel.setText("Password missmatch");

        }
    }

    public void registrationUser(){
       DatabaseConnection connection= new DatabaseConnection();
       Connection connectDB= connection.getConnection();

       String name= nameTextField.getText();
       String username= usernameTextField.getText();
       String password= confirmpasswordPasswordField.getText();

       String insertFields="INSERT INTO useraccounts(Username,Password,Name) VALUES ('";
       String insertValues=username + "' , '" + password + "' , '"+ name + "')";
       String insertToRegister= insertFields+insertValues;

       try{
           Statement statement=connectDB.createStatement();
           statement.executeUpdate(insertToRegister);

       }catch(Exception e){
           e.printStackTrace();
           e.getCause();
       }


    }
    




}