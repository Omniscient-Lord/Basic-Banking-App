package com.example.basicbankingapp.banking;

import android.content.Context;

import com.example.basicbankingapp.database.CustomersInfo;

public class Transaction {

    private String transactionId;
    private long senderAcc;
    private long receiverAcc;
    private long time;
    private double amount;

    public Transaction(){
        transactionId = null;
        senderAcc = 0;
        receiverAcc = 0;
        time = 0;
        amount = 0.0;
    }

    public Transaction(String transactionId, long senderAcc, long receiverAcc, long time, double amount) {
        this.transactionId = transactionId;
        this.senderAcc = senderAcc;
        this.receiverAcc = receiverAcc;
        this.time = time;
        this.amount = amount;
    }

    public Transaction(Transaction transaction,long time){
        transactionId = null;
        senderAcc = transaction.senderAcc;
        receiverAcc = transaction.receiverAcc;
        this.time = time;
        amount = transaction.amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public long getSenderAcc() {
        return senderAcc;
    }

    public long getReceiverAcc() {
        return receiverAcc;
    }

    public long getTime() {
        return time;
    }

    public double getAmount() {
        return amount;
    }

    public void generateTransactionID(Context context){
        CustomersInfo customersInfo = new CustomersInfo(context);
        String senderName = customersInfo.detailOfCustomer(senderAcc).getName();
        String receiverName = customersInfo.detailOfCustomer(receiverAcc).getName();
        transactionId = senderName.charAt(0) + "" + receiverName.charAt(0) + time;
    }
}
