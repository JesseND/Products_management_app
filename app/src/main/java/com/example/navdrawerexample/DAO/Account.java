package com.example.navdrawerexample.DAO;


import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class Account {
   private int _id ;
   private double price;
   private String name;

    public Account(Context context){

    }
    public Account(String mName, double mPrice){
        name = mName;
        price = mPrice;

    }

    public Account(int mId, String mName, double mPrice){
        _id = mId;
        name = mName;
        price = mPrice;

    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String string(){
        return "";
    }
}
