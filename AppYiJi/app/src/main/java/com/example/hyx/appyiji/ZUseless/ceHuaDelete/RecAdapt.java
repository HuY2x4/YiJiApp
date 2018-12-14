package com.example.hyx.appyiji.ZUseless.ceHuaDelete;

import android.content.Context;
import android.view.View;

import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Model.Record;

import java.util.List;



/**
 * Created by hyx on 2018/11/15.
 */

public class RecAdapt extends BaseRecyclerViewAdapter<Record> {//RecyclerView.Adapter<RecAdapt.MyHolder>
//    List<Record> items;
//    private final int resourceId;
    private OnDeleteClickLister mDeleteClickListener;

    public RecAdapt(Context context, List<Record> items){
        super(context, items, R.layout.activity_main_list);
        //this.items=items;
       // resourceId = textViewResourceId;
    }


    @Override
    protected void onBindData(RecyclerViewHolder holder, Record bean, int position) {
//        View view = holder.getView(R.id.main_list_delete);
//        view.setTag(position);
//        if (!view.hasOnClickListeners()) {
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (mDeleteClickListener != null) {
//                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
//                    }
//                }
//            });
//        }
//        ((ImageView) holder.getView(R.id.main_list_img)).setBackgroundResource(bean.getImage());
//        ((TextView) holder.getView(R.id.main_list_content)).setText(bean.getContent());
//        ((TextView) holder.getView(R.id.main_list_cost)).setText(bean.getCost());
//        ((TextView) holder.getView(R.id.main_list_data)).setText(bean.getData());
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
}

/*@Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resourceId,parent,false);       //将之前写好的list_view封装到一个View中
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        View view = holder.getView(R.id.main_list_delete);
        view.setTag(position);
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                }
            });
        }
        Record item = items.get(position); // 获取当前项的Fruit实例
        holder.image.setImageResource(item.getImage());//为图片视图设置图片资源
        holder.content.setText(item.getContent());
        holder.data.setText(item.getData());
        holder.cost.setText(item.getCost());
    }



    @Override
    public int getItemCount() {
        return items.size();
    }


    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
    class MyHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        ImageView image;
        TextView content;
        TextView data;
        TextView cost;
        public MyHolder(View itemView) {
            super(itemView);
            this.mViews = new SparseArray<>();
             image = (ImageView) itemView.findViewById(R.id.main_list_img);//获取该布局内的图片视图
             content = (TextView) itemView.findViewById(R.id.main_list_content);//获取该布局内的文本视图
             data = (TextView) itemView.findViewById(R.id.main_list_data);//获取该布局内的文本视图
             cost = (TextView) itemView.findViewById(R.id.main_list_cost);//获取该布局内的文本视图
        }

        View getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }

    }*/