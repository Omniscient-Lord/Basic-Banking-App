package com.example.basicbankingapp.logic;

import android.content.Context;

import com.example.basicbankingapp.banking.CustomerMockData;
import com.example.basicbankingapp.banking.Transaction;
import com.example.basicbankingapp.database.CustomersInfo;
import com.example.basicbankingapp.database.TransactionsInfo;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionHandler {

    private Transaction transaction;
    private TransactionsInfo transactionsInfoDB;
    private CustomerMockData sender;
    private CustomerMockData receiver;
    private CustomersInfo customersInfoDB;
    private Context context;

    public TransactionHandler(Context context, Transaction transactDone){
        this.context = context;
        transaction = transactDone;
        transactionsInfoDB = new TransactionsInfo(context);
        customersInfoDB = new CustomersInfo(context);
        sender = customersInfoDB.detailOfCustomer(transactDone.getSenderAcc());
        receiver = customersInfoDB.detailOfCustomer(transactDone.getReceiverAcc());
    }

    public boolean make(){
        boolean success=false;
        if(sender.getBalance()>=transaction.getAmount()){
            try {
                transaction = new Transaction(transaction,new Date().getTime());
                transaction.generateTransactionID(context);
                transactionsInfoDB.save(transaction);
                double senderNewBalance = sender.getBalance()-transaction.getAmount();
                double receiverNewBalance = receiver.getBalance()+transaction.getAmount();
                customersInfoDB.updateBalance(sender.getAccID(),new BigDecimal(senderNewBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                customersInfoDB.updateBalance(receiver.getAccID(),new BigDecimal(receiverNewBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                success = true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return success;
    }

}
