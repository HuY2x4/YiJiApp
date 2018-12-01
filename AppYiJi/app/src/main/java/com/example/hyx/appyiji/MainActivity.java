package com.example.hyx.appyiji;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hyx.appyiji.Adapter.MyPagerAdapter;
import com.example.hyx.appyiji.Fragment.main.JZFragment;
import com.example.hyx.appyiji.Fragment.main.TJFragment;
import com.example.hyx.appyiji.ReClass.NoScrollViewPager;
import com.example.hyx.appyiji.Utils.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hyx on 2018/11/3.
 */
public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    @ViewInject(R.id.main)  private DrawerLayout drawerLayout;
    @ViewInject(R.id.main_nav) private NavigationView navigationView;
    @ViewInject(R.id.main_menu) private ImageView menu;
    @ViewInject(R.id.main_search) private ImageView search;
    @ViewInject(R.id.main_add) private ImageView add;
    @ViewInject(R.id.main_viewpager) private NoScrollViewPager viewpager;
    @ViewInject(R.id.main_tablayout) private TabLayout tablayout;




    private ImageView touxiang;
    MyPagerAdapter adapter;
    List<Fragment> fragmentList;
    List<String> list_Title;

    private int[] imageRes = {
            R.drawable.selector_tab_create,R.drawable.selector_tab_assessment
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|
//            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|
//            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
//            View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
        if (Build.VERSION.SDK_INT >= 21) {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);
        ViewInjectUtils.inject(this);
        View headerView = navigationView.getHeaderView(0);//获取头布局
        touxiang=(ImageView)headerView.findViewById(R.id.main_head_touxiang) ;

        touxiang.setOnClickListener(this);
        menu.setOnClickListener(this);
        add.setOnClickListener(this);
        search.setOnClickListener(this);



        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new JZFragment());
        fragmentList.add(new TJFragment());
        list_Title.add("记账");
        list_Title.add("统计");
        adapter=new MyPagerAdapter(getSupportFragmentManager(),MainActivity.this,fragmentList,list_Title,imageRes);
        viewpager.setAdapter(adapter);
        viewpager.setNoScroll(true);
        tablayout.setupWithViewPager(viewpager);//此方法就是让tablayout和ViewPager联动
        for (int i = 0; i <tablayout.getTabCount() ; i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

        // 设置导航菜单宽度
        ViewGroup.LayoutParams params = navigationView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels * 3 / 5;
        navigationView.setLayoutParams(params);



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setChecked(true);

                Log.e("aaaa","id:"+R.id.main_nav_set+"  idtrue:"+item.getItemId());
                switch (item.getItemId()){
                    case R.id.main_nav_set:
                        Intent intent_set=new Intent(MainActivity.this,SetActivity.class);
                        startActivity(intent_set);
                        break;
                    case R.id.main_nav_test:
                        Intent intent_test=new Intent(MainActivity.this,TestActivity.class);
                        startActivity(intent_test);
                        break;

                }
                Toast.makeText(MainActivity.this,item.getTitle().toString(),Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_menu:
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
                break;
            case R.id.main_head_touxiang:
                Intent intent_touxiang=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent_touxiang);
                break;
            case R.id.main_add:
                Intent intent_add=new Intent(MainActivity.this,MainAddActivity.class);
                startActivity(intent_add);
                break;
            case R.id.main_search:
                break;
        }

    }


}
