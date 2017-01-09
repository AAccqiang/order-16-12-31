package com.example.administrator.orderapp.fragment.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.MenuOrderActivity;
import com.example.administrator.orderapp.entry.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RemarkFragment extends DialogFragment {

    @BindView(R.id.tv_remark)
    EditText mTvRemark;
    private MenuOrderActivity mMenuOrderActivity;

    public RemarkFragment(MenuOrderActivity menuOrderActivity) {
        mMenuOrderActivity = menuOrderActivity;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_remark, container, false);
        ButterKnife.bind(this, view);
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
                String remark = mTvRemark.getText().toString();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setRemark(remark);
                messageEvent.setType(MessageEvent.TYPE_DIALOG_REMARK_RETURN);
                EventBus.getDefault().post(messageEvent);
                break;
        }
    }


}
