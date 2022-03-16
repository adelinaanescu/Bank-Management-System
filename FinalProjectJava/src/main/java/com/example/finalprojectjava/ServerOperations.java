package com.example.finalprojectjava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ServerOperations implements IServerOperations {

    public boolean validateLogin(String usernameTextField,String passwordPasswordField){
        DatabaseConnection connectionNow= new DatabaseConnection();
        Connection connectionDatabase= connectionNow.getConnection();
        boolean cnt=false;

        String verifyLogin=" Select count(1) from useraccounts where username='"+usernameTextField+"' and password = '"+ passwordPasswordField+"'";
        try {
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    cnt=true;
                    break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        if(cnt)
            return true;
        else
            return false;
    }
}
