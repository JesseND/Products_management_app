package com.example.navdrawerexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "product.db";
    public  static final int DB_VERSION = 1;


    public static final String PRODUCT_TABLE_NAME = "account";

    //Columns to include in the table
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";


    /*
    create table prices( id integer primary key autoincrement,
        name text,
        price integer);
     */
    public static final String CREATE_TABLE = "CREATE TABLE " + PRODUCT_TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PRICE + " REAL );";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+ PRODUCT_TABLE_NAME );
        onCreate(db);
    }

    public SQLiteDatabase open(){
        return getReadableDatabase();
    }




   public void close(){
          if (this!=null)
           close();
   }
}
