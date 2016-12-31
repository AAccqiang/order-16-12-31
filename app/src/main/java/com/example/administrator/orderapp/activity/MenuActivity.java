package com.example.administrator.orderapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.adapter.AddMenuAdapter;
import com.example.administrator.orderapp.adapter.MenuAdapter;
import com.example.administrator.orderapp.adapter.TableAdapter;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.TableBean;
import com.example.administrator.orderapp.fragment.MenuColdFragment;
import com.example.administrator.orderapp.fragment.MenuHotFragment;
import com.example.administrator.orderapp.fragment.MenuMainFragment;
import com.example.administrator.orderapp.fragment.MenuOtherFragment;
import com.example.administrator.orderapp.fragment.MenuSoupFragment;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class MenuActivity extends FragmentActivity {

    @BindView(R.id.ll_hot) LinearLayout llHot;
    @BindView(R.id.ll_cold) LinearLayout llCold;
    @BindView(R.id.ll_main) LinearLayout llMain;
    @BindView(R.id.ll_other) LinearLayout llOther;
    @BindView(R.id.ll_soup) LinearLayout llSoup;

    @BindView(R.id.tv_menu_hot)TextView tvHot;
    @BindView(R.id.tv_menu_cold)TextView tvCold;
    @BindView(R.id.tv_menu_main)TextView tvMain;
    @BindView(R.id.tv_menu_soup)TextView tvSoup;
    @BindView(R.id.tv_menu_other)TextView tvOther;


    @BindView(R.id.viewPager)ViewPager viewPager;
    @BindView(R.id.spinner)Spinner mSpinner;

    @BindView(R.id.menu_ren)TextView tvRen;
    private DBmanager mDBmanager;
    private List<TableBean> mTableBeanList ;
    private TableAdapter mTableAdapter;
    private AddMenuAdapter mAddMenuAdapter;

    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
        mDBmanager = new DBmanager(this);
        expandableListView = (ExpandableListView) findViewById(R.id.edListView);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(listener);
        setTableSpinner();

        String name = SharedPreferUtil.getName(this);
        tvRen.setText(name);


    }
//    private Menus mMenus ;
//
//    public Menus setMenu(Menus mm){
//        return mm;
//    }


    //接口回调
    //适配器中
    List<Menus> list = new ArrayList<>();
    public void setAddMenuAdapter(Menus mMenus,Context context){

        mAddMenuAdapter.clearList();
        list.add(mMenus);
        mAddMenuAdapter = new AddMenuAdapter(context);
        mAddMenuAdapter.addList(list);
        mAddMenuAdapter.notifyDataSetChanged();
        expandableListView.setAdapter(mAddMenuAdapter);
        expandableListView.setGroupIndicator(null);
    }

    @OnClick(R.id.btn_back)
    public void back(){
        finish();
    }
    //桌号
    // TODO: 2016/12/29 0029 桌号方式推出做（弹框）
    private void setTableSpinner(){
        mTableBeanList = mDBmanager.getTableNum();
        mTableAdapter = new TableAdapter(this);
        mTableAdapter.addList(mTableBeanList);
        mTableAdapter.notifyDataSetChanged();

        mSpinner.setAdapter(mTableAdapter);
    }


    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MenuHotFragment();
                case 1:
                    //跳转到本地视频的fragment
                    return new MenuColdFragment();
                case 2:
                    //跳转到我的收藏的fragment
                    return new MenuSoupFragment();
                case 3:
                    //跳转到我的收藏的fragment
                    return new MenuMainFragment();
                case 4:
                    //跳转到我的收藏的fragment
                    return new MenuOtherFragment();
                default:
                    throw new RuntimeException("未知错误");

            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            handler.sendEmptyMessageDelayed(0,300);
            llHot.setSelected(position == 0);
            llCold.setSelected(position == 1);
            llSoup.setSelected(position == 2);
            llMain.setSelected(position == 3);
            llOther.setSelected(position == 4);
            initText();
            switch (position){
                case 0:
                    tvHot.setTextSize(20.0f);
                    tvHot.setTextColor(color);
                    break;
                case 1:
                    tvCold.setTextSize(20.0f);
                    tvCold.setTextColor(color);
                    break;
                case 2:
                    tvSoup.setTextSize(20.0f);
                    tvSoup.setTextColor(color);
                    break;
                case 3:
                    tvMain.setTextSize(20.0f);
                    tvMain.setTextColor(color);
                    break;
                case 4:
                    tvOther.setTextSize(20.0f);
                    tvOther.setTextColor(color);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    int c = Color.parseColor("#dddddd");
    int color  = Color.parseColor("#ffffff");

    @OnClick({R.id.ll_hot,R.id.ll_cold,R.id.ll_main,R.id.ll_soup,R.id.ll_other})
    public void choose(View v){
        handler.sendEmptyMessageDelayed(0,300);

        initText();

        switch (v.getId()){
            case R.id.ll_hot:
                viewPager.setCurrentItem(0);
                tvHot.setTextSize(20.0f);
                tvHot.setTextColor(color);
                break;
            case R.id.ll_cold:
                viewPager.setCurrentItem(1);
                tvCold.setTextSize(20.0f);
                tvCold.setTextColor(color);
                break;
            case R.id.ll_soup:
                viewPager.setCurrentItem(2);
                tvSoup.setTextSize(20.0f);
                tvSoup.setTextColor(color);
                break;
            case R.id.ll_main:
                viewPager.setCurrentItem(3);
                tvMain.setTextSize(20.0f);
                tvMain.setTextColor(color);
                break;
            case R.id.ll_other:
                viewPager.setCurrentItem(4);
                tvOther.setTextSize(20.0f);
                tvOther.setTextColor(color);
                break;
            default:
                throw new RuntimeException("未知错误");
        }
    }

    private void initText(){
        tvHot.setTextColor(c);
        tvCold.setTextColor(c);
        tvMain.setTextColor(c);
        tvSoup.setTextColor(c);
        tvOther.setTextColor(c);

        tvHot.setTextSize(14);
        tvCold.setTextSize(14);
        tvMain.setTextSize(14);
        tvSoup.setTextSize(14);
        tvOther.setTextSize(14);
    }



    //每过2秒接收定时器处理一次消息
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                initText();
            }else if(msg.what == 1){
                Log.e("bbb","接受到");
               Intent intent = new Intent(MenuActivity.this,MenuActivity.class);
                startActivity(intent);
            }
        }
    };

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void switchFragment(){
//
//
//    }

}
