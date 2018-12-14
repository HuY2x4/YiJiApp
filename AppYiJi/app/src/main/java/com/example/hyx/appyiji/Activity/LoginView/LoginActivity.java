package com.example.hyx.appyiji.Activity.LoginView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.hyx.appyiji.Activity.MainView.MainActivity;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Utils.ViewInject.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.ViewInject.annotation.*;

@ContentView(value = R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewInject(R.id.login_login) Button button_login;
    @ViewInject(R.id.login_zhuce) TextView button_zhuce;
    @ViewInject(R.id.login_userName) EditText edt_userName;
    @ViewInject(R.id.login_password) EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        ViewInjectUtils.inject(this);
        //button_login=(Button)findViewById(R.id.login_login);
        button_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_login:
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
