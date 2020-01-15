package com.example.ferry.engineertechnical.Fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ferry.engineertechnical.DataFavorite;
import com.example.ferry.engineertechnical.MetaDataFavorite;
import com.example.ferry.engineertechnical.R;
import com.example.ferry.engineertechnical.sqlite.SQLiteConfig;
import com.example.ferry.engineertechnical.utils.RecyclerViewAdapter;
import com.example.ferry.engineertechnical.utils.RecyclerViewAdapterFavor;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment {

    protected SQLiteConfig config;
    DataFavorite dataFavorite;

    private RecyclerView rvViewFavorite;

    private RecyclerView.Adapter adapterFavorite;

    private ArrayList<MetaDataFavorite> arrayListFavorite;

    public FavoriteFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        config = new SQLiteConfig(getContext());
        dataFavorite= new DataFavorite(getContext());
        arrayListFavorite= new ArrayList<>();





        rvViewFavorite = view.findViewById(R.id.rv_mainFavo);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvViewFavorite.setHasFixedSize(true);
        int spanCount = 2;
        int spacing = 10;
        boolean includeEdge = true;
        rvViewFavorite.setLayoutManager(gridLayoutManager);
        rvViewFavorite.addItemDecoration(new FavoriteFragment.GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        rvViewFavorite.setItemAnimator(new DefaultItemAnimator());


        arrayListFavorite= dataFavorite.selectList();

        adapterFavorite = new RecyclerViewAdapterFavor(arrayListFavorite,getContext());
        rvViewFavorite.setAdapter(adapterFavorite);

        return view ;
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


    public void updateNewsList(){


    }
}
