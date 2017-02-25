package com.example.navdrawerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navdrawerexample.DAO.Account;
import com.example.navdrawerexample.DAO.AccountDAO;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    EditText idEt;
    EditText nameEt;
    EditText priceEt;
    Button updateBtn;
    int currentProductId;
    double currentProductPrice;
    String currentProductName;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        idEt = (EditText) findViewById(R.id.pId);
        nameEt = (EditText) findViewById(R.id.pName);
        priceEt = (EditText)findViewById(R.id.pPrice);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        getExtrasAndDisplay();
        updateBtn.setOnClickListener(this);
    }

    public void onClick(View v){
        //String idEntered = productIDET.getText().toString();
        int idValue = Integer.valueOf(idEt.getText().toString()).intValue(); ;
        String nameValue = nameEt.getText().toString();
        double priceValue = Double.valueOf(priceEt.getText().toString()).doubleValue();
        Account a = new Account(nameValue, priceValue);
        AccountDAO dao = new AccountDAO(this);
        dao.update(a, idValue);

        Toast.makeText(this, "product with the id: "+idValue+ "updated ",Toast.LENGTH_LONG ).show();
    }

    public void getExtrasAndDisplay(){
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            currentProductId = extras.getInt("id");
            idEt.setText(currentProductId+"");
            currentProductName = extras.getString("name");
            nameEt.setText(currentProductName+"");
            currentProductPrice = extras.getDouble("price");
            priceEt.setText(currentProductPrice+"");
        }else{
            return ;
        }
    }

}
