package com.example.damia.licznik;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    String valueToCheck;
    public static final String VALUE_TO_CHECK_KEY = "value_to_check";
    TextView tvVat, tvZus, tvDochodowy, tvBrutto, tvKwotaFinalna, tvNetto;
    SaveValues saveValueToShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tvDochodowy = (TextView) findViewById(R.id.tvDochodowy);
        tvBrutto = (TextView) findViewById(R.id.tvBrutto);
        tvVat = (TextView) findViewById(R.id.tvVat);
        tvZus = (TextView) findViewById(R.id.tvZus);
        tvKwotaFinalna = (TextView) findViewById(R.id.tvKwotaFinalna);
        tvNetto = (TextView) findViewById(R.id.tvNetto);
        Intent intent = getIntent();
        valueToCheck = intent.getSerializableExtra(VALUE_TO_CHECK_KEY).toString();
        saveValueToShow = (SaveValues) intent.getSerializableExtra(VALUE_TO_CHECK_KEY);

        if(valueToCheck != null){
            tvVat.setText(saveValueToShow.getVat());
            tvBrutto.setText(saveValueToShow.getBrutto());
            tvDochodowy.setText(saveValueToShow.getDochodowy());
            tvKwotaFinalna.setText(saveValueToShow.getFinalna());
            tvNetto.setText("Kwota Netto: " + saveValueToShow.getNetto());
            tvZus.setText(saveValueToShow.getZus());
        }
    }
}
