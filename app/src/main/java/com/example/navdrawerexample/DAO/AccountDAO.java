package com.example.navdrawerexample.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.navdrawerexample.DBHelper;

import java.util.ArrayList;


public class AccountDAO {
    private DBHelper helper;
    private SQLiteDatabase db;
    private ArrayList<Account> ret ;

    public AccountDAO(Context context){
        helper = new DBHelper(context);
    }

    public void addProduct(Account a) {
        ContentValues values = new ContentValues();
        values.put("name", a.getName());
        values.put("price", a.getPrice());
         db = helper.open();
        long id = db.insert("account", null, values);
        db.close();
    }

    public ArrayList<Account> getAllProducts(){
        ArrayList<Account> array_list = new ArrayList<Account>();

        db = helper.open();
        Cursor res =  db.rawQuery( "select * from account", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            String name = res.getString(res.getColumnIndex("name"));
            double price = res.getDouble(res.getColumnIndex("price"));
            int id = res.getInt(res.getColumnIndex("_id"));
            Account a = new Account(id,name, price);
            array_list.add(a);
            res.moveToNext();
        }
        res.close();
        return array_list;
    }

    public int delete(int id){
       db = helper.open();
        int count = db.delete(helper.PRODUCT_TABLE_NAME, "_id="+id, null);
        db.close();

        return count;
    }

    public void update(Account a, int id){
        ContentValues value = new ContentValues();
        value.put("name", a.getName());
        value.put("price", a.getPrice());
        db = helper.open();

        db.update("account", value, "_id ="+ id, null);
        db.close();

    }

}
