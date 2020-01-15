package com.example.ferry.engineertechnical.screan;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ferry.engineertechnical.DataFavorite;
import com.example.ferry.engineertechnical.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {


    private Button favoriteButton ;
    private DataFavorite dataFavorite;

    private String imdbID,judul,released,runtime, genre, director, writer, actors, plot, poster ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle extras=getIntent().getExtras();

        favoriteButton= findViewById(R.id.favotitebutton);
        dataFavorite= new DataFavorite(this);

        if(extras!=null){
            imdbID= extras.getString("imdbID");

        }
        else{
            imdbID=null;}

        callDetail(imdbID);


        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataFavorite.save(judul,released,runtime, genre, director, writer, actors, plot, poster,imdbID );

                Log.e("DATA","data adalah "+dataFavorite.selectList().size());

            }
        });

    }




    private void  callDetail(String imdbID){

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        progressDialog.setMessage(getString(R.string.load));
//        showDialog();
        String baseurl = "http://www.omdbapi.com/?apikey=db2f9d24&i=";

        String url= baseurl + imdbID ;


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    JSONObject jObj = new JSONObject(response);


                     judul = jObj.getString("Title");
                     released = jObj.getString("Released");
                     runtime = jObj.getString("Runtime");
                     genre = jObj.getString("Genre");
                     director = jObj.getString("Director");
                     writer = jObj.getString("Writer");
                     actors = jObj.getString("Actors");
                    plot = jObj.getString("Plot");
                     poster = jObj.getString("Poster");


                    Picasso.with(getApplicationContext()).load(poster).into( (ImageView) findViewById(R.id.main_backdrop));

                    CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
                    collapsingToolbarLayout.setTitle(judul);

                    ((TextView) findViewById(R.id.grid_title)).setText(judul);
                    ((TextView) findViewById(R.id.grid_writers)).setText(writer);
                    ((TextView) findViewById(R.id.grid_actors)).setText(actors);
                    ((TextView) findViewById(R.id.grid_director)).setText(director);
                    ((TextView) findViewById(R.id.grid_genre)).setText(genre);
                    ((TextView) findViewById(R.id.grid_released)).setText(released);
                    ((TextView) findViewById(R.id.grid_plot)).setText(plot);
                    ((TextView) findViewById(R.id.grid_runtime)).setText(runtime);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, "Request Error", Toast.LENGTH_SHORT).show();

            }
        });


        queue.add(request);

    }
}
