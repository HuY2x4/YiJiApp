package com.example.hyx.appyiji;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hyx.appyiji.ReClass.MyDatebaseHelper;
import com.example.hyx.appyiji.Utils.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.annotation.ContentView;
import com.example.hyx.appyiji.Utils.annotation.ViewInject;
import com.example.hyx.appyiji.Utils.useless.copydb;

/**
 * Created by hyx on 2018/11/22.
 */
@ContentView(value = R.layout.activity_main_test)
public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.test_crate) private Button crete;
    @ViewInject(R.id.test_insert) private Button insert;
    @ViewInject(R.id.test_select) private Button select;
    @ViewInject(R.id.test_copy) private Button copy;
    private MyDatebaseHelper datebase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        crete.setOnClickListener(this);
        insert.setOnClickListener(this);
        select.setOnClickListener(this);
        copy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test_crate:
                datebase=new MyDatebaseHelper(this,"YiJiDateTest.db",null,1);
                break;
            case R.id.test_copy:
                copydb.copydbmethod();
                break;
            case R.id.test_insert:
                SQLiteDatabase db =datebase.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("image",666);
                db.insert("outRecord",null,values);
                values.clear();
                break;
            case R.id.test_select:
                SQLiteDatabase db_select=datebase.getReadableDatabase();
                Cursor cusor=db_select.query("outRecord",null,null,null,null,null,null);
                if(cusor.moveToFirst()){
                    Log.e("aaaa","cusor:");
                    do {
                        Log.e("aaaa","id:"+cusor.getInt(cusor.getColumnIndex("id")));
                        Log.e("aaaa","image:"+cusor.getInt(cusor.getColumnIndex("image")));
                        Log.e("aaaa","data:"+cusor.getInt(cusor.getColumnIndex("data")));


                    }while(cusor.moveToNext());
                }
                break;
        }

    }
}
