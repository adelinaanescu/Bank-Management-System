package com.example.finalprojectjava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetUserInformation {

    public Integer  getID() throws SQLException, NullQueryException {
        DatabaseConnection connectionNow= new DatabaseConnection();
        Connection connectionDatabase= connectionNow.getConnection();

        LoginController loginController = HelloApplication.fxmlLoader.getController();
        //LoginController loginController = new LoginController();
        String username=loginController.tempusername;
        //System.out.println(username+"...");
        ResultSet queryResult = null;
        String sqlst=" Select userID from p3database.useraccounts where username='"+username+"'";
        try {
            Statement statement=connectionDatabase.createStatement();
            queryResult= statement.executeQuery(sqlst);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
            if(queryResult==null)
                throw new NullQueryException("SQL Select statemant returns null");
        }
       // queryResult.next();
       // System.out.println(loginController.tempusername);

        while(queryResult.next()){
            return queryResult.getInt("UserID");
        }

        return -1;

    }

    public Double  getAccountBalance() throws SQLException {
        DatabaseConnection connectionNow= new DatabaseConnection();
        Connection connectionDatabase= connectionNow.getConnection();

        LoginController loginController = HelloApplication.fxmlLoader.getController();
        //LoginController loginController = new LoginController();
        String username=loginController.tempusername;
       // System.out.println(username);
        ResultSet queryResult = null;
        String sqlst=" Select AccountBalance from p3database.useraccounts where username='"+username+"'";
        try {
            Statement statement=connectionDatabase.createStatement();
            queryResult= statement.executeQuery(sqlst);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        while(queryResult.next()){
            return queryResult.getDouble("AccountBalance");
        }
       // System.out.println(queryResult.getDouble("AccountBalance"+username));
        return -1.0;

    }
}
