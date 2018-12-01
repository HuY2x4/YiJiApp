package com.example.hyx.appyiji.Fragment.main_add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hyx.appyiji.MainActivity;
import com.example.hyx.appyiji.MainAddActivity;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.ReClass.MyRadioGroup;
import com.example.hyx.appyiji.SetActivity;
import com.example.hyx.appyiji.TestActivity;
import com.example.hyx.appyiji.Utils.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.annotation.ViewInject;

/**
 * Created by hyx on 2018/11/20.
 */

public class outFragment extends Fragment {

    private MyRadioGroup outGroup;
    private RadioButton eat;
    private RadioButton clothes;
    private RadioButton life;
    private RadioButton car;
    private RadioButton book;
    private RadioButton game;

    public static String curName;
    public static int curImgId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//       ViewInjectUtils.inject(this.getActivity());   //坑爹玩意，找这个bug找了两个小时，就是多加了这句话，错都不报一下
        View view = inflater.inflate(R.layout.activity_main_add_frm_out, container, false);
        outGroup=(MyRadioGroup)view.findViewById(R.id.main_add_out_group);
        eat=(RadioButton)view.findViewById(R.id.main_add_out_eat);
        clothes=(RadioButton)view.findViewById(R.id.main_add_out_clothes);
        life=(RadioButton)view.findViewById(R.id.main_add_out_life);
        car=(RadioButton)view.findViewById(R.id.main_add_out_car);
        book=(RadioButton)view.findViewById(R.id.main_add_out_book);
        game=(RadioButton)view.findViewById(R.id.main_add_out_game);
//        life.setChecked(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        outGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_add_out_eat:
                        curName="餐饮食品";
                        curImgId=R.drawable.ic_add_out_eat_black_24dp;
                        break;
                    case R.id.main_add_out_clothes:
                        curName="衣服饰品";
                        curImgId=R.drawable.ic_add_out_clothes_black_24dp;
                        break;
                    case R.id.main_add_out_life:
                        curName="居家生活";
                        curImgId=R.drawable.ic_add_out_life_black_24dp;
                        break;
                    case R.id.main_add_out_car:
                        curName="行车交通";
                        curImgId=R.drawable.ic_add_out_car_black_24dp;
                        break;
                    case R.id.main_add_out_book:
                        curName="文化教育";
                        curImgId=R.drawable.ic_add_out_book_black_24dp;
                        break;
                    case R.id.main_add_out_game:
                        curName="休闲娱乐";
                        curImgId=R.drawable.ic_add_out_game_black_24dp;
                        break;


                }
            }
        });
    }
}
