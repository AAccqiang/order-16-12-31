package com.example.administrator.orderapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.adapter.AddMenuAdapter;
import com.example.administrator.orderapp.adapter.TableAdapter;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.MessageEvent;
import com.example.administrator.orderapp.entry.TableBean;
import com.example.administrator.orderapp.fragment.MenuColdFragment;
import com.example.administrator.orderapp.fragment.MenuHotFragment;
import com.example.administrator.orderapp.fragment.MenuMainFragment;
import com.example.administrator.orderapp.fragment.MenuOtherFragment;
import com.example.administrator.orderapp.fragment.MenuSoupFragment;
import com.example.administrator.orderapp.fragment.dialog.VisitorFragment;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MenuActivity extends FragmentActivity {

    @BindView(R.id.ll_hot)
    LinearLayout llHot;
    @BindView(R.id.ll_cold)
    LinearLayout llCold;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.ll_other)
    LinearLayout llOther;
    @BindView(R.id.ll_soup)
    LinearLayout llSoup;

    @BindView(R.id.tv_menu_hot)
    TextView tvHot;
    @BindView(R.id.tv_menu_cold)
    TextView tvCold;
    @BindView(R.id.tv_menu_main)
    TextView tvMain;
    @BindView(R.id.tv_menu_soup)
    TextView tvSoup;
    @BindView(R.id.tv_menu_other)
    TextView tvOther;


    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.spinner)
    Spinner mSpinner;
    @BindView(R.id.menu_ren)
    TextView tvRen;

    @BindView(R.id.tv_menu_num)
    TextView tvNum;
    @BindView(R.id.tv_menu_numPay)
    TextView tvNumPay;
    @BindView(R.id.menu_right_num)
    Button mMenuRightNum;


    private DBmanager mDBmanager;
    private List<TableBean> mTableBeanList;
    private TableAdapter mTableAdapter;
    private AddMenuAdapter mAddMenuAdapter;

    private ExpandableListView expandableListView;
    private int mTableNum;

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


    @OnClick(R.id.btn_back)
    public void back() {
        finish();
    }

    //桌号
    // TODO: 2016/12/29 0029 桌号方式推出做（弹框）
    private void setTableSpinner() {
        mTableBeanList = mDBmanager.getTableNum();
        mTableAdapter = new TableAdapter(this);
        mTableAdapter.addList(mTableBeanList);
        mTableAdapter.notifyDataSetChanged();

        mSpinner.setAdapter(mTableAdapter);

    }


    private FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
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
            handler.sendEmptyMessageDelayed(0, 300);
            llHot.setSelected(position == 0);
            llCold.setSelected(position == 1);
            llSoup.setSelected(position == 2);
            llMain.setSelected(position == 3);
            llOther.setSelected(position == 4);
            initText();
            switch (position) {
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
    int color = Color.parseColor("#ffffff");

    @OnClick({R.id.ll_hot, R.id.ll_cold, R.id.ll_main, R.id.ll_soup, R.id.ll_other})
    public void choose(View v) {
        handler.sendEmptyMessageDelayed(0, 300);

        initText();

        switch (v.getId()) {
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

    private void initText() {
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
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                initText();
            }
        }
    };

    //设置桌号

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }



    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    Menus mMenus;
    List<Menus> list = new ArrayList<>();

    String visitorNum;//桌位数
    public void setVisitorNum(String visitorNum) {
        this.visitorNum = visitorNum;
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchFragment(MessageEvent event) {
        switch (event.getType()) {
            case MessageEvent.TYPE_MENU_FRAGMENT:
                mMenus = event.getMenus();

                if (!list.contains(mMenus)) {
                    list.add(mMenus);
                } else {
                    Toast.makeText(this, "亲,已添加到菜列表中~", Toast.LENGTH_SHORT).show();
                }
//                demoAdapter.addList(list);
//                demoAdapter.notifyDataSetChanged();
//                mListView.setLayoutManager(new LinearLayoutManager(this));
//                mListView.setAdapter(demoAdapter);
                Log.e("aa", mMenus.toString());
                mAddMenuAdapter = new AddMenuAdapter(this);
                mAddMenuAdapter.addList(list);
                expandableListView.setAdapter(mAddMenuAdapter);
                mAddMenuAdapter.notifyDataSetChanged();
                expandableListView.setGroupIndicator(null);
                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int i) {
                        for (int j = 0; j < mAddMenuAdapter.getGroupCount(); j++) {
                            if (j != i) {
                                expandableListView.collapseGroup(j);
                            }
                        }
                    }
                });


                break;

            case MessageEvent.TYPE_MENU_REMOVE:
                Menus menus = event.getMenus();
                Log.e("aa", menus.toString() + "接收了");
                list.remove(menus);
                mAddMenuAdapter.clearList();
                mAddMenuAdapter.addList(list);

                mAddMenuAdapter.notifyDataSetChanged();
                expandableListView.setGroupIndicator(null);

                break;
            case MessageEvent.TYPE_DIALOG:
                Log.e("visitorNum",visitorNum);
                mMenuRightNum.setText(visitorNum);
                break;
        }
        tvNum.setText("共" + list.size() + "份");
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            int b = Integer.parseInt(list.get(i).getPrice());
            total += b;
        }
        tvNumPay.setText("总价格：" + total + "元  ");
    }

    //保存桌号,客人数,菜图片String路径集合“,”分开
    private void saveTablenumAndVisitornum() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i < list.size();i++){
            String a = list.get(i).getImgPath();
            if(i != (list.size() -1)){
                sb.append(a + ",");
            }else{
                sb.append(a);
            }
        }

        SharedPreferUtil.saveStringImgPath(this,sb.toString());
        SharedPreferUtil.saveTable(this, mTableNum + "");
        SharedPreferUtil.saveVisitorNum(this,visitorNum + "");
    }


    //下单 传递去菜单页面
    @OnClick(R.id.btn_down)
    public void down() {
        if(list.size() == 0){
            Toast.makeText(this, "选菜后再点我吧~", Toast.LENGTH_SHORT).show();
            return;
        }
        String n = mMenuRightNum.getText().toString();
        if(n.equals("") || n == null){
            Toast.makeText(this, "亲,客人都还没添加~", Toast.LENGTH_SHORT).show();
            return;
        }

        long a = mSpinner.getSelectedItemId();
        mTableNum = mTableBeanList.get((int) a).getNum();

        getObjId();
        saveTablenumAndVisitornum();//保存客人数 桌号
        for (Menus menus : list) {
            //插入obj
            Log.e("meni", menus.toString());
            menus.setObj(orderId);
            //插入桌号

            Log.e("桌号", mTableNum + "");

            menus.setTableNum(mTableNum + "");

            mDBmanager.saveMenuList(menus);
        }

        List<Menus> tempDishList = mDBmanager.getTempDishList();
        for (Menus m : tempDishList) {
            Log.e("list.递交前", m.toString());
        }

        Intent intent = new Intent();
        intent.setClass(this, MenuOrderActivity.class);

        startActivity(intent);

        finish();
        // TODO: 2017/1/6 0006 aa
    }

    String orderId;

    private String getObjId() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = new Date(System.currentTimeMillis());
        String da = sdf.format(date);
        Log.e("da", da);
        orderId = da + mTableNum + "0000" + (new Random().nextInt(899) + 100);
        return orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @OnClick(R.id.menu_right_num)
    public void onClick() {
        new VisitorFragment(this).show(getSupportFragmentManager(), "aa");
    }


}
