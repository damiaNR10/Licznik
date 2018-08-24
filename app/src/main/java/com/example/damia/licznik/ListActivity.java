package com.example.damia.licznik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class ListActivity extends AppCompatActivity {

    RecyclerView savedRecyclerView;
    public DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        savedRecyclerView = (RecyclerView) findViewById(R.id.savedRecyclerView);

        dataAdapter = new DataAdapter(getLayoutInflater());
        savedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        savedRecyclerView.setAdapter(dataAdapter);
        //Bundle bundle = getIntent().getExtras();
        //Intent intent = new Intent();

        //int rozmiarTablicy = bundle.getInt("rozmiarTablicy");



        int rozmiarTablicy = getIntent().getIntExtra("rozmiarTablicy",0);
        for (int i = 0; i < rozmiarTablicy; i++) {
            //String daneDoZapisu = bundle.getString("tablicaDanych");
            String daneDoZapisu = getIntent().getStringExtra("tablicaDanych");

            dataAdapter.add(daneDoZapisu);
            Log.d("sprawdzam", daneDoZapisu);
        }
        /*String daneDoZapisu = bundle.getString("tablicaDanych");
        dataAdapter.add(daneDoZapisu);
        Log.d("sprawdzam", daneDoZapisu);*/
    }
}
