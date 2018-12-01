package com.example.hyx.appyiji.model;

/**
 * Created by hyx on 2018/11/4.
 */

public class main_list_item {
    private int image;
    private String content;
    private String data;
    private String cost;

    public main_list_item(int image,String content,String data,String cost){
        this.image=image;
        this.content=content;
        this.data=data;
        this.cost=cost;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "main_list_item{" +
                "image=" + image +
                ", content='" + content + '\'' +
                ", data='" + data + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
