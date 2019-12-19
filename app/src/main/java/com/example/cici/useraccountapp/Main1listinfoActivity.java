package com.example.cici.useraccountapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main1listinfoActivity extends ActionBarActivity {

    TextView appname,account,password,phone,email,other;
    String intentstring;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1listinfo);

        appname=(TextView)findViewById(R.id.appname);
        account=(TextView)findViewById(R.id.account);
        password=(TextView)findViewById(R.id.password);
        phone=(TextView)findViewById(R.id.phone);
        email=(TextView)findViewById(R.id.email);
        other=(TextView)findViewById(R.id.other);
        back=(ImageView)findViewById(R.id.back);

        intentstring=getIntent().getStringExtra("intent_string");
        queryDate(null);//搜索数据并显示

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void queryDate(View view) {
        DBHelper dbHelper = new DBHelper(this,2);
        //1.得到连接
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        String selection="appname=?";
        String[] selectionArgs={intentstring};
        //2.执行query select * from person
        Cursor cursor =  sqLiteDatabase.query("user", null, selection, selectionArgs, null, null, null);

        Log.d("debug",intentstring);
        cursor.moveToNext();
        // appname
        String sappname = cursor.getString(cursor.getColumnIndex("appname"));
        //account
        String saccount=cursor.getString(cursor.getColumnIndex("account"));
        //password
        String spassword=cursor.getString(cursor.getColumnIndex("password"));
        // phone
        String sphone = cursor.getString(cursor.getColumnIndex("phone"));
        //email
        String semail = cursor.getString(cursor.getColumnIndex("email"));
        //other
        String sother = cursor.getString(cursor.getColumnIndex("other"));

        appname.setText(sappname);
        account.setText(saccount);
        password.setText(spassword);
        phone.setText(sphone);
        email.setText(semail);
        other.setText(sother);

        //3.关闭连接
        cursor.close();
        sqLiteDatabase.close();

    }
}
