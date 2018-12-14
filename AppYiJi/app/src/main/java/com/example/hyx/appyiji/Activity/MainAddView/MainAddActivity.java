package com.example.hyx.appyiji.Activity.MainAddView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyx.appyiji.Activity.MainView.MainActivity;
import com.example.hyx.appyiji.Activity.MainAddView.Adapter.MyPagerAdapter_Add;
import com.example.hyx.appyiji.Activity.MainAddView.KongJian.CustomDatePickerDialogFragment;
import com.example.hyx.appyiji.Activity.MainAddView.Fragment.inFragment;
import com.example.hyx.appyiji.Activity.MainAddView.Fragment.outFragment;
import com.example.hyx.appyiji.R;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DAO.RecordDaoImpl;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.DBManager;
import com.example.hyx.appyiji.Utils.SQLiteDatabase.MyDatebaseHelper;
import com.example.hyx.appyiji.Activity.MainAddView.ExtendsClass.MyRadioGroup;
import com.example.hyx.appyiji.Utils.ViewInject.ViewInjectUtils;
import com.example.hyx.appyiji.Utils.ViewInject.annotation.ContentView;
import com.example.hyx.appyiji.Utils.ViewInject.annotation.ViewInject;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hyx on 2018/11/20.
 */
@ContentView(value = R.layout.activity_main_add)
public class MainAddActivity extends AppCompatActivity implements View.OnClickListener,CustomDatePickerDialogFragment.OnSelectedDateListener{
    @ViewInject(R.id.main_add_view) private ViewPager viewpager;
    @ViewInject(R.id.main_add_tab) private TabLayout tablayout;
    @ViewInject(R.id.main_add_dateButton) private Button dateButton;
    @ViewInject(R.id.main_add_dateHS) private TextView dateHM;
    @ViewInject(R.id.main_add_money) private EditText money;
    @ViewInject(R.id.main_add_remark) private EditText remark;
    @ViewInject(R.id.main_add_back) private ImageView back;
    @ViewInject(R.id.main_add_baocun) private Button confirm;
//    @ViewInject(R.id.main_add_out_group) private MyRadioGroup radioGroup;
    private MyRadioGroup radioGroup;

    private MyDatebaseHelper datebase;
    private RecordDaoImpl recordDao;
    View viewPager;
    List<Fragment> fragmentList;
    List<String> list_Title;
    MyPagerAdapter_Add adapter;
    Calendar cal;
    int hour;
    int minute;
    String hm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ViewInjectUtils.inject(this);
//        viewpager=(ViewPager)findViewById(R.id.main_add_view);
//
        datebase=DBManager.getDBHelp(this);
        recordDao=new RecordDaoImpl();
        updateDateButtonText();
        cal = Calendar.getInstance();//日期控件
        hour= cal.get(Calendar.HOUR_OF_DAY);
        minute= cal.get(Calendar.MINUTE);
        if(minute<10){
            hm=Integer.toString(hour)+":0"+Integer.toString(minute);
        }
        else {
            hm=Integer.toString(hour)+":"+Integer.toString(minute);
        }

        dateHM.setText(hm);
        back.setOnClickListener(this);
        dateButton.setOnClickListener(this);
        confirm.setOnClickListener(this);
        money.setInputType(EditorInfo.TYPE_CLASS_NUMBER);

        fragmentList = new ArrayList<>();
        list_Title = new ArrayList<>();
        fragmentList.add(new outFragment());
        fragmentList.add(new inFragment());
        list_Title.add("支出");
        list_Title.add("收入");
        adapter=new MyPagerAdapter_Add(getSupportFragmentManager(),MainAddActivity.this,fragmentList,list_Title);
        viewpager.setAdapter(adapter);

        tablayout.setupWithViewPager(viewpager);//此方法就是让tablayout和ViewPager联动
        for (int i = 0; i <tablayout.getTabCount() ; i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.main_add_dateButton:

                showDatePickDialog();

                break;
            case R.id.main_add_back:
                Intent intent_back=new Intent(MainAddActivity.this,MainActivity.class);
                startActivity(intent_back);
                break;
            case R.id.main_add_baocun:
                String moneystr=money.getText().toString();
                String remarkstr=remark.getText().toString();
                String timeHMstr=dateHM.getText().toString();
                String datestr=dateButton.getText().toString();
                String curName;
                int imgId;
                if(tablayout.getSelectedTabPosition()==0){
                    viewPager = LayoutInflater.from(this).inflate(R.layout.activity_main_add_frm_out, null);
                    radioGroup = (MyRadioGroup)viewPager.findViewById(R.id.main_add_out_group);
                    outFragment outF = (outFragment) adapter.getCurrentObeject();
                    curName=outF.getCurName();
                    imgId=outF.getCurImgId();
                    recordDao.addRecord(this,imgId,curName,datestr+" "+timeHMstr,moneystr,DBManager.OUTRECORD);
                }
                else{
                    viewPager = LayoutInflater.from(this).inflate(R.layout.activity_main_add_frm_in, null);
                    radioGroup = (MyRadioGroup)viewPager.findViewById(R.id.main_add_in_group);
//                    inFragment inF=(inFragment)getSupportFragmentManager().findFragmentById()
                    inFragment inF = (inFragment) adapter.getCurrentObeject();
                    curName=inF.getCurName();
                    imgId=inF.getCurImgId();
                    recordDao.addRecord(this,imgId,curName,datestr+" "+timeHMstr,moneystr,DBManager.INRECORD);
                }

//                radioGroup= (RadioGroup) getSupportFragmentManager().findFragmentById(R.id.far).getView().findViewById(R.id.button);
//                inFragment mf= (inFragment) ((MyPagerAdapter_Add)viewpager.getAdapter()).;
//                String type="";
//                int img=0;
//                boolean braeki=false;
//                for(int i = 0 ;i < radioGroup.getChildCount();i++){
//                    LinearLayout lyou=(LinearLayout)radioGroup.getChildAt(i);
//                    for (int j=0;j<lyou.getChildCount();j++){
//                        RadioButton rb = (RadioButton)lyou.getChildAt(j);
//                        if(rb.isChecked()){
//                            type=rb.getText().toString();
//
//                            braeki=true;
//                            break;
//                        }
//                    }
//                    if (braeki){
//                        break;
//                    }
//                }


                //添加数据到SQLite

//                SQLiteDatabase db =datebase.getWritableDatabase();
//
//                ContentValues values=new ContentValues();
//
//                values.put("image",imgId);
//                values.put("content",curName);
//                values.put("data",datestr+" "+timeHMstr);
//                if(tablayout.getSelectedTabPosition()==0){
//                    moneystr="-"+moneystr;
//                    if(moneystr.equals("-")){
//                        moneystr="0";
//                    }
//                    values.put("cost",moneystr);
//                    db.insert("outRecord",null,values);
//                }
//                else {
//                    if(moneystr.equals("")){
//                        moneystr="0";
//                    }
//                    values.put("cost",moneystr);
//                    db.insert("inRecord",null,values);
//                }
//
//                values.clear();

//                Cursor cusor=db.query("outRecord",null,null,null,null,null,null);
//                if(cusor.moveToFirst()){
//
//                    do {
//                        Log.e("aaaa","存进来了data:"+cusor.getString(cusor.getColumnIndex("data")));
//                    }while(cusor.moveToNext());
//                }
//                cusor.close();
                Intent intent_baocun=new Intent(MainAddActivity.this,MainActivity.class);
                startActivity(intent_baocun);
                break;
            default:
                break;
        }



    }
    long day = 24 * 60 * 60 * 1000;
    private void showDatePickDialog() {
        CustomDatePickerDialogFragment fragment = new CustomDatePickerDialogFragment();
        fragment.setOnSelectedDateListener(this);
        Bundle bundle = new Bundle();
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTimeInMillis(System.currentTimeMillis());
        currentDate.set(Calendar.HOUR_OF_DAY,0);
        currentDate.set(Calendar.MINUTE,0);
        currentDate.set(Calendar.SECOND,0);
        currentDate.set(Calendar.MILLISECOND,0);
        bundle.putSerializable(CustomDatePickerDialogFragment.CURRENT_DATE,currentDate);
        long start = currentDate.getTimeInMillis() - day * 30;
        long end = currentDate.getTimeInMillis() ;
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(start);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(end);
        bundle.putSerializable(CustomDatePickerDialogFragment.START_DATE,startDate);
        bundle.putSerializable(CustomDatePickerDialogFragment.END_DATE,currentDate);

        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(),CustomDatePickerDialogFragment.class.getSimpleName());
    }

    @Override
    public void onSelectedDate(int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        dateButton.setText(year+"-"+monthOfYear+"-"+dayOfMonth+" ");

    }

    public void updateDateButtonText(){
//        String datebutton= dateButton.getText().toString();
//        String str1[]=datebutton.split("年");
//        String str2[]=str1[1].split("月");
//        String str3[]=str2[1].split("日");
//        dateButton.setText(str1[0]+"-"+str2[0]+"-"+str3[0]);
        Calendar cal=Calendar.getInstance();
        int y=cal.get(Calendar.YEAR);
        int m=cal.get(Calendar.MONTH)+1;
        int d=cal.get(Calendar.DATE);
        dateButton.setText(y+"-"+m+"-"+d);
    }



}
