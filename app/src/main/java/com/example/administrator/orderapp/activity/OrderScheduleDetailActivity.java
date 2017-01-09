package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.adapter.OrderDetailAdapter;
import com.example.administrator.orderapp.entry.Order;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/5 0005.
 */

public class OrderScheduleDetailActivity extends Activity {
    @BindView(R.id.detail_tvNum)
    TextView mDetailTvNum;
    @BindView(R.id.detail_tvName)
    TextView mDetailTvName;
    @BindView(R.id.detail_tvOrderid)
    TextView mDetailTvOrderid;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    DBmanager mDBmanager;
    OrderDetailAdapter mOrderDetailAdapter;
    private String mThisOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ButterKnife.bind(this);
        mDBmanager = new DBmanager(this);
        setHead();
        setContent();

    }

    private void setContent(){
        List<Order> orders = mDBmanager.getOrders();
        List<Order> list  = new ArrayList<>();
        for(Order o : orders){
            if(o.getOrderId().equals(mThisOrderId)){
                list.add(o);
            }
        }


        mOrderDetailAdapter = new OrderDetailAdapter(this,list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mOrderDetailAdapter);
    }


    private void setHead(){
        Intent intent = getIntent();
        mThisOrderId = intent.getStringExtra("thisOrderId");
        mDetailTvOrderid.setText("流水号 : "+ mThisOrderId);

        String name = SharedPreferUtil.getName(this);
        mDetailTvName.setText(name);

        String tvNum = mThisOrderId.substring(12, 15);
        mDetailTvNum.setText("桌号 : " +tvNum);
    }

    @OnClick(R.id.btn_back)
    public void back() {
        finish();
    }
}
