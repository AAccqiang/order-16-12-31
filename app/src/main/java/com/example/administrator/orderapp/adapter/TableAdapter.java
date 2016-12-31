package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.TableBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class TableAdapter extends BaseAdapter {

    private List<TableBean> mTableList = new ArrayList<>();
    private Context mContext;
    public TableAdapter(Context context) {
        mContext = context;
    }

    public void clearList(){
        mTableList.clear();
        notifyDataSetChanged();
    }

    public void addList(List<TableBean> list){
        mTableList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTableList.size();
    }

    @Override
    public Object getItem(int i) {
        return mTableList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.layout_table_item,null);
        TableBean tableBean = mTableList.get(i);
        TextView tvTable = (TextView) view.findViewById(R.id.tv_tableNum);
        tvTable.setText(tableBean.getNum() + "");
        return view;
    }



}
