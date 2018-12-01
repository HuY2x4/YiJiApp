package com.example.hyx.appyiji.Adapter.useless;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.model.main_list_item;

import java.util.List;

/**
 * Created by hyx on 2018/11/4.
 */

public class itemAdapter extends ArrayAdapter {
    private final int resourceId;

    public itemAdapter(Context context, int textViewResourceId, List<main_list_item> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        main_list_item item = (main_list_item) getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        ImageView image = (ImageView) view.findViewById(R.id.main_list_img);//获取该布局内的图片视图
        TextView content = (TextView) view.findViewById(R.id.main_list_content);//获取该布局内的文本视图
        TextView data = (TextView) view.findViewById(R.id.main_list_data);//获取该布局内的文本视图
        TextView cost = (TextView) view.findViewById(R.id.main_list_cost);//获取该布局内的文本视图

        image.setImageResource(item.getImage());//为图片视图设置图片资源
        content.setText(item.getContent());
        data.setText(item.getData());
        cost.setText(item.getCost());
        return view;
    }
}
