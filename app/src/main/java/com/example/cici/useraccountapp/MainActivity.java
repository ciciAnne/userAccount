package com.example.cici.useraccountapp;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    private TabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabHost=getTabHost();
        mTabHost.setup();

        TabHost.TabSpec tabSpec1=mTabHost.newTabSpec("tab1").setIndicator("管理").
                setContent(new Intent(this, Main1Activity.class));
        TabHost.TabSpec tabSpec2=mTabHost.newTabSpec("tab2").setIndicator("密码器").
                setContent(new Intent(this, Main2Activity.class));
        TabHost.TabSpec tabSpec3=mTabHost.newTabSpec("tab3").setIndicator("用户").
                setContent(new Intent(this, Main3Activity.class));
        mTabHost.addTab(tabSpec1);
        mTabHost.addTab(tabSpec2);
        mTabHost.addTab(tabSpec3);
    }
}
