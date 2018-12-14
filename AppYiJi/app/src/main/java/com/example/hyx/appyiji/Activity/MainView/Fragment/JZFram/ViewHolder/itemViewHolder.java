package com.example.hyx.appyiji.Activity.MainView.Fragment.JZFram.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Model.Record;

/**
 * Created by hyx on 2018/11/19.
 */

public class itemViewHolder extends RecyclerView.ViewHolder {

    private final ImageView image ;
    private final TextView content ;
    private final TextView data ;
    private final TextView cost;
    public LinearLayout mylayout;
    public RelativeLayout rv_delete;
    public itemViewHolder(View view) {
        super(view);
        image = (ImageView) view.findViewById(R.id.main_list_img);//获取该布局内的图片视图
        content = (TextView) view.findViewById(R.id.main_list_content);//获取该布局内的文本视图
        data = (TextView) view.findViewById(R.id.main_list_data);//获取该布局内的文本视图
        cost = (TextView) view.findViewById(R.id.main_list_cost);//获取该布局内的文本视图
        mylayout = (LinearLayout) view.findViewById(R.id.main_list_MyL);//获取该布局内的文本视图
        rv_delete = (RelativeLayout) view.findViewById(R.id.main_list_delete);//获取该布局内的文本视图
    }

    public void update(Record item) {
        image.setImageResource(item.getImage());
        content.setText(item.getContent());
        data.setText(item.getData());
        cost.setText(item.getCost());
        if(item.getCost().startsWith("-")){
            cost.setTextColor(0xffde6249);//浅红色
        }
        else{
            cost.setTextColor(0xff90d78b);//浅绿色
        }
        if(item.getCost().equals("0")){
            cost.setTextColor(0xff778899);//灰色
        }

    }
}
