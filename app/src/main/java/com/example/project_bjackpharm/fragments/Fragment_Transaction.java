package com.example.project_bjackpharm.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project_bjackpharm.R;
import com.example.project_bjackpharm.TransactionAdapter;
import com.example.project_bjackpharm.activities.HomeActivity;
import com.example.project_bjackpharm.database.Database_Transaction;

import java.util.ArrayList;


public class Fragment_Transaction extends Fragment {

    RecyclerView transRV;
    ArrayList<Database_Transaction> database_transactions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database_transactions = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment__transaction, container, false);
        database_transactions = HomeActivity.transaksi;

//        if(!database_transactions.isEmpty()) {
//            Toast.makeText(getContext(), String.valueOf(database_transactions.size()), Toast.LENGTH_SHORT).show();
//        }


        transRV = (RecyclerView) view.findViewById(R.id.transView);
        TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(), database_transactions);
//        transactionAdapter = new TransactionAdapter(getContext(), database_transactions);
        transRV.setAdapter(transactionAdapter);
        transRV.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        TransactionAdapter transactionAdapter = new TransactionAdapter(getContext(), database_transactions);
        transRV.setAdapter(transactionAdapter);
        transRV.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
