package com.example.hyx.appyiji.Model;

import java.util.List;

/**
 * Created by hyx on 2018/11/16.
 */

public class Records {
    private String title;
    private List<Record> item;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Record> getItem() {
        return item;
    }

    public void setItem(List<Record> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Records{" +
                "title='" + title + '\'' +
                ", item=" + item +
                '}';
    }
}
