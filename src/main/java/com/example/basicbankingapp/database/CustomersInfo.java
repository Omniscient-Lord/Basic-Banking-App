package com.example.basicbankingapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basicbankingapp.banking.CustomerMockData;

import java.util.ArrayList;
import java.util.List;

public class CustomersInfo extends DatabaseConnection {

    private static final String dbName = "BANKING";
    private static final int dbVersion = 1;
    private static final String tableName = CUSTOMER_TABLE;

    public CustomersInfo(Context context) {
        super(context, dbName, dbVersion);
    }

    public boolean save(CustomerMockData customerMockData){
        boolean success = false;
        try{
            database = getWritableDatabase();
            database.execSQL("insert into " + tableName + " ("+ accID +", " + name + ", " + dob + ", " + email + ", " + mobNum + ", " + balance + ", " + address + ") values(" + customerMockData.getAccID() + ",'" + customerMockData.getName() + "'," + customerMockData.getDob() + ",'" + customerMockData.getEmail() + "'," + customerMockData.getMobNum() + "," + customerMockData.getBalance() + ",'" + customerMockData.getAddress() + "')");
            success = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public List<CustomerMockData> allCustomers(){
        database = getReadableDatabase();
        try (Cursor cursor = database.rawQuery("select * from " + tableName + ";", null)) {
            List<CustomerMockData> customersList = new ArrayList<>();
            while (cursor.moveToNext()) {
                customersList.add(new CustomerMockData(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getLong(4), cursor.getDouble(5), cursor.getString(6)));
            }
            return customersList;
        }
    }

    public CustomerMockData detailOfCustomer(long customerID){
        database = getReadableDatabase();
        CustomerMockData customerMockData = new CustomerMockData();
        try{
            try(Cursor cursor = database.rawQuery("select * from " + tableName + " where " + accID + "=" + customerID + ";",null)){
                cursor.moveToFirst();
                customerMockData = new CustomerMockData(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getLong(4), cursor.getDouble(5), cursor.getString(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customerMockData;
    }

    public void updateBalance(long accID,double newBalance){
        try{
            database = getWritableDatabase();
            database.execSQL("update " + tableName + " set " + balance + "=" + newBalance + " where " + CustomersInfo.accID + "=" + accID);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static long count(Context context){
        long count = 0;
        try{
            CustomersInfo customersInfo = new CustomersInfo(context);
            SQLiteDatabase database = customersInfo.getReadableDatabase();
            try (Cursor cursor = database.rawQuery("select count(*) from " + tableName + ";", null)) {
                cursor.moveToFirst();
                count= cursor.getLong(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public static void updateDBIfDefaultNotFound(Context context){
        try {
            long count = count(context);
            CustomersInfo customersInfo = new CustomersInfo(context);
            if (count != 10){
                for (CustomerMockData customerMockData : CustomerMockData.getDefaultCustomerList()){
                    customersInfo.save(customerMockData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
