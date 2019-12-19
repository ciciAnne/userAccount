package com.example.cici.useraccountapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Main1listActivity extends ActionBarActivity {

    private LinearLayout addAccountView;

    @Override
    protected void onResume(){
        super.onResume();

        //将所有动态添加的item放到main1list的LinearLayout容器下
        addAccountView=(LinearLayout)findViewById(R.id.ll_addview);
        addViewItem(null);//添加账户item
        itemClick();

        ImageView add=(ImageView)findViewById(R.id.add);
        ImageView back=(ImageView)findViewById(R.id.back);

        //转到添加页
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main1listActivity.this, Main1addnewActivity.class);
                startActivity(intent);
            }
        });
        //转到上一页
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1list);

    }

    private void addViewItem(View view){
        DBHelper dbHelper = new DBHelper(this,2);
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();//1.得到连接
        //2.执行query select * from user
        Cursor cursor =  sqLiteDatabase.query("user", null, null, null, null, null, null);
        //得到cursor查询到的总记录数
        int count = cursor.getCount();
        addAccountView.removeAllViews();

        while(cursor.moveToNext()){
            View accountView=View.inflate(this,R.layout.item_account,null);//找到item的xml
            // appname
            String sappname = cursor.getString(cursor.getColumnIndex("appname"));
            //account
            String saccount=cursor.getString(cursor.getColumnIndex("account"));

            TextView appname=(TextView)accountView.findViewById(R.id.appname);
            TextView account=(TextView)accountView.findViewById(R.id.account);
            appname.setText(sappname);
            account.setText(saccount);
            addAccountView.addView(accountView);
        }
        cursor.close();
        sqLiteDatabase.close();
        Toast.makeText(this,"共"+count+"项", Toast.LENGTH_SHORT).show();
    }

    private void itemClick(){
        for(int i=0;i<addAccountView.getChildCount();i++){
            final View childAt=addAccountView.getChildAt(i);
            final ImageView bt_arrowright=(ImageView)childAt.findViewById(R.id.arrowright);
            final ImageView bt_cancel=(ImageView)childAt.findViewById(R.id.cancel);
            final TextView appname=(TextView)childAt.findViewById(R.id.appname);
            bt_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DBHelper dbHelper = new DBHelper(Main1listActivity.this,2);
                    //1.得到连接
                    SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
                    String sappname=appname.getText().toString();
                    //执行delete
                    String where = "appname = ?";
                    String[] value = new String[]{ sappname };
                    //返回删除的数量
                    /**
                     * 第一个参数：表名
                     * 第二个参数：删除的where后的语句
                     * 第三个参数：? 所代表的值
                     */
                    int deleteCount = sqLiteDatabase.delete("user", where, value);
                    //3.关闭连接
                    sqLiteDatabase.close();
                    //4.提示
                    Toast.makeText(Main1listActivity.this,"删除"+deleteCount+"项",Toast.LENGTH_SHORT).show();
                    addAccountView.removeView(childAt);
                }
            });
            bt_arrowright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String sappname=appname.getText().toString();
                    Intent intent=new Intent(Main1listActivity.this,Main1listinfoActivity.class);
                    intent.putExtra("intent_string",sappname);
                    startActivity(intent);
                }
            });
        }
    }


}
