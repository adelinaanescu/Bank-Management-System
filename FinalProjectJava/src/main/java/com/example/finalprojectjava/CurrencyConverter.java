package com.example.finalprojectjava;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyConverter implements  ICurrencyConverter{

   public Double getConvertCoefficiant(String fromcurrency, String tocurrency){
        DatabaseConnection connectionNow= new DatabaseConnection();
        Connection connectionDatabase= connectionNow.getConnection();

        String getcurrencyQuery="SELECT "+ tocurrency +" FROM p3database.changecurrency WHERE Currency='"+fromcurrency+"'";

        try{
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(getcurrencyQuery);
            while(queryResult.next()){
                Double convert_coefficiant=queryResult.getDouble(tocurrency);
                // System.out.println(convert_coefficiant);
                return convert_coefficiant;
            }


        }catch (SQLException e){
            Logger.getLogger(TransactionsController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }

        return 0.0;

    }

}
