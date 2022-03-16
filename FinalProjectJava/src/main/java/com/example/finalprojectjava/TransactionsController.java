package com.example.finalprojectjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionsController implements Initializable {
    @FXML
    private TableView<Transaction> transactionTableView;
    @FXML
    private TableColumn<Transaction, TransactionType> typeTableColumn;

    @FXML
    private TableColumn<Transaction, String> descriptionTableColumn;

    @FXML
    private TableColumn<Transaction, Integer > amountTableColumn;
    @FXML
    private TableColumn<Transaction, Currency>  currencyTableColumn;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button backButton;


    ObservableList<Transaction> transactionObservableList= FXCollections.observableArrayList();

    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection connectionNow= new DatabaseConnection();
        Connection connectionDatabase= connectionNow.getConnection();

        String transactionsviewQuery="SELECT UserID,TransactionType,Amount,Currency,Description FROM p3database.usertransactions";

        try{
            Statement statement=connectionDatabase.createStatement();
            ResultSet queryResult= statement.executeQuery(transactionsviewQuery);
            while(queryResult.next()){
                Integer queryID=queryResult.getInt("UserID");
                String querydescription=queryResult.getString("Description");
                String querytype=queryResult.getString("TransactionType");
                Integer queryamount=queryResult.getInt("Amount");
                String querycurrency=queryResult.getString("Currency");


              GetUserInformation getUserInformation=new GetUserInformation();
              if(queryID==getUserInformation.getID())
                    transactionObservableList.add(new Transaction(queryID,querydescription,querytype,queryamount,querycurrency));

            }

            typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            amountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
            currencyTableColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));

            transactionTableView.setItems(transactionObservableList);

            FilteredList<Transaction> filteredData=new FilteredList<>(transactionObservableList,b ->true);

            searchTextField.textProperty().addListener((observable,oldValue,newValue) -> {
                filteredData.setPredicate( transaction  -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();
                if(transaction.getType().toString().toLowerCase().contains(searchKeyword)){
                            return true;
                }else if(transaction.getDescription().toLowerCase().contains(searchKeyword)){
                    return true;
                }else if(transaction.getAmount().toString().contains(searchKeyword)){
                    return true;
                }else if(transaction.getCurrency().toString().toLowerCase().contains(searchKeyword)) {
                    return true;
                }else
                    return false; //keyword is not in the list


            });

            });

            SortedList<Transaction> sortedData=new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(transactionTableView.comparatorProperty());
            transactionTableView.setItems(sortedData);




        }
        catch(SQLException | NullQueryException e){
            Logger.getLogger(TransactionsController.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }

    }
}
