package com.example.hyx.appyiji.Fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hyx.appyiji.Adapter.ceHuaDelete.RecAdapt;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.model.main_list_item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hyx on 2018/11/4.
 */

public class TJFragment extends Fragment {
    private List<main_list_item> items_test = new ArrayList<main_list_item>();
    private RecyclerView listview_test;
    private Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_main_frm_tongji, container, false);
//        listview_test = view.findViewById(R.id.main_listview_test );
//        initItem_test();
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
//        listview_test.setLayoutManager(layoutManager);
//        //设置LayoutManager
//
//        RecAdapt adapter_test = new RecAdapt( R.layout.activity_main_list_in, items_test);
//        listview_test.setAdapter(adapter_test);

        return null;
    }



    public void initItem_test(){
        main_list_item item1=new main_list_item( R.drawable.ic_main_list_car,"交通费用","2018-11-4","20");
        main_list_item item2=new main_list_item( R.drawable.ic_main_list_eat,"吃晚饭","2018-11-4","11");

        items_test.add(item1);
        items_test.add(item2);

    }}