package com.example.finalprojectjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application  {

    public static FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public void start(Stage primaryStage) throws IOException {



       // System.out.println(username+" "+password);

        Scanner in = new Scanner(System.in);
        System.out.println("\tHello! \n\t Please select an option:\n\t[1]Start platform with login\n\t[2]Start platform with auto-login(program arguments needed)\n");

        int option = in.nextInt();

        Parent root = null;

        if(option == 1){
            fxmlLoader.setLocation(getClass().getResource("login-view.fxml"));
            root= fxmlLoader.load();
           // root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        } else if(option == 2) {

            String username = new String("");
            String password = new String("");
            username = getParameters().getUnnamed().get(0);
            password = getParameters().getUnnamed().get(1);
            LoginController loginController=new LoginController();
            loginController.setTempusername(username);
            ServerOperations serverOperations=new ServerOperations();
            if(serverOperations.validateLogin(username,password))
            {
                System.out.println("Welcome "+username+" !\n");
                root = FXMLLoader.load(getClass().getResource("account-view.fxml"));
            }
            else
                System.out.println("Invalid username or password\n");


        } else
            System.out.println("Invalid option\n");

        //Parent root= FXMLLoader.load(getClass().getResource("login-view.fxml"));
        Scene scene= new Scene(root,373,409);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

       launch(args);

    }
}