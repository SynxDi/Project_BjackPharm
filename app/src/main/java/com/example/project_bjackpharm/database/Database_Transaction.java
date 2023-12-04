package com.example.project_bjackpharm.database;


public class Database_Transaction {


    Integer transactionID, jumlahQuantity, price_Transaction;
    String transactionDate, medicine_image_Transaction, medicine_name_transaction;

    public void setJumlahQuantity(Integer jumlahQuantity) {
        this.jumlahQuantity = jumlahQuantity;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Database_Transaction(Integer transactionID, Integer jumlahQuantity, Integer price_Transaction, String transactionDate, String medicine_image_Transaction, String medicine_name_transaction) {
        this.transactionID = transactionID;
        this.jumlahQuantity = jumlahQuantity;
        this.price_Transaction = price_Transaction;
        this.transactionDate = transactionDate;
        this.medicine_image_Transaction = medicine_image_Transaction;
        this.medicine_name_transaction = medicine_name_transaction;
    }

    public Integer getTransactionID() {
        return transactionID;
    }

    public Integer getJumlahQuantity() {
        return jumlahQuantity;
    }

    public Integer getPrice_Transaction() {
        return price_Transaction;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getMedicine_image_Transaction() {
        return medicine_image_Transaction;
    }

    public String getMedicine_name_transaction() {
        return medicine_name_transaction;
    }
}

