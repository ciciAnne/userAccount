package com.example.cici.useraccountapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends ActionBarActivity {

    EditText password,fenlei;
    TextView passtext;
    Button createpass,copypass,addnew;
    String newpass;
    int spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        password=(EditText)findViewById(R.id.password);
        fenlei=(EditText)findViewById(R.id.fenlei);
        passtext=(TextView)findViewById(R.id.passtext);
        createpass=(Button)findViewById(R.id.createpass);
        copypass=(Button)findViewById(R.id.copypass);
        addnew=(Button)findViewById(R.id.addnew);

        createpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals("")&&!fenlei.getText().toString().equals("")){
                    spassword=Integer.parseInt(password.getText().toString());
                    final String sfenlei=fenlei.getText().toString();

                    spassword=spassword*(int)(Math.random()*10+1);
                    newpass=spassword+sfenlei;
                    passtext.setText(newpass);
                }
            }
        });

        //新增账号按钮的跳转
        addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!passtext.getText().toString().equals("")){
                    Intent intent=new Intent(Main2Activity.this,Main1addnewActivity.class);
                    intent.putExtra("intent_string",newpass);
                    startActivity(intent);

                    password.setText("");
                    fenlei.setText("");
                    passtext.setText("");
                }
            }
        });

        //复制密码
        copypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!passtext.getText().toString().equals("")){
                    ClipboardManager cm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData mClipData=ClipData.newPlainText("Label",newpass);
                    cm.setPrimaryClip(mClipData);
                    Toast.makeText(Main2Activity.this,"复制成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
