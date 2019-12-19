package com.example.cici.useraccountapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by cici on 2019/12/18.
 */
public class DBHelper extends SQLiteOpenHelper{

    public DBHelper(Context context,int version){
        super(context,"mysql.db",null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        Log.e("TAG", "onCreate");

        String sql="create table user(_id integer primary key autoincrement," +
                "appname text not null,account text not null,password text not null," +
                "phone text,email text,other text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1){
        Log.d("TAG", "onUpgrade");
    }
}
