package com.example.hyx.appyiji.Activity.MainView.Fragment.JZFram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hyx.appyiji.Activity.MainView.Fragment.JZFram.ViewHolder.itemViewHolder;
import com.example.hyx.appyiji.Activity.MainView.Fragment.JZFram.ViewHolder.titleViewHolder;
import com.example.hyx.appyiji.Activity.MainView.Adapter.GroundRecyclerAdapter.GroupItemDecoration;
import com.example.hyx.appyiji.Activity.MainView.Adapter.GroundRecyclerAdapter.GroupRecyclerAdapter;
import com.example.hyx.appyiji.Activity.MainView.Adapter.GroundRecyclerAdapter.OnChildClickListener;
import com.example.hyx.appyiji.Model.Record;
import com.example.hyx.appyiji.Model.Records;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DAO.RecordDaoImpl;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DBManager;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.MyDatebaseHelper;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by hyx on 2018/11/4.
 */

public class JZFragment extends Fragment implements OnChildClickListener {
    private List<Record> items_list_in ;
    private List<Record> items_list_out ;
    private List<Records> items;
    private RecyclerView recyclerView;
    private MyDatebaseHelper datebase;
    private RecordDaoImpl recordDao;
    GroupRecyclerAdapter<Records, titleViewHolder, itemViewHolder> recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_frm_jizhang, container, false);
        datebase= DBManager.getDBHelp(this.getContext());
        recordDao=new RecordDaoImpl();
        initItems();
        final LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        recyclerView = view.findViewById(R.id.main_recycler );   //用侧滑删除动画之前
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new GroupRecyclerAdapter<Records, titleViewHolder, itemViewHolder>(items) {

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

                                Record item=items.get(groupPosition).getItem().get(childPosition);
                                if(groupPosition==0){
                                    recordDao.deleteRecord(item.getContent(),item.getData(),item.getCost(),DBManager.INRECORD);
                                }
                                else{
                                    recordDao.deleteRecord(item.getContent(),item.getData(),item.getCost(),DBManager.OUTRECORD);
                                }

                                items.get(groupPosition).getItem().remove(childPosition);
                                recyclerAdapter.update(items);

                            }
                        });

                        holder.update(getGroup(groupPosition).getItem().get(childPosition));

                    }

                    @Override
                    protected int getChildCount(Records group) {
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
        items=new ArrayList<Records>();
        initItem_In();
        initItem_Out();
        Records items_in=new Records();
        Records items_out=new Records();
        items_in.setTitle(" 收入");
        items_in.setItem(items_list_in);
        items_out.setTitle(" 支出");
        items_out.setItem(items_list_out);
        items.add(items_in);
        items.add(items_out);

    }


    public void initItem_In(){
        items_list_in=recordDao.getAllRecord(this.getContext(),DBManager.INRECORD);
        items_list_in.add(new Record( R.drawable.ic_main_list_give,"【初始数据】父母援助","2018-11-4","666"));
        items_list_in.add(new Record( R.drawable.ic_main_list_work,"【初始数据】兼职","2018-11-4","66"));

    }

    public void initItem_Out(){
        items_list_out=recordDao.getAllRecord(this.getContext(),DBManager.OUTRECORD);
        items_list_out.add(new Record( R.drawable.ic_main_list_car,"【初始数据】交通费用","2018-11-4","-20"));
        items_list_out.add(new Record( R.drawable.ic_main_list_eat,"【初始数据】吃晚饭","2018-11-4","-11"));
        items_list_out.add(new Record( R.drawable.ic_main_list_eat,"【初始数据】吃夜宵","2018-11-4","-8"));
        items_list_out.add(new Record( R.drawable.ic_main_list_eat,"【初始数据】吃早饭","2018-11-4","-9"));
        items_list_out.add(new Record( R.drawable.ic_main_list_eat,"【初始数据】吃午饭","2018-11-4","-16"));
        items_list_out.add(new Record( R.drawable.ic_main_list_eat,"【初始数据】吃下午饭","2018-11-4","-10"));
        items_list_out.add(new Record( R.drawable.ic_main_list_eat,"【初始数据】吃晚饭","2018-11-4","-10"));
        items_list_out.add(new Record( R.drawable.ic_main_list_car,"【初始数据】坐车吃宵夜","2018-11-4","-15"));
        items_list_out.add(new Record( R.drawable.ic_main_list_car,"【初始数据】坐车回来","2018-11-4","-15"));
    }


    @Override
    public void onChildClick(View itemView, int groupPosition, int childPosition) {
        String mes=groupPosition+"+"+childPosition;
        Toast.makeText(this.getContext(),mes,Toast.LENGTH_SHORT).show();
    }
}
