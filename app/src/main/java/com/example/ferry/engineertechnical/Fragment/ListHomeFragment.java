package com.example.ferry.engineertechnical.Fragment;


import android.app.ProgressDialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ferry.engineertechnical.R;
import com.example.ferry.engineertechnical.utils.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListHomeFragment extends Fragment {


    private ProgressDialog progressDialog;
    private AppCompatAutoCompleteTextView textSearch;
    private Button buttonSearch;
    private ArrayList<String> Title,Year,imdbID,Type,Poster;
    private RecyclerView rvView;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public ListHomeFragment() {

    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_list_home, container, false);
       progressDialog = new ProgressDialog(getActivity());
       progressDialog.setCancelable(false);
       textSearch= view.findViewById(R.id.editTextSearch);
            buttonSearch= view.findViewById(R.id.buttonSearch);
            Title= new ArrayList<>();
            Year= new ArrayList<>();
            imdbID= new ArrayList<>();
            Type= new ArrayList<>();
            Poster= new ArrayList<>();



            rvView = view.findViewById(R.id.rv_main);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            rvView.setHasFixedSize(true);
            int spanCount = 2;
            int spacing = 10;
            boolean includeEdge = true;
            rvView.setLayoutManager(gridLayoutManager);
            rvView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
            rvView.setItemAnimator(new DefaultItemAnimator());

            buttonSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callData(textSearch.getText().toString());
                }
            });
            adapter = new RecyclerViewAdapter(Title,Year,imdbID,Type,Poster,getContext());
        return view;

    }



    private void callData(String keyWord){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        progressDialog.setMessage(getString(R.string.load));
//        showDialog();
        String baseurl = "http://www.omdbapi.com/?apikey=db2f9d24&type=movie&s=";

        String url= baseurl + keyWord ;


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray ststus = jObj.getJSONArray("Search");

                    Title.clear();
                    Year.clear();
                    imdbID.clear();
                    Type.clear();
                    Poster.clear();

                    for(int i =0;i<ststus.length();i++) {
                        JSONObject item = ststus.getJSONObject(i);
                        Title.add(item.get("Title").toString());
                        Year.add(item.get("Year").toString());
                        imdbID.add(item.get("imdbID").toString());
                        Type.add(item.get("Type").toString());
                        Poster.add(item.get("Poster").toString());
                    }
                    updateNewsList();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Request Error", Toast.LENGTH_SHORT).show();

            }
        });


        queue.add(request);

    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    public void updateNewsList(){
        rvView.setAdapter(adapter);

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
}
