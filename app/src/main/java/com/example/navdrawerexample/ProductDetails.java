package com.example.navdrawerexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navdrawerexample.DAO.Account;
import com.example.navdrawerexample.DAO.AccountDAO;

import java.math.BigDecimal;

public class ProductDetails extends AppCompatActivity {

    TextView prodId ;
    TextView prodName;
    TextView prodPrice;
    ImageButton change, increaseP, decreaseP, delete_img;
    int currentProductId;

    String currentProductName;
    double currentProductPrice;
    Account a;
    AccountDAO dao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Bundle extras = getIntent().getExtras();
        currentProductId = extras.getInt("id");
        currentProductName= extras.getString("name");
        currentProductPrice = extras.getDouble("price");
        init();

        increaseP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new AccountDAO(getApplicationContext());

                currentProductPrice = formatDoubleValue((currentProductPrice +=1));
                a = new Account(getApplicationContext());
                a.setName(currentProductName);
                a.setPrice(currentProductPrice);
                dao.update(a, currentProductId);
                prodPrice.setText(""+currentProductPrice);

            }
        });


        decreaseP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new AccountDAO(getApplicationContext());
                if(currentProductPrice>=1)
                {
                    currentProductPrice = formatDoubleValue((currentProductPrice-=1));
                }

                a = new Account(getApplicationContext());
                a.setName(currentProductName);
                a.setPrice(currentProductPrice);
                dao.update(a, currentProductId);
                prodPrice.setText(""+currentProductPrice);
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this,UpdateActivity.class);
                intent.putExtra("id", currentProductId);
                intent.putExtra("name", currentProductName);
                intent.putExtra("price",currentProductPrice );
                startActivity(intent);
            }
        });

        delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonClicked();
            }
        });

    }

    public void init(){
        prodId = (TextView) findViewById(R.id.product_id);
        prodId.setText(""+currentProductId);
        prodName = (TextView) findViewById(R.id.product_name);
        prodName.setText(currentProductName);
        prodPrice = (TextView) findViewById(R.id.product_price);
        prodPrice.setText(""+currentProductPrice);

        change = (ImageButton) findViewById(R.id.edit_imgBtn);
        increaseP = (ImageButton) findViewById(R.id.increaseP);
        decreaseP = (ImageButton) findViewById(R.id.decreaseP);
        delete_img = (ImageButton) findViewById(R.id.delete_img);


        Toast.makeText(this," "+currentProductId+" "+currentProductName+" "+currentProductPrice,Toast.LENGTH_LONG ).show();
    }


    public void deleteButtonClicked(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to delete this product?");
        alertDialogBuilder.setPositiveButton("confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        dao = new AccountDAO(getApplicationContext());
                        dao.delete(currentProductId);
                        Toast.makeText(ProductDetails.this, " "+currentProductName+"deleted in your products database ", Toast.LENGTH_LONG);
                        Intent i = new Intent(ProductDetails.this, ShowProducts.class);
                        startActivity(i);
                    }
                });

        alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public double formatDoubleValue(double d){
        BigDecimal bd = new BigDecimal(d);
        bd  = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
}

