package com.example.finalprojectjava;




public class Transaction {
    Integer userID;
    String description;
    TransactionType type;
    Integer amount;
    Currency currency;



    public Transaction(Integer userID, String description, String type, Integer amount, String currency) {

        this.userID = userID;
        this.description = description;
        this.type = TransactionType.valueOf(type);
        this.amount = amount;
        this.currency = Currency.valueOf(currency);
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
