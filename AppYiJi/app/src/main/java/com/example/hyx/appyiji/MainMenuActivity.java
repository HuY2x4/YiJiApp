package com.example.hyx.appyiji;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.hyx.appyiji.Utils.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.annotation.ContentView;
import com.example.hyx.appyiji.Utils.annotation.ViewInject;

/**
 * Created by hyx on 2018/11/3.
 */
@ContentView(value = R.layout.activity_main_head)
public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.main_head_touxiang) private ImageView touxiang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        touxiang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_head_touxiang:
                Intent intent=new Intent(MainMenuActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }

    }
}
