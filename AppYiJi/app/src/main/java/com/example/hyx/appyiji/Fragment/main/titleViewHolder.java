package com.example.hyx.appyiji.Fragment.main;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.model.main_list_items;

/**
 * Created by hyx on 2018/11/19.
 */

public class titleViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleView;
    titleViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.main_list_title);


    }
    void update(main_list_items items) {
        titleView.setText(items.getTitle());
        titleView.setTextSize(23);

    }
}
