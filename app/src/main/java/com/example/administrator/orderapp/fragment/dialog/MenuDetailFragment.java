package com.example.administrator.orderapp.fragment.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.MenuActivity;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.http.UrlUtil;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MenuDetailFragment extends DialogFragment {

    @BindView(R.id.tv_menu_name)
    TextView mTvMenuName;
    @BindView(R.id.tv_menu_pay)
    TextView mTvMenuPay;

    @BindView(R.id.tv_tedian)
    TextView mTvTedian;
    @BindView(R.id.iv_usericon)
    CircularImageView mIvUsericon;
    private MenuActivity mMenuActivity;

    public MenuDetailFragment(MenuActivity mMenuActivity) {
        this.mMenuActivity = mMenuActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_menu, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }


    private void init() {
        Menus menu = mMenuActivity.getMenu();
        Picasso.with(getContext()).load(UrlUtil.BASE + menu.getImgPath()).into(mIvUsericon);
        mTvMenuName.setText(menu.getDishName());
        mTvMenuPay.setText(menu.getPrice() + "/份");
        mTvTedian.setText("特点:" + menu.getIntroduction());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "执行了");
        mMenuActivity.setVisible();
    }

    @OnClick(R.id.rl_dialogMenu)
    public void onClick() {
        getDialog().dismiss();
    }
}
