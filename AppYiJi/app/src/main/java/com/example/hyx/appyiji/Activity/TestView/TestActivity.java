package com.example.hyx.appyiji.Activity.TestView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.MyDatebaseHelper;
import com.example.hyx.appyiji.Utils.ViewInject.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.ViewInject.annotation.ContentView;
import com.example.hyx.appyiji.Utils.ViewInject.annotation.ViewInject;
import com.example.hyx.appyiji.ZUseless.copydb;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by hyx on 2018/11/22.
 */
@ContentView(value = R.layout.activity_main_test)
public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.test_kai)  private Button kai;
    @ViewInject(R.id.test_guan)  private Button guan;
    private AVLoadingIndicatorView loading;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        loading = (AVLoadingIndicatorView) findViewById(R.id.test_loading);
        loading.setIndicator("BallPulseIndicator");
        kai.setOnClickListener(this);
        guan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test_kai:
                loading.setVisibility(View.VISIBLE);
                break;
            case R.id.test_guan:
                loading.setVisibility(View.INVISIBLE);
                break;
        }

    }
}
