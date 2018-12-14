package com.example.hyx.appyiji.Activity.MainView.Fragment.JZFram.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Model.Records;

/**
 * Created by hyx on 2018/11/19.
 */

public class titleViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleView;
    public titleViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.main_list_title);


    }
    public void update(Records items) {
        titleView.setText(items.getTitle());
        titleView.setTextSize(23);

    }
}
