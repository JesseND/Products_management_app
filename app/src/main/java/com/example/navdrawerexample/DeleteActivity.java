package com.example.navdrawerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navdrawerexample.DAO.AccountDAO;

public class DeleteActivity extends AppCompatActivity implements View.OnClickListener {

    Button deleteButton;
    EditText productIDET;
    AccountDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        productIDET = (EditText)findViewById(R.id.pId);
        deleteButton = (Button) findViewById(R.id.deleteBtn);

        deleteButton.setOnClickListener(this);
    }

    public void onClick(View v){
        String idEntered = productIDET.getText().toString();
        int idInt = Integer.valueOf(idEntered).intValue();;
        int count = 0;
        try{
            count = delete(idInt);
        }catch(Exception e){
            e.printStackTrace();
        }
         if(count == 1)
        Toast.makeText(this, "row deleted" ,Toast.LENGTH_LONG ).show();
        else{
             Toast.makeText(this, "deleting the row failed" ,Toast.LENGTH_LONG ).show();
         }
    }

    public int delete (int id){
        dao = new AccountDAO(this);
        int count = dao.delete(id);
        return count;
    }
}
