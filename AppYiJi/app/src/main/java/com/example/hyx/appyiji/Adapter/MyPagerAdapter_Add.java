package com.example.hyx.appyiji.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hyx.appyiji.R;

import java.util.List;

/**
 * Created by hyx on 2018/11/3.
 */

public class MyPagerAdapter_Add extends FragmentPagerAdapter {
    private Context context;
    private List<Fragment> fragmentList;
    private List<String> list_Title;
    public Fragment currentFragment;
    public MyPagerAdapter_Add(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> list_Title) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    /**
     * //此方法用来显示tab上的名字
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
 //       return list_Title.get(position);

//        SpannableString sb = new SpannableString("   "+list_Title.get(position));
//        Drawable image = context.getResources().getDrawable(list_icon.get(position));
//        image.setBounds(0,0,image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;

        return null;

    }

    public View getTabView(int position){
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_tab_tap_add, null);
        TextView tv= (TextView) view.findViewById(R.id.main_tab_tap_add_text);
        tv.setText(list_Title.get(position));
        return view;
    }



}
