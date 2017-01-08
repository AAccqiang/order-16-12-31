package com.example.administrator.orderapp.fragment.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.MenuActivity;
import com.example.administrator.orderapp.entry.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VisitorFragment extends DialogFragment {


    @BindView(R.id.et_visitorNum)
    EditText mEtVisitorNum;
    private MenuActivity mMenuActivity;

    public VisitorFragment(MenuActivity mMenuActivity) {
        this.mMenuActivity = mMenuActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_visitor, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @OnClick({R.id.iv_back, R.id.dialog_btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:

                getDialog().dismiss();
                break;
            case R.id.dialog_btn_ok:
                String num = mEtVisitorNum.getText().toString();
                mMenuActivity.setVisitorNum(num);
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType(MessageEvent.TYPE_DIALOG);
                EventBus.getDefault().post(messageEvent);
                getDialog().dismiss();
                break;
        }
    }
}
