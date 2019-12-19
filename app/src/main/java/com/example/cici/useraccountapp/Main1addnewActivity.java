package com.example.cici.useraccountapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.Toast;

public class Main1addnewActivity extends ActionBarActivity {

    EditText appname,account,password,phone,email,other;
    String sappname,saccount,spassword,sphone,semail,sother;
    Button cancle,yes;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1addnew);

        appname=(EditText)findViewById(R.id.appname);
        account=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        other=(EditText)findViewById(R.id.other);
        back=(ImageView)findViewById(R.id.back);

        cancle=(Button)findViewById(R.id.cancle);
        yes=(Button)findViewById(R.id.yes);

        back.setOnClickListener(new backClick());
        cancle.setOnClickListener(new backClick());
        yes.setOnClickListener(new insertClick());

        //获取密码器页的生成的密码
        String intentstring=getIntent().getStringExtra("intent_string");
        password.setText(intentstring);
    }

    class backClick implements View.OnClickListener{
        @Override
        public void onClick(View view){
            //清空数据
            appname.setText("");
            account.setText("");
            password.setText("");
            phone.setText("");
            email.setText("");
            other.setText("");
            //退出
            finish();
        }
    }

    class insertClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            sappname=appname.getText().toString();
            saccount=account.getText().toString();
            spassword=password.getText().toString();
            sphone=phone.getText().toString();
            semail=email.getText().toString();
            sother=other.getText().toString();

            if(sappname.equals("")||saccount.equals("")||spassword.equals("")){
                Toast.makeText(Main1addnewActivity.this, "前三项不能为空", Toast.LENGTH_SHORT).show();
            }
            else{
                insertDate(view);
                //清空数据并退出
                appname.setText("");
                account.setText("");
                password.setText("");
                phone.setText("");
                email.setText("");
                other.setText("");

                finish();
                //Intent intent=new Intent(Main1addnewActivity.this,Main1listActivity.class);
                //startActivity(intent);

            }
        }
    }

    public void insertDate(View view){
        DBHelper dbHelper = new DBHelper(this,2);
        //1.得到连接
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        //2.执行insert
        ContentValues values = new ContentValues();
        values.put("appname",sappname);
        values.put("account",saccount);
        values.put("password",spassword);
        values.put("phone",sphone);
        values.put("email",semail);
        values.put("other",sother);
        //返回插入的id
        /**
         * 第一个参数：表名
         * 第二个参数：null
         * 第三个参数：传入的HashMap值
         */
        long id = sqLiteDatabase.insert("user", null, values);

        //3.关闭连接
        sqLiteDatabase.close();

        //4.提示
        Toast.makeText(this,"id= "+id, Toast.LENGTH_SHORT).show();
    }
}
