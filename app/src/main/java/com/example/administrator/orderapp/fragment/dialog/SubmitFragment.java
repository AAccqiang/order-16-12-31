package com.example.administrator.orderapp.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.MenuOrderActivity;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class SubmitFragment extends DialogFragment {

    @BindView(R.id.alter_tv_username)
    TextView mAlterTvUsername;
    @BindView(R.id.alter_tv_orderId)
    TextView mAlterTvOrderId;
    private MenuOrderActivity mMenuOrderActivity;

    public SubmitFragment(MenuOrderActivity menuOrderActivity) {
        mMenuOrderActivity = menuOrderActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_submit, container, false);
        ButterKnife.bind(this, view);
        String name = SharedPreferUtil.getName(mMenuOrderActivity);
        mAlterTvUsername.setText("员工账号 : "+name);
        mAlterTvOrderId.setText("流水号 : "+mMenuOrderActivity.getOrderId());
        return view;
    }


    @OnClick({R.id.alter_btn_back, R.id.alter_btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alter_btn_back:
                getDialog().dismiss();
                break;
            case R.id.alter_btn_submit:
                getDialog().dismiss();
                mMenuOrderActivity.orderSubmit();
                break;
        }
    }
}
