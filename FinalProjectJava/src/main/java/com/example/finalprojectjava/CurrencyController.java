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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CurrencyController implements Initializable {

    ObservableList<String> currencyList= FXCollections.observableArrayList("RON","EURO","DOLLAR");

    @FXML
    private Button backButton;

    @FXML
    private Button convertButton;

    @FXML
    private TextField amountTextField;

    @FXML
    private ChoiceBox fromBox;

    @FXML
    private ChoiceBox toBox;

    @FXML
    private Label convertAmountLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fromBox.setItems(currencyList);
        toBox.setItems(currencyList);

    }

    public void convertButtonOnAction(ActionEvent e){

        CurrencyConverter currencyConverter=new CurrencyConverter();
        Double coeff=currencyConverter.getConvertCoefficiant(fromBox.getValue().toString(),toBox.getValue().toString());
        Double result=coeff*Double.parseDouble(amountTextField.getText());
        convertAmountLabel.setText(result.toString());

    }
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage=(Stage) backButton.getScene().getWindow();
        stage.close();
    }


}
