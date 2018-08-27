package com.example.damia.licznik;

import android.graphics.Color;
import android.provider.ContactsContract;
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
    private List<SaveValues> savedList = new ArrayList<>();
    DataClickedListener dataClickedListener;

    public DataAdapter(LayoutInflater layoutInflater){

        this.layoutInflater = layoutInflater;
    }

    @Override//sluzy do utworzenia widokow kiedy sa potrzebne
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new DataViewHolder(view,this );
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

    public void add(SaveValues nazwa){
        savedList.add(nazwa);
        notifyItemInserted(savedList.size() - 1);
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
