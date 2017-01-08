package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.LoginResult;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.fragment.dialog.SubmitFragment;
import com.example.administrator.orderapp.http.RetrofitClient;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dministrator on 2016/12/24 0024.
 */

public class HomeActivity extends FragmentActivity {

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
    private RetrofitClient mRetrofitClient;

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

        mRetrofitClient = RetrofitClient.getInstance();
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

    private void initDb() {
        SharedPreferUtil.getDBIsFirst(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                mDBmanager.initTableDb(HomeActivity.this);
                Log.e("db", "插入数据库一次");
            }
        }).start();

    }


    @OnClick({R.id.home_btn_menu, R.id.home_btn_add, R.id.home_btn_schedule,
            R.id.home_btn_pay, R.id.home_btn_more})
    public void colorChare(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.home_btn_menu:
                intent = new Intent(HomeActivity.this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.home_btn_add:

                break;
            case R.id.home_btn_schedule:
                queryAllorder();

                break;
            case R.id.home_btn_pay:
                List<Menus> tempDishList = mDBmanager.getTempDishList();
                for (Menus menus : tempDishList) {

                    if (menus.getCheckout().equals("0")) {
                        intent = new Intent(this, MenuOrderActivity.class);
                        startActivity(intent);

                        return;
                    }
                }
                Toast.makeText(this, "暂时还没要付款的菜订单~", Toast.LENGTH_SHORT).show();
                break;

            case R.id.home_btn_more:

                break;
        }
    }

    private void queryAllorder() {
        Call<LoginResult> call = mRetrofitClient.httpApi().queryAll();
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult result = response.body();

                if (result.getRt().equals("200")) {

                    List<String> list = new ArrayList<String>();

                    for (int i = 0; i < result.getList().size(); i++) {
                        list.add(result.getList().get(i).get(0));
                    }
                    Intent intent = new Intent(HomeActivity.this, OrderScheduleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("sche", (ArrayList<String>) list);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    return;
                } else {
                    Toast.makeText(HomeActivity.this, "暂时没有订单！", Toast.LENGTH_SHORT).show();
                }

                Log.e("aa", "aaaaaaa");
                Log.e("aa", result.toString() + "---");

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e("aa", "bbbbbb");
            }
        });

    }
}
