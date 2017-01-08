package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.adapter.OrderDetailAdapter;
import com.example.administrator.orderapp.adapter.SuheduleAdapter;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class OrderScheduleActivity extends Activity {


    SuheduleAdapter mSuheduleAdapter;

    @BindView(R.id.expandableList)
    ExpandableListView mExpandableList;

    @BindView(R.id.os_selectBill)
    TextView mOsSelectBill;
    @BindView(R.id.os_changTable)
    TextView mOsChangTable;
    @BindView(R.id.os_combineable)
    TextView mOsCombineable;
    @BindView(R.id.os_update)
    TextView mOsUpdate;

    DBmanager mDBmanager;
    private OrderDetailAdapter mOrderDetailAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
        mDBmanager = new DBmanager(this);

        setAdapter();

    }
    List<List<Order>> cList = new ArrayList<>();
    //添加流水单->布局
    private void setAdapter() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final ArrayList<String> sche = bundle.getStringArrayList("sche");

        cList.clear();
        List<Order> ordersList = mDBmanager.getOrders();
        cList.add(ordersList);

//        mOrderDetailAdapter = new OrderDetailAdapter(this,sche,cList);
//        mRecyclerView = (RecyclerView).findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(context,3));
       // mRecyclerView.setAdapter(mOrderDetailAdapter);

        mSuheduleAdapter = new SuheduleAdapter(this, sche,cList);
        mExpandableList.setAdapter(mSuheduleAdapter);
        mExpandableList.setGroupIndicator(null);
        mExpandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int position) {
                for (int i = 0; i < sche.size(); i++) {
                    if (position != i) {// 关闭其他分组
                        mExpandableList.collapseGroup(i);
                    }
                }
            }
        });
    }

    @OnClick(R.id.btn_back)
    public void btnBack() {
        finish();
    }

}
