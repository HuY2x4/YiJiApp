package com.example.hyx.appyiji.model;

import java.util.List;

/**
 * Created by hyx on 2018/11/16.
 */

public class main_list_items {
    private String title;
    private List<main_list_item> item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<main_list_item> getItem() {
        return item;
    }

    public void setItem(List<main_list_item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "main_list_items{" +
                "title='" + title + '\'' +
                ", item=" + item +
                '}';
    }
}
