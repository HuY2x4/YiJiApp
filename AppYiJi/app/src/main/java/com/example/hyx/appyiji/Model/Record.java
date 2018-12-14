package com.example.hyx.appyiji.Model;

/**
 * Created by hyx on 2018/11/4.
 */

public class Record {
    private int id;
    private int image;
    private String content;
    private String data;
    private String cost;

    public Record(){
    }
    public Record(int image, String content, String data, String cost){
        this.image=image;
        this.content=content;
        this.data=data;
        this.cost=cost;
    }
    public Record(int id,int image, String content, String data, String cost){
        this.id = id;
        this.image=image;
        this.content=content;
        this.data=data;
        this.cost=cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Record{" +
                "image=" + image +
                ", content='" + content + '\'' +
                ", data='" + data + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
