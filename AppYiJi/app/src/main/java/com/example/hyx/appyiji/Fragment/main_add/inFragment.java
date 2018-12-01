package com.example.hyx.appyiji.Fragment.main_add;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hyx.appyiji.MainActivity;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.ReClass.MyRadioGroup;
import com.example.hyx.appyiji.SetActivity;
import com.example.hyx.appyiji.TestActivity;

/**
 * Created by hyx on 2018/11/20.
 */

public class inFragment extends Fragment {
    private MyRadioGroup InGroup;
    private RadioButton parent;
    private RadioButton work;


    public static String curName;
    public static int curImgId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_add_frm_in, container, false);
        InGroup=(MyRadioGroup)view.findViewById(R.id.main_add_in_group);
        parent=(RadioButton)view.findViewById(R.id.main_add_in_parent);
        work=(RadioButton)view.findViewById(R.id.main_add_in_work);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.main_add_in_parent:
                        curName="父母援助";
                        curImgId=R.drawable.ic_add_in_parent_black;
                        break;
                    case R.id.main_add_in_work:
                        curName="兼职所得";
                        curImgId=R.drawable.ic_add_in_word_black;
                        break;



                }
            }
        });
    }

}
