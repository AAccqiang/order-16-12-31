package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dministrator on 2016/12/24 0024.
 */

public class HomeActivity extends Activity {

    @BindView(R.id.home_btn_menu)
    LinearLayout btnMenu;
    @BindView(R.id.home_btn_add)
    LinearLayout btnAdd;
    @BindView(R.id.home_btn_pay)
    LinearLayout btnPay;
    @BindView(R.id.home_btn_schedule)
    LinearLayout btnSchedule;
    @BindView(R.id.home_btn_more)
    LinearLayout btnMore;
    @BindView(R.id.home_tv_name)
    TextView tvName;
    private DBmanager mDBmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mDBmanager = new DBmanager(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        String name = SharedPreferUtil.getName(this);
        tvName.setText(name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sp = getSharedPreferences("first", Context.MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirsts", true);
        if (isFirst) {
            initDb();
        }

    }

    private void initDb(){
        SharedPreferUtil.getDBIsFirst(this);
        mDBmanager.initTableDb(this);
    }


    @OnClick({R.id.home_btn_menu,R.id.home_btn_add,R.id.home_btn_schedule,
                R.id.home_btn_pay,R.id.home_btn_more})
    public void colorChare(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.home_btn_menu:
                intent = new Intent(HomeActivity.this,MenuActivity.class);
                startActivity(intent);

                break;
            case R.id.home_btn_add:

                break;
            case R.id.home_btn_schedule:

                break;
            case R.id.home_btn_pay:

                break;
            case R.id.home_btn_more:

                break;
        }
    }
}
