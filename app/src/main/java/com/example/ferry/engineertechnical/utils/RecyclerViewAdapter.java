package com.example.ferry.engineertechnical.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferry.engineertechnical.screan.DetailActivity;
import com.example.ferry.engineertechnical.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Feery on 7/11/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private ArrayList<String> Title,Year,imdbID,Type,Poster;
    private Context context;

    public RecyclerViewAdapter(
            ArrayList<String>Title,
            ArrayList<String>Year,
            ArrayList<String>imdbID,
            ArrayList<String>Type,
            ArrayList<String>Poster,
            Context context) {


        this.Title=Title;
        this.Year=Year;
        this.imdbID=imdbID;
        this.Type=Type;
        this.Poster=Poster;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_rv_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.ViewHolder viewHolder, int i) {




            viewHolder.view_title.setText(Title.get(i));
            viewHolder.view_jam.setText(Type.get(i));
            viewHolder.view_tipe.setText(Year.get(i));
            viewHolder.view_harga.setText(imdbID.get(i));
            Picasso.with(context).load(Poster.get(i)).into(viewHolder.imageView);


            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle dataBundel= new Bundle();
                    dataBundel.putString("imdbID",viewHolder.view_harga.getText().toString());

                    Intent intent=new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtras(dataBundel);
                    view.getContext().startActivity(intent);
                }
            });


    }



    @Override
    public int getItemCount() {
        return Title.size();
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





