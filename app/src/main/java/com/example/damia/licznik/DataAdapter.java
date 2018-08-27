package com.example.damia.licznik;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataViewHolder>{

    private final LayoutInflater layoutInflater;
    //private List<SaveValues> savedList = new ArrayList<>();
    private List<String> savedList = new ArrayList<>();


    public DataAdapter(LayoutInflater layoutInflater){

        this.layoutInflater = layoutInflater;
    }

    @Override//sluzy do utworzenia widokow kiedy sa potrzebne
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new DataViewHolder(view);
    }

    @Override//zadaniem jest przypisanie odpowiednich wartości do kazdego wiersza
    public void onBindViewHolder(DataViewHolder dataViewHolder, int i) {
        String nazwaZapisu = savedList.get(i);

        dataViewHolder.setData(nazwaZapisu);
    }

    @Override
    public int getItemCount() {
        return savedList.size();
    }

    public void add(String nazwa){
        savedList.add(nazwa);
        notifyItemInserted(savedList.size() - 1);
    }
}

class DataViewHolder extends RecyclerView.ViewHolder{

    private String data;
    private TextView textView;

    public DataViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;
    }

    public void setData(String data) {
        this.data = data;
        textView.setText(data);
    }

    public String getData() {
        return data;
    }
}
