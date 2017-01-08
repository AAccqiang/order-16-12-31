package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.adapter.OrderAdapter;
import com.example.administrator.orderapp.entry.LoginResult;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.MessageEvent;

import com.example.administrator.orderapp.entry.Order;
import com.example.administrator.orderapp.fragment.dialog.SubmitFragment;
import com.example.administrator.orderapp.http.RetrofitClient;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/1/1 0001.
 */

public class MenuOrderActivity extends FragmentActivity  {

    @BindView(R.id.menu_ren)
    Button btnName;
    @BindView(R.id.tv_two)
    TextView tvNum;
    @BindView(R.id.tv_five)
    TextView tvPay;
    @BindView(R.id.tv_orderId)
    TextView tvOrderId;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private List<Menus> list = new ArrayList<>();
    private OrderAdapter adapter;
    @BindView(R.id.re)
    RecyclerView mRecyclerView;

    RetrofitClient mRetrofitClient;

    DBmanager mDBmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        mRetrofitClient = RetrofitClient.getInstance();
        mDBmanager = new DBmanager(this);

        getList();


        if (list.size() != 0){
            for (Menus menus : list) {
                int a = Integer.parseInt(menus.getPrice().toString());
                tatol += a;
            }

            tatol = 0;
            for (Menus menus : list) {
                int a = Integer.parseInt(menus.getPrice().toString());
                tatol += a;
            }
            // TODO: 2017/1/6 0006 price
            getObjId();
            getMenuCount();
            setName();
        }else{
            Toast.makeText(this, "暂时没菜哦~", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    String orderId;
    public String getOrderId(){
        return orderId;
    }

    private void setName() {
        String name = SharedPreferUtil.getName(this);
        btnName.setText(name);
    }

    private void getMenuCount() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDishNum("1");
            list.get(i).setRemark("");
        }
    }


    //头部
    private void getObjId() {

        orderId =  list.get(0).getObj();
        tvOrderId.setText("流水号：" + orderId);
        tvTitle.setText(list.get(0).getTableNum() + "号桌订单");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    // tatol 价格总数    b 控制菜的增加或减少  c 菜的数量
    int tatol;
    int b = 0;
    int c;

    //EventBus 从适配器OrderAdapter中传递信息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchPay(MessageEvent event) {

        //1 减少按钮  2 增加按钮  3 删除菜按钮
        switch (event.getType()) {
            case MessageEvent.TYPE_Menu_LEFT:

                c = list.size() + b;
                if (event.getTypeNum() == 1) {

                    tvNum.setText(c - 1 + "");
                    b = b - 1;
                    tvPay.setText(tatol - event.getPay() + "");
                    tatol -= event.getPay();

                } else if (event.getTypeNum() == 2) {

                    tvNum.setText(c + 1 + "");
                    b = b + 1;
                    tvPay.setText(tatol + event.getPay() + "");
                    tatol += event.getPay();

                } else if (event.getTypeNum() == 3) {

                    tvNum.setText(list.size() - 1 + "");
                    tatol = 0;
                    for (Menus menus : list) {
                        int a = Integer.parseInt(menus.getPrice().toString());
                        tatol += a;
                    }
                    tatol -= event.getPay();
                    tvPay.setText(tatol + "");
                    b = 0;
                }


                break;

        }
    }
    List<Menus> l;
    //设置菜的
    private void getList() {

        list.clear();
        l = mDBmanager.getTempDishList();
         Log.e("aaaaaaaaaaaa",l.size() + "");

        for(Menus s : l){
            if(s.getCheckout().equals("0")){
                list.add(s);
            }
        }

        if(list.size() == 0){
            Toast.makeText(this, "暂时没菜哦~", Toast.LENGTH_SHORT).show();
            return;
        }

        for(Menus m : list){
            Log.e("aa",m.toString());
        }
        tvNum.setText(list.size() + "");
        int tatol = 0;
        if(list.size() == 0){
            Log.e("list,",+list.size() + "---------");
            return;
        }
        Log.e("list,",list.get(0).getPrice());
        for (Menus menus : list) {
            int a = Integer.parseInt(menus.getPrice());
            tatol += a;
        }
        tvPay.setText(tatol + "");

        adapter = new OrderAdapter(this, list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.btn_empty, R.id.btn_down, R.id.btn_back})
    public void switchKey(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_down:
                SubmitFragment submitFragment = new SubmitFragment(this);
                submitFragment.show(getSupportFragmentManager(), "aa");
                break;
            case R.id.btn_empty:
                adapter.clearList();
                mDBmanager.delectAllList();
                adapter.notifyDataSetChanged();

                tvPay.setText("0");
                tvNum.setText("0");
                break;
        }
    }



    public void orderSubmit() {
      //  SharedPreferUtil.saveMenu(this,list);

        Order order ;

        String visitorNum = SharedPreferUtil.getVisitorNum(this);
        String tv = tvNum.getText().toString();
        for(Menus menus : list){
            order= new Order();
            order.setOrderId(orderId);
            order.setMenuName(menus.getDishName());
            order.setPayNum(tatol + "");
            order.setVisitorNum(visitorNum);
            order.setMenuNum(tv + "");
            order.setImgPath(menus.getImgPath());
            Log.e("orders",order.toString());
            mDBmanager.saveOrders(order);

        }

        if (list.isEmpty()) {
            Toast.makeText(this, "下单失败", Toast.LENGTH_SHORT).show();
        } else {
            String username = SharedPreferUtil.getName(getApplicationContext());

            JSONObject obj = new JSONObject();

            try {
                obj.put("name", username);
                obj.put("cus", "timer");
                obj.put("status", "无");
                obj.put("sum", "" + tatol);
                obj.put("orders", getOrderId());

                JSONArray array = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    Menus menus = list.get(i);
                    JSONObject o = new JSONObject();
                    o.put("list" + i, menus.getDishId() + "," + menus.getDishNum() + "," + menus.getRemark());
                    array.put(i, o);
                }

                obj.put("list", array);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.e("down", obj.toString());

            Call<LoginResult> call = mRetrofitClient.httpApi().submit(obj.toString());
            call.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    LoginResult result = response.body();
                    if (result.getRt().equals("200")) {
                        Toast.makeText(MenuOrderActivity.this, "下单成功！", Toast.LENGTH_SHORT).show();
                         mDBmanager.updateList();
//                                Intent in = new Intent(MenuOrderActivity.this,HomeActivity.class);
//                                startActivity(in);
                                finish();
                    } else {
                        Toast.makeText(MenuOrderActivity.this, "下单失败！", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {

                    Toast.makeText(MenuOrderActivity.this, "下单超时！", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }


}
