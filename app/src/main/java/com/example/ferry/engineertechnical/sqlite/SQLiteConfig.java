package com.example.ferry.engineertechnical.sqlite;

import android.content.Context;

import com.example.ferry.engineertechnical.utils.AssetsPropertyReader;

import java.util.Properties;



public class SQLiteConfig {

    private String sqliteName;
    private Integer sqliteVer;
    private Integer serverVersion;



    public SQLiteConfig(Context context) {
        AssetsPropertyReader assetsPropertyReader = new AssetsPropertyReader(context);
        Properties properties = assetsPropertyReader.getProperties("sqlite-config.properties");

        setSqliteName(properties.getProperty("SQLITE_NAME"));
        setSqliteVer(Integer.parseInt(properties.getProperty("SQLITE_VER")));

        setServerVersion(Integer.parseInt(properties.getProperty("SERVER_VERSION")));



    }


    public Integer getServerVersion() {
        return serverVersion;
    }

    public Integer getSqliteVer() {
        return sqliteVer;
    }

    public String getSqliteName() {
        return sqliteName;
    }

    public void setServerVersion(Integer serverVersion) {
        this.serverVersion = serverVersion;
    }


    public void setSqliteName(String sqliteName) {
        this.sqliteName = sqliteName;
    }


    public void setSqliteVer(Integer sqliteVer) {
        this.sqliteVer = sqliteVer;
    }



}
