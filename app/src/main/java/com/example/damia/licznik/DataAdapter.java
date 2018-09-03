package com.example.damia.licznik;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataViewHolder>{

    private final LayoutInflater layoutInflater;
    private final SharedPreferences sharedPreferences;
    private List<SaveValues> savedList = new ArrayList<>();
    DataClickedListener dataClickedListener;

    public DataAdapter(LayoutInflater layoutInflater, SharedPreferences sharedPreferences){

        this.layoutInflater = layoutInflater;
        this.sharedPreferences = sharedPreferences;

        //String savedObjectJson = sharedPreferences.getString("SAVED_KEY", "[]");
        Gson gson = new Gson();
        //JSONArray jsonArray = new JSONArray(savedObjectJson);
        String json = sharedPreferences.getString("MyObject", "");
        SaveValues obj = gson.fromJson(json, SaveValues.class);
        //for (int i = 0; i < jsonArray.length(); i++) {
        //savedList.add(i, obj);
        //for (int i = 0; i < savedList.size(); i++) {
        //savedList.add(obj);
        /*for(int a = 0; a < sharedPreferences.getAll().size(); a++ ){
            //if(sharedPreferences.getAll().size() > 0){
                String json = sharedPreferences.getString("MyObject", "");
                SaveValues obj = gson.fromJson(json, SaveValues.class);
                savedList.add(a, obj);
                Log.d("sprawdzam", obj.getNazwa());
            //}
        }*/
        //Log.d("sprawdzam", obj.getNazwa());
        //}
        //savedList.add(obj);
        //}
        notifyDataSetChanged();
        /*List<SaveValues> arrayToSave= new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            savedList.add(obj[i]);
        }*/
    }

    @Override//sluzy do utworzenia widokow kiedy sa potrzebne
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new DataViewHolder(view,this);
    }

    @Override//zadaniem jest przypisanie odpowiednich wartości do kazdego wiersza
    public void onBindViewHolder(DataViewHolder dataViewHolder, int i) {
        String nazwaZapisu = savedList.get(i).getNazwa();
        dataViewHolder.setData(nazwaZapisu);
    }

    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public void remove(int position){
        savedList.remove(position);
        //saveInPreferences();
    }

    public void add(SaveValues nazwa){
        savedList.add(nazwa);
        //notifyItemInserted(savedList.size() - 1);
        notifyDataSetChanged();
        //saveInPreferences();
    }

    private void saveInPreferences() {
        JsonArray arrayToSave= new JsonArray();

        for (int i = 0; i < savedList.size(); i++) {
            //arrayToSave.add(String.valueOf(savedList.get(i)));
            Gson gsonToJson = new Gson();
            String jsonIt = gsonToJson.toJson(savedList.get(i));
            arrayToSave.add(jsonIt);
        }
        Gson gson = new Gson();
        //String json = gson.toJson(arrayToSave);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("SAVED_KEY",arrayToSave.toString());
        editor.apply();
    }

    public void setDataClickedListener(DataClickedListener dataClickedListener) {
        this.dataClickedListener = dataClickedListener;
    }

    public void clicked(int position) {
        if(dataClickedListener != null){
            dataClickedListener.onDataClicked(savedList.get(position));
        }
    }

    public interface DataClickedListener {
        void onDataClicked(SaveValues saveValues);
    }

}

class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private String data;
    private TextView textView;
    private final DataAdapter dataAdapter;

    public DataViewHolder(View itemView, DataAdapter dataAdapter) {
        super(itemView);
        textView = (TextView) itemView;
        this.dataAdapter = dataAdapter;
        textView.setOnClickListener(this);
    }

    public void setData(String data) {
        this.data = data;
        textView.setText(data);
    }

    public String getData() {
        return data;
    }

    @Override
    public void onClick(View view) {
        dataAdapter.clicked(getPosition());
    }

}