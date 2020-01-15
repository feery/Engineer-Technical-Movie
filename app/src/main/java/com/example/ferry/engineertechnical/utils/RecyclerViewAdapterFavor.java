package com.example.ferry.engineertechnical.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferry.engineertechnical.MetaDataFavorite;
import com.example.ferry.engineertechnical.R;
import com.example.ferry.engineertechnical.screan.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerViewAdapterFavor extends RecyclerView.Adapter<RecyclerViewAdapterFavor.ViewHolder> {

    private ArrayList<MetaDataFavorite>metaDataFavorites;
    private Context context;

    public RecyclerViewAdapterFavor(ArrayList<MetaDataFavorite> arrayListFavorite, Context context) {

        this.metaDataFavorites=arrayListFavorite;
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        return new RecyclerViewAdapterFavor.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {






        Log.e("Data","Holder arrayListFavorite get Title "+ metaDataFavorites.get(position).getJudul());

        holder.view_title.setText(metaDataFavorites.get(position).getJudul());
        holder.view_jam.setText(metaDataFavorites.get(position).getActors());
        holder.view_tipe.setText(metaDataFavorites.get(position).getGenre());
        holder.view_harga.setText(metaDataFavorites.get(position).getDirector());
        Picasso.with(context).load(metaDataFavorites.get(position).getPoster()).into(holder.imageView);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle dataBundel= new Bundle();
                dataBundel.putString("imdbID",holder.view_harga.getText().toString());

                Intent intent=new Intent(view.getContext(), DetailActivity.class);
                intent.putExtras(dataBundel);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return metaDataFavorites.size();
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView view_title;
        private TextView view_jam;
        private TextView view_harga;
        private TextView view_tipe;
        private CardView cardView;
        private ImageView imageView;



        ViewHolder(View v) {
            super(v);
            view_jam = v.findViewById(R.id.tv_jam);
            view_title = v.findViewById(R.id.tv_title);
            view_harga = v.findViewById(R.id.tv_harga);
            view_tipe = v.findViewById(R.id.tv_tipe);
            cardView = v.findViewById(R.id.cv_main);
            imageView= v.findViewById(R.id.image);

        }
    }
}
