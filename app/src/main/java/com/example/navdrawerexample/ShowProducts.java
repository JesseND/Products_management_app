package com.example.navdrawerexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.navdrawerexample.DAO.Account;
import com.example.navdrawerexample.DAO.AccountDAO;
import com.example.navdrawerexample.DAO.ProductsAdapter;

import java.util.ArrayList;

public class ShowProducts extends AppCompatActivity {

   ImageButton del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        AccountDAO d = new AccountDAO(this);

        ArrayList<Account> products = d.getAllProducts();

        // Find a reference to the {@link ListView} in the layout
        ListView productListView = (ListView) findViewById(R.id.list);

        final ProductsAdapter myAdapter = new ProductsAdapter(this, products);
        productListView.setAdapter(myAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current earthquake that was clicked on
                Account currentProduct = myAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                String currentProductName = currentProduct.getName();
                double currentProductPrice = currentProduct.getPrice();
                int currentProductId = currentProduct.getId();

                // Create a new intent to view the earthquake URI
                Intent intent = new Intent(ShowProducts.this,ProductDetails.class);

                intent.putExtra("name", currentProductName);
                intent.putExtra("price", currentProductPrice);
                intent.putExtra("id", currentProductId);

                // Send the intent to launch a new activity
                startActivity(intent);
            }
        });
    }


}
