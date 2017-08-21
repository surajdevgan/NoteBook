package com.example.suraj.notebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.net.URI;

/**
 * Created by suraj on 29-04-2017.
 */

public class Util {


    public static final String DATABASE_NAME = "notebook.db"; // name of the database
    public static final int DATABASE_VERSION = 1;
    public static final String TAB_NAME = "note";


    // these are some columns that we need in our able
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DATE = "date";


    private String[] allColumns = {COLUMN_ID,COLUMN_TITLE,COLUMN_MESSAGE,COLUMN_CATEGORY,COLUMN_DATE}; // this is the array of all columns


    public static final String CREATE_TAB_QUERY = "create table note(" +
            "_id integer primary key autoincrement," +
            "title varchar(256)," +
            "message varchar(230),"+
            "category text not null," +
            "date" + ")";



public static final Uri NOTE_URI = Uri.parse("content://com.example.suraj.notebook.mycontentprovider/"+TAB_NAME);


}
