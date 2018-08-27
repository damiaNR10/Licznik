package com.example.damia.licznik;

import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;

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

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();


        //int rozmiarTablicy = getIntent().getIntExtra("rozmiarTablicy",0);
        int rozmiarTablicy = bundle.getInt("rozmiarTablicy");
        for (int i = 0; i < rozmiarTablicy; i++) {
            //Intent intent = this.getIntent();
            //Bundle bundle = intent.getExtras();
            SaveValues daneDoZapisu = (SaveValues) bundle.getSerializable("tablicaDanych" + String.valueOf(i));
            //String daneDoZapisu = bundle.getString("tablicaDanych");
            //String daneDoZapisu = getIntent().getStringExtra("tablicaDanych");
            //Serializable daneDoZapisu = getIntent().getSerializableExtra("tablicaDanych");
            dataAdapter.add(daneDoZapisu.getNazwa());
            Log.d("sprawdzam", daneDoZapisu.getNazwa());
        }
        /*String daneDoZapisu = bundle.getString("tablicaDanych");
        dataAdapter.add(daneDoZapisu);
        Log.d("sprawdzam", daneDoZapisu);*/
    }
}
