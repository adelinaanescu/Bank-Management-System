package com.example.finalprojectjava;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private Label loginMessageLabel;


    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Button cancelButton;

    @FXML
    private ImageView bankImageView;

    @FXML
    private Button newaccountButton;

    public static String tempusername;

    public void setTempusername(String tempusername) {
        this.tempusername = tempusername;

    }

    private ServerOperations serverOperations = new ServerOperations();


    public void loginButtonOnAction(ActionEvent e){

        if(!usernameTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()){
            loginMessageLabel.setText("Try to login!");
            if(serverOperations.validateLogin(usernameTextField.getText(),passwordPasswordField.getText()))
            {
                loginMessageLabel.setText("Welcome");
                setTempusername(usernameTextField.getText());
                createAccountStage();


            }else
                loginMessageLabel.setText("Invalid Login. Try again!");

        }else{
            loginMessageLabel.setText("Please enter username and password!");
        }
    }

    public void newaccountButtonOnAction(ActionEvent e){

       createRegistrationStage();
    }

    public void cancelButtonOnACtion(ActionEvent e){
        Stage stage=(Stage) cancelButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void createAccountStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("account-view.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Welcome in your account");
            registrationStage.setScene(new Scene(root,373,409));
            registrationStage.show();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createRegistrationStage(){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("registration.fxml"));
            Stage registrationStage= new Stage();
            registrationStage.setTitle("Create Accout");
            registrationStage.setScene(new Scene(root,373,409));
            registrationStage.show();


        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }





}