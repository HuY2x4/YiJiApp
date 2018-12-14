//package com.example.hyx.appyiji.Adapter.useless;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.hyx.appyiji.R;
//import com.example.hyx.appyiji.model.Record;
//
//import java.util.List;
//
///**
// * Created by hyx on 2018/11/15.
// */
//
//public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyHolder> {
//    List<Record> items;
//    int layoutId;
//
//    public RecAdapter(List<Record> items,int layoutId) {
//        this.items = items;
//        this.layoutId = layoutId;
//    }
//
//    @Override
//    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
//        //将之前写好的list_view封装到一个View中
//        MyHolder holder = new MyHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyHolder holder, int position) {
//        Record item = items.get(position);
//        holder.image.setImageResource(item.getImage());//为图片视图设置图片资源
//        holder.content.setText(item.getContent());
//        holder.data.setText(item.getData());
//        holder.cost.setText(item.getCost());
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    class MyHolder extends RecyclerView.ViewHolder {
//
//        ImageView image;
//        TextView content;
//        TextView data;
//        TextView cost;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//
//            image = (ImageView) itemView.findViewById(R.id.main_list_img);//获取该布局内的图片视图
//            content = (TextView) itemView.findViewById(R.id.main_list_content);//获取该布局内的文本视图
//            data = (TextView) itemView.findViewById(R.id.main_list_data);//获取该布局内的文本视图
//            cost = (TextView) itemView.findViewById(R.id.main_list_cost);//获取该布局内的文本视图
//        }
//
//
//    }
//}
//
