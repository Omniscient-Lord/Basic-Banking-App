package com.example.basicbankingapp.banking;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerMockData {
    private long accID;
    private String name;
    private long dob;
    private String email;
    private long mobNum;
    private double balance;
    private String address;

    private static List<CustomerMockData> defaultCustomerListMockData = new ArrayList<CustomerMockData>(){
        {
            //Added Mock Data for Customers Here
            add(new CustomerMockData(9484934,"Test User1",getDate("25-08-1974"),"user1@gmail.com",222222,120000.00,"Pune, MH"));
            add(new CustomerMockData(9484938,"Test User2",getDate("28-11-1999"),"user2@yahoo.com",333333,450000.00,"Srinagar, JK"));
            add(new CustomerMockData(9484960,"Test User3",getDate("23-04-1967"),"user3@rediffmail.com",444444,620000.00,"Borivali, MH"));
            add(new CustomerMockData(9484966,"Test User4",getDate("31-05-1978"),"user4@redit.com",555555,200600.00,"Lucknow, UP"));
            }
    };

    public static List<CustomerMockData> getDefaultCustomerList() {
        return defaultCustomerListMockData;
    }

    @SuppressLint("SimpleDateFormat")
    private static long getDate(String source) {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return (Objects.requireNonNull(dateFormat.parse(source)).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public CustomerMockData(){
        this.accID = 0;
        this.name = null;
        this.dob = 0;
        this.email = null;
        this.mobNum = 0;
        this.balance = 0.0;
        this.address = null;
    }

    public CustomerMockData(long accID, String name, long dob, String email, long mobNum, double balance, String address) {
        //Setting Customer Data to Data class
        this.accID = accID;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.mobNum = mobNum;
        this.balance = balance;
        this.address = address;
    }

    public long getAccID() {
        return accID;
    }

    public String getName() {
        return name;
    }

    public long getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public long getMobNum() {
        return mobNum;
    }

    public double getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }
}
