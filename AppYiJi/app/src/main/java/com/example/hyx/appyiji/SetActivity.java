package com.example.hyx.appyiji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;



import com.example.hyx.appyiji.ReClass.NoScrollViewPager;
import com.example.hyx.appyiji.Utils.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.annotation.ContentView;
import com.example.hyx.appyiji.Utils.annotation.ViewInject;

/**
 * Created by hyx on 2018/11/21.
 */
@ContentView(value = R.layout.activity_main_set)
public class SetActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.main_set_yao) private Switch yao;
    @ViewInject(R.id.main_set_moon) private Switch moon;
    @ViewInject(R.id.main_set_clock) private Switch clock;
    @ViewInject(R.id.main_set_clear) private Button clear;
    @ViewInject(R.id.main_set_toolbar) private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ViewInjectUtils.inject(this);
        yao.setOnClickListener(this);
        moon.setOnClickListener(this);
        clock.setOnClickListener(this);
        clear.setOnClickListener(this);

        SharedPreferences pref=getSharedPreferences("YiJidata",MODE_PRIVATE);
        yao.setChecked(pref.getBoolean("yao",true));
        moon.setChecked(pref.getBoolean("moon",false));
        clock.setChecked(pref.getBoolean("clock",false));
        Log.e("aaaa","moon c:"+moon.isChecked());
        if(moon.isChecked()){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            toolbar.setBackgroundResource(R.drawable.back_main_head_night);
        }
        else{
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            toolbar.setBackground(getResources().getDrawable(R.drawable.back_main_head));
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_set_yao:
                SharedPreferences.Editor editor_yao=getSharedPreferences("YiJidata",MODE_PRIVATE).edit();
                editor_yao.putBoolean("yao",yao.isChecked());
                editor_yao.apply();
                break;
            case R.id.main_set_moon:
                SharedPreferences.Editor editor_moon=getSharedPreferences("YiJidata",MODE_PRIVATE).edit();
                editor_moon.putBoolean("moon",moon.isChecked());
                Log.e("aaaa","moon 写入share库："+moon.isChecked());
                editor_moon.apply();
                if(moon.isChecked()){
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }
                else{
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
                break;
            case R.id.main_set_clock:
                SharedPreferences.Editor editor_clock=getSharedPreferences("YiJidata",MODE_PRIVATE).edit();
                editor_clock.putBoolean("clock",clock.isChecked());
                editor_clock.apply();
                break;
            case R.id.main_set_clear:
                //假装取数据
                SharedPreferences pref=getSharedPreferences("YiJidata",MODE_PRIVATE);
                Log.e("aaaa","SharedPreferences  moon:"+pref.getBoolean("moon",false)+"  yao:"+pref.getBoolean("yao",false));
                break;
        }
    }
}
