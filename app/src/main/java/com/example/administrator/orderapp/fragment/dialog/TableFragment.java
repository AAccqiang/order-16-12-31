package com.example.administrator.orderapp.fragment.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.MenuActivity;
import com.example.administrator.orderapp.adapter.MenuTableAdapter;
import com.example.administrator.orderapp.entry.TableBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TableFragment extends DialogFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.rl_dialogTable)
    RelativeLayout mRlDialogTable;
    private MenuActivity mMenuActivity;

    public TableFragment(MenuActivity mMenuActivity) {
        this.mMenuActivity = mMenuActivity;
    }

    DBmanager mDBmanager;
    MenuTableAdapter mMenuTableAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.dialog_table, container, false);

        ButterKnife.bind(this, view);
        initTable();
        return view;
    }


    private void initTable() {
        mDBmanager = new DBmanager(getContext());
        List<TableBean> mTableBeanList = mDBmanager.getTableNum();
        mMenuTableAdapter = new MenuTableAdapter(mTableBeanList, getContext());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setAdapter(mMenuTableAdapter);

        mMenuTableAdapter.setOnItemClickListener(new MenuTableAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int positity) {
                getDialog().dismiss();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("rl_dialogTable", "执行了");
        mMenuActivity.setVisible();
    }


}
