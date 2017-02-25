package com.example.navdrawerexample.DAO;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog.Builder;
import java.util.ArrayList;

import com.example.navdrawerexample.R;



public class ProductsAdapter extends ArrayAdapter<Account> {

    ImageButton del;

    public ProductsAdapter(Context context, ArrayList<Account> products) {
        super(context, 0, products);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.product_list_item, parent, false);
        }

        final Account currentProduct = getItem(position);

        TextView productId = (TextView)listItemView.findViewById(R.id.mytext1);
        productId.setText(""+currentProduct.getId());

        TextView productName = (TextView)listItemView.findViewById(R.id.mytext2);
        productName.setText(currentProduct.getName());

        TextView productPrice = (TextView)listItemView.findViewById(R.id.mytext3);
        productPrice.setText(""+currentProduct.getPrice());

//        del = (ImageButton)  listItemView.findViewById(R.id.delete_img);
//
//
//        del.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Builder builder = new Builder(getContext());
//                 builder.setTitle("Confirm ");
//                 builder.setIcon(R.drawable.delete_icon);
//                 builder.setMessage("Are you sure you want to delete the product?");
//
//                 builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                     @Override
//                     public void onClick(DialogInterface dialog, int which) {
//
//                         int id = currentProduct.getId();
//                         AccountDAO dao = new AccountDAO(getContext());
//                         Toast.makeText(getContext(), "Product: "+currentProduct.getName()+"has been deleted", Toast.LENGTH_LONG).show();
//                         dao.delete(id);
//                         notifyDataSetChanged();
//                     }
//                 });
//
//                 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                     @Override
//                     public void onClick(DialogInterface dialog, int which) {
//                         Toast.makeText(getContext(), "Product: "+currentProduct.getName()+" still in your database. ", Toast.LENGTH_LONG).show();
//                     }
//                 });
//             }
//         });
        return listItemView;
    }

}
