package com.example.hyx.appyiji.welcomeView;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyx.appyiji.LoginActivity;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Utils.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyx on 2018/11/30.
 */

public class welcomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    @ViewInject(R.id.welcome_enter)  private TextView enter;
    private ViewPager viewPager;
    private com.example.hyx.appyiji.welcomeView.welcomeViewPagerAdapter welcomeViewPagerAdapter;
    private List<View> views;
    private ImageView[] imgs;
    private  int[] ids={R.id.welcome_point1,R.id.welcome_point2,R.id.welcome_point3};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ViewInjectUtils.inject(this);
        initView();
        initImgs();
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(welcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<>();
        views.add(inflater.inflate(R.layout.activity_welcome_page1,null));
        views.add(inflater.inflate(R.layout.activity_welcome_page2,null));
        views.add(inflater.inflate(R.layout.activity_welcome_page3,null));
        viewPager = (ViewPager) findViewById(R.id.welcome_viewPage);
        welcomeViewPagerAdapter =new welcomeViewPagerAdapter(this,views);
        viewPager.setAdapter(welcomeViewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
    }

    private void initImgs(){
        imgs=new ImageView[views.size()];
        for(int i=0;i<views.size();i++){
            imgs[i]=(ImageView) findViewById(ids[i]);
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<ids.length;i++){
            if(position==i){
                imgs[i].setImageResource(R.drawable.ic_circle_select);
            }
            else{
                imgs[i].setImageResource(R.drawable.ic_circle_default);
            }
        }
        if(position==2){
            enter.setVisibility(View.VISIBLE);
        }
        else{
            enter.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
