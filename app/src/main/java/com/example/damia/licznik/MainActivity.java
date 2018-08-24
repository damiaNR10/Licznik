package com.example.damia.licznik;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<SaveValues> savedList = new ArrayList<>();
    EditText etKwotaNetto;
    CheckBox cbZus;
    TextView tvVat, tvZus, tvDochodowy, tvBrutto, tvKwotaFinalna;
    Button bPrzelicz, bZapisz, bPokazZapisane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etKwotaNetto = (EditText) findViewById(R.id.etKwotaNetto);
        cbZus = (CheckBox) findViewById(R.id.cbZus);
        tvDochodowy = (TextView) findViewById(R.id.tvDochodowy);
        tvBrutto = (TextView) findViewById(R.id.tvBrutto);
        tvVat = (TextView) findViewById(R.id.tvVat);
        tvZus = (TextView) findViewById(R.id.tvZus);
        tvKwotaFinalna = (TextView) findViewById(R.id.tvKwotaFinalna);
        bPrzelicz = (Button) findViewById(R.id.bPrzelicz);
        bZapisz = (Button) findViewById(R.id.bZapisz);
        bPokazZapisane = (Button) findViewById(R.id.bPokazZapisane);

        bPrzelicz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etKwotaNetto.getText())) {
                    double kwotaNetto = Double.valueOf(etKwotaNetto.getText().toString());
                    double kwotaVatu = 0.23 * kwotaNetto;
                    tvVat.setText("VAT: " + String.valueOf(kwotaVatu));
                    bZapisz.setVisibility(View.VISIBLE);
                    bPokazZapisane.setVisibility(View.VISIBLE);
                    if(cbZus.isChecked()){
                        double kwotaZusu = kwotaNetto - kwotaVatu - 520;
                        double kwotaDochodowego = 0.18 * kwotaZusu;
                        tvDochodowy.setText("Podatek dochodowy: " + String.valueOf(kwotaDochodowego));
                        tvZus.setText("ZUS: 520");
                        double kwotaBrutto = kwotaNetto * 1.23;
                        tvBrutto.setText("Kwota Brutto: " + kwotaBrutto);
                        double kwotaFinalna = kwotaBrutto - kwotaVatu - kwotaDochodowego - 520;
                        tvKwotaFinalna.setText("Kwota finalna: " + kwotaFinalna);
                    }else{
                        double kwotaZusu = kwotaNetto - kwotaVatu - 1200;
                        double kwotaDochodowego = 0.18 * kwotaZusu;
                        tvDochodowy.setText("Podatek dochodowy: " + String.valueOf(kwotaDochodowego));
                        tvZus.setText("ZUS: 1200");
                        double kwotaBrutto = kwotaNetto  * 1.23;
                        tvBrutto.setText("Kwota Brutto: " + kwotaBrutto);
                        double kwotaFinalna = kwotaBrutto - kwotaVatu - kwotaDochodowego - 1200;
                        tvKwotaFinalna.setText("Kwota finalna: " + kwotaFinalna);
                    }

                } else if(TextUtils.isEmpty(etKwotaNetto.getText())) {
                    bZapisz.setVisibility(View.GONE);
                    bPokazZapisane.setVisibility(View.GONE);
                    tvVat.setText("Podaj kwotę netto!");
                    tvZus.setText("");
                    tvBrutto.setText("");
                    tvDochodowy.setText("");
                    tvKwotaFinalna.setText("");
                }
            }
        });
        bZapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        bPokazZapisane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                Intent intent1 = new Intent(MainActivity.this, ListActivity.class);

                for (int i = 0; i < savedList.size(); i++) {
                    intent1.putExtra("tablicaDanych" + String.valueOf(i), savedList.get(0).getNazwa());
                }
                startActivity(intent1);
            }
        });



    }

    public void showInputDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Zapisz jako: ");
        final EditText saveInput = new EditText(MainActivity.this);
        saveInput.setInputType(InputType.TYPE_CLASS_TEXT);
        saveInput.setHint("Nazwa");
        builder.setView(saveInput);
        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SaveValues saveValues = new SaveValues(saveInput.getText().toString());
                saveValues.setBrutto(tvBrutto.getText().toString());
                saveValues.setDochodowy(tvDochodowy.getText().toString());
                saveValues.setFinalna(tvKwotaFinalna.getText().toString());
                saveValues.setNetto(etKwotaNetto.getText().toString());
                savedList.add(saveValues);
                //Log.d("sprawdzam", savedList.get(0).toString() + "    " + saveValues.getNazwa());

                for (int i = 0; i < savedList.size(); i++) {
                    Log.d("sprawdzam", savedList.get(i).getNazwa() + "    " + saveValues.getNazwa());
                }


            }
        });
        builder.show();
    }
}
