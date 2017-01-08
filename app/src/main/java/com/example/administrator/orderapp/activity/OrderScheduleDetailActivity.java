package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.administrator.orderapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class OrderScheduleDetailActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_back)
    public void back(){
        finish();
    }
}
