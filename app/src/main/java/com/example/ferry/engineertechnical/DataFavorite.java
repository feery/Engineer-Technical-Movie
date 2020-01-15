package com.example.ferry.engineertechnical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ferry.engineertechnical.sqlite.SQLiteConfig;
import com.example.ferry.engineertechnical.sqlite.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class DataFavorite {

    private static final String TABLE                   ="SMMF_V3_USER";
    private static final String _ID                     ="_ID";
    private static final String TITLE                   ="TITLE";
    private static final String RUNTIME                 ="RUNTIME";
    private static final String RELEASED                ="RELEASED";
    private static final String GENRE                   ="GENRE";
    private static final String DIRECTOR                ="DIRECTOR";
    private static final String WRITER                  ="WRITER";
    private static final String ACTORS                  ="ACTORS";
    private static final String PLOT                    ="PLOT";

    private static final String POSTER="POSTER";
    private static final String ID_OMDBAPI="ID_OMDBAPI";





    public static final String CREATE_TABLE =
            " CREATE TABLE "+TABLE+" ("
                    +_ID+" INTEGER PRIMARY KEY, "

                    +TITLE+" TEXT, "
                    +RUNTIME+" TEXT, "
                    +RELEASED+" TEXT, "
                    +GENRE+" TEXT, "
                    +DIRECTOR+" TEXT, "
                    +WRITER+" TEXT, "
                    +ACTORS+" TEXT, "
                    +PLOT+" TEXT, "
                    +POSTER+" TEXT, "
                    +ID_OMDBAPI+" TEXT); ";

    public static final String DROP_TABLE =
            "DROP TABLE IF EXISTS "+TABLE;



    private final Context context;
    private SQLiteHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;



    public DataFavorite(Context context) {
        this.context = context;
    }

    private DataFavorite open() throws SQLException {
        SQLiteConfig config = new SQLiteConfig(context);
        databaseHelper = new SQLiteHelper(context,config.getSqliteName(),config.getSqliteVer());
        return this;
    }

    private void close() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen())
            sqLiteDatabase.close();
    }

    public MetaDataFavorite save(MetaDataFavorite model) {
        open();
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues v = new ContentValues();

        v.put(TITLE, model.getJudul());
        v.put(RUNTIME, model.getRuntime());
        v.put(RELEASED, model.getReleased());
        v.put(GENRE, model.getGenre());
        v.put(DIRECTOR, model.getDirector());
        v.put(WRITER, model.getWriter());
        v.put(ACTORS, model.getActors());
        v.put(PLOT, model.getPlot());
        v.put(POSTER, model.getPoster());
        v.put(ID_OMDBAPI, model.getId_omdbapi());


        if (model.get_id() == null) {
            Long id = sqLiteDatabase.insert(TABLE, null, v);
            model.set_id(id.intValue());
        } else {
            sqLiteDatabase.update(TABLE, v, _ID + "=?", new String[]{String.valueOf(model.get_id())});
        }

        close();
        return model;
    }


    public MetaDataFavorite save( String judul ,
    String released,
    String runtime ,
    String genre ,
    String director ,
    String writer ,
    String actors ,
    String plot ,
    String poster ,
    String id_omdbapi)


    {
        MetaDataFavorite model = new MetaDataFavorite();

           model.setJudul(judul);
           model.setActors(actors);
          model.setWriter(writer);
          model.setReleased(released);
          model.setPlot(plot);
          model.setPoster(poster);
          model.setDirector(director);
          model.setGenre(genre);
          model.setRuntime(runtime);
          model.setId_omdbapi(id_omdbapi);

        return save(model);
    }



    public ArrayList<MetaDataFavorite> selectList() {
        String q = "SELECT * FROM "+TABLE + " WHERE 1=1";


        open();
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(q, null);

        ArrayList<MetaDataFavorite> list = new ArrayList<>();
        MetaDataFavorite metaData;
        if (c.moveToFirst()) {
            do {
                metaData = new MetaDataFavorite();
                metaData.set_id(c.getInt(c.getColumnIndex(_ID)));
                metaData.setJudul(c.getString((c.getColumnIndex(TITLE))));
                metaData.setReleased(c.getString((c.getColumnIndex(RELEASED))));
                metaData.setRuntime(c.getString((c.getColumnIndex(RUNTIME))));
                metaData.setGenre(c.getString((c.getColumnIndex(GENRE))));
                metaData.setDirector(c.getString((c.getColumnIndex(DIRECTOR))));
                metaData.setWriter(c.getString((c.getColumnIndex(WRITER))));
                metaData.setActors(c.getString(c.getColumnIndex(ACTORS)));
                metaData.setPlot(c.getString((c.getColumnIndex(PLOT))));
                metaData.setPoster(c.getString((c.getColumnIndex(POSTER))));
                metaData.setId_omdbapi(c.getString((c.getColumnIndex(ID_OMDBAPI))));

                list.add(metaData);
            } while (c.moveToNext());
        }

        close();
        return list;
    }




}
