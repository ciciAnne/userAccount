package com.example.cici.useraccountapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends ActionBarActivity {

    EditText account,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        account=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        Button bt1=(Button)findViewById(R.id.bt1);

        bt1.setOnClickListener(new btClick());
    }
    class btClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(account.getText().toString().equals("")||password.getText().toString().equals(""))
            {
                Toast.makeText(SignActivity.this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
            }
            else if(account.getText().toString().equals("admin")&&password.getText().toString().equals("12345678")){
                Intent intent=new Intent(SignActivity.this,MainActivity.class);
                startActivity(intent);
                account.setText("");
                password.setText("");
            }
            else
            {
                Toast.makeText(SignActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
