package com.example.hyx.appyiji.Activity.MainView.Adapter.GroundRecyclerAdapter;

/**
 * Created by hyx on 2018/11/25.
 */

import android.view.View;

/**
 * Group 标题被点击的回调事件
 *
 * @author 黄浩杭 (huanghaohang@parkingwang.com)
 * @since 2018-01-09 0.2
 */
public interface OnGroupClickListener {
    /**
     * Callback when the group item was clicked.
     *
     * @param itemView      the itemView of the group item.
     * @param groupPosition the position of the group.
     */
    void onGroupItemClick(View itemView, int groupPosition);
}