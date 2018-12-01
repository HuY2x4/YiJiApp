package com.example.hyx.appyiji.Fragment.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hyx.appyiji.Adapter.GroundRecyclerAdapter.GroupItemDecoration;
import com.example.hyx.appyiji.Adapter.GroundRecyclerAdapter.GroupRecyclerAdapter;
import com.example.hyx.appyiji.Adapter.GroundRecyclerAdapter.OnChildClickListener;
import com.example.hyx.appyiji.MainActivity;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.ReClass.MyDatebaseHelper;
import com.example.hyx.appyiji.model.main_list_item;
import com.example.hyx.appyiji.model.main_list_items;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by hyx on 2018/11/4.
 */

public class JZFragment extends Fragment implements OnChildClickListener {
    private List<main_list_item> items_list_in ;
    private List<main_list_item> items_list_out ;
    private List<main_list_items> items;
    private RecyclerView recyclerView;
    private MyDatebaseHelper datebase;
    GroupRecyclerAdapter<main_list_items, titleViewHolder, itemViewHolder> recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_frm_jizhang, container, false);
        datebase=new MyDatebaseHelper(this.getContext(),"YiJiDate.db",null,1);
        initItems();
        final LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        recyclerView = view.findViewById(R.id.main_recycler );   //用侧滑删除动画之前
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new GroupRecyclerAdapter<main_list_items, titleViewHolder, itemViewHolder>(items) {

                    @Override
                    protected titleViewHolder onCreateGroupViewHolder(ViewGroup parent) {
                        return new titleViewHolder(layoutInflater.inflate(R.layout.activity_main_list_title, parent, false));
                    }

                    @Override
                    protected itemViewHolder onCreateChildViewHolder(ViewGroup parent) {
                        return new itemViewHolder(layoutInflater.inflate(R.layout.activity_main_list, parent, false));
                    }

                    @Override
                    protected void onBindGroupViewHolder(titleViewHolder holder, int groupPosition) {
                        holder.update(getGroup(groupPosition));
                    }

                    @Override
                    protected void onBindChildViewHolder(itemViewHolder holder, final int groupPosition, final int childPosition) {
                        holder.rv_delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                /*
                                *点击按钮后回到初始布局
                                 */

                                /*
                                 *从dbsqlite中删除数据的代码
                                 */
                                SQLiteDatabase db =datebase.getWritableDatabase();
                                main_list_item item=items.get(groupPosition).getItem().get(childPosition);
                                if(groupPosition==0){
                                    db.delete("inRecord","content=? and data=? and cost=?",new String[]{
                                            item.getContent(),item.getData(),item.getCost()
                                    });
                                }
                                else{
                                    db.delete("outRecord","content=? and data=? and cost=?",new String[]{
                                            item.getContent(),item.getData(),item.getCost()
                                    });
                                }


                                items.get(groupPosition).getItem().remove(childPosition);

                                recyclerAdapter.update(items);

                            }
                        });

                        holder.update(getGroup(groupPosition).getItem().get(childPosition));

                    }

                    @Override
                    protected int getChildCount(main_list_items group) {
                        return group.getItem().size();
                    }

        };
        recyclerView.setAdapter(recyclerAdapter);

        GroupItemDecoration decoration = new GroupItemDecoration(recyclerAdapter);
        decoration.setGroupDivider(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_height_16_dp, null));
        decoration.setTitleDivider(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_height_1_px, null));
        decoration.setChildDivider(ResourcesCompat.getDrawable(getResources(), R.drawable.divider_white_header, null));
        recyclerView.addItemDecoration(decoration);
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }

    public void initItems(){
        items=new ArrayList<main_list_items>();
        initItem_In();
        initItem_Out();
        main_list_items items_in=new main_list_items();
        main_list_items items_out=new main_list_items();
        items_in.setTitle(" 收入");
        items_in.setItem(items_list_in);
        items_out.setTitle(" 支出");
        items_out.setItem(items_list_out);
        items.add(items_in);
        items.add(items_out);

    }


    public void initItem_In(){
        items_list_in=new ArrayList<main_list_item>();
        SQLiteDatabase db=datebase.getWritableDatabase();
        Cursor cusor=db.query("inRecord",null,null,null,null,null,null);
        if(cusor.moveToFirst()){
           do {
               main_list_item itemAdd=new main_list_item( cusor.getInt(cusor.getColumnIndex("image")),
                       cusor.getString(cusor.getColumnIndex("content")),
                       cusor.getString(cusor.getColumnIndex("data")),
                       cusor.getString(cusor.getColumnIndex("cost")));
               items_list_in.add(itemAdd);
           }while(cusor.moveToNext());
       }Collections.reverse(items_list_in);
        main_list_item item1=new main_list_item( R.drawable.ic_main_list_give,"【初始数据】父母援助","2018-11-4","666");
        main_list_item item2=new main_list_item( R.drawable.ic_main_list_work,"【初始数据】兼职","2018-11-4","66");
        items_list_in.add(item1);
        items_list_in.add(item2);

    }

    public void initItem_Out(){
        items_list_out=new ArrayList<main_list_item>();
        SQLiteDatabase db=datebase.getReadableDatabase();
        Cursor cusor=db.query("outRecord",null,null,null,null,null,null);
        if(cusor.moveToFirst()){
            do {
                main_list_item itemAdd=new main_list_item( cusor.getInt(cusor.getColumnIndex("image")),
                        cusor.getString(cusor.getColumnIndex("content")),
                        cusor.getString(cusor.getColumnIndex("data")),
                        cusor.getString(cusor.getColumnIndex("cost")));

                items_list_out.add(itemAdd);
            }while(cusor.moveToNext());
        }
        Collections.reverse(items_list_out);
        main_list_item item1=new main_list_item( R.drawable.ic_main_list_car,"【初始数据】交通费用","2018-11-4","-20");
        main_list_item item2=new main_list_item( R.drawable.ic_main_list_eat,"【初始数据】吃晚饭","2018-11-4","-11");
        main_list_item item3=new main_list_item( R.drawable.ic_main_list_eat,"【初始数据】吃夜宵","2018-11-4","-8");
        main_list_item item4=new main_list_item( R.drawable.ic_main_list_eat,"【初始数据】吃早饭","2018-11-4","-9");
        main_list_item item5=new main_list_item( R.drawable.ic_main_list_eat,"【初始数据】吃午饭","2018-11-4","-16");
        main_list_item item6=new main_list_item( R.drawable.ic_main_list_eat,"【初始数据】吃下午饭","2018-11-4","-10");
        main_list_item item7=new main_list_item( R.drawable.ic_main_list_eat,"【初始数据】吃晚饭","2018-11-4","-10");
        main_list_item item8=new main_list_item( R.drawable.ic_main_list_car,"【初始数据】坐车吃宵夜","2018-11-4","-15");
        main_list_item item9=new main_list_item( R.drawable.ic_main_list_car,"【初始数据】坐车回来","2018-11-4","-15");
        items_list_out.add(item1);
        items_list_out.add(item2);
        items_list_out.add(item3);
        items_list_out.add(item4);
        items_list_out.add(item5);
        items_list_out.add(item6);
        items_list_out.add(item7);
        items_list_out.add(item8);
        items_list_out.add(item9);
    }


    @Override
    public void onChildClick(View itemView, int groupPosition, int childPosition) {
        String mes=groupPosition+"+"+childPosition;
        Toast.makeText(this.getContext(),mes,Toast.LENGTH_SHORT).show();
    }
}
