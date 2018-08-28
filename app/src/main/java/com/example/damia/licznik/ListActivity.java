package com.example.damia.licznik;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class ListActivity extends AppCompatActivity implements DataAdapter.DataClickedListener {

    RecyclerView savedRecyclerView;
    public DataAdapter dataAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        savedRecyclerView = (RecyclerView) findViewById(R.id.savedRecyclerView);

        dataAdapter = new DataAdapter(getLayoutInflater(), PreferenceManager.getDefaultSharedPreferences(this));
        dataAdapter.setDataClickedListener(this);
        savedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        savedRecyclerView.setAdapter(dataAdapter);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        int rozmiarTablicy = bundle.getInt("rozmiarTablicy");
        for (int i = 0; i < rozmiarTablicy; i++) {
            SaveValues daneDoZapisu = (SaveValues) bundle.getSerializable("tablicaDanych" + String.valueOf(i));
            dataAdapter.add(daneDoZapisu);
            Log.d("sprawdzam", daneDoZapisu.getNazwa());
        }
    }

    @Override
    public void onDataClicked(SaveValues saveValues) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(MainActivity.VALUE_TO_CHECK_KEY, saveValues);
        startActivityForResult(intent, 2);
    }
}
