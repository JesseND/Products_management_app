package com.example.navdrawerexample;

import com.example.navdrawerexample.DAO.Account;
import com.example.navdrawerexample.DAO.AccountDAO;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertFragment extends Fragment implements View.OnClickListener {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button addButton;
    EditText mPnameEt;
    EditText mPpriceEt;


    private String mParam1;
    private String mParam2;


    public InsertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertFragment newInstance(String param1, String param2) {
        InsertFragment fragment = new InsertFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_insert, container, false);
          addButton = (Button) view.findViewById(R.id.addBtn);
          mPnameEt = (EditText) view.findViewById(R.id.nameEt);
          mPpriceEt = (EditText) view.findViewById(R.id.priceEt);
        addButton.setOnClickListener(this);
        return view;
    }

    public void onClick(View v){
         String nameV = mPnameEt.getText().toString();
         String value = mPpriceEt.getText().toString();
         double priceV = 0;
          try{
              priceV = Double.valueOf(value).doubleValue();
              Account account = new Account(nameV, priceV);
              AccountDAO dao = new AccountDAO(getActivity());
              dao.addProduct(account);

          }catch(Exception e){
              e.printStackTrace();
          }

        Toast.makeText(getActivity(), "name of the product: "+ nameV+" price: "+priceV,Toast.LENGTH_LONG ).show();
    }
}