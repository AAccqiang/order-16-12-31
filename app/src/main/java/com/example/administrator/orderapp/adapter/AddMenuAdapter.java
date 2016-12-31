package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.Menus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class AddMenuAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<Menus> mMenusList = new ArrayList<>();
    private List<List<View>> mViews = new ArrayList<>();
    private LayoutInflater mLayoutInflater;
    View view;
    public AddMenuAdapter(Context context) {

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
    }

    public void clearList(){
        mMenusList.clear();
        notifyDataSetChanged();
    }

    public void addList(List<Menus> list){
        mMenusList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return mMenusList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return mMenusList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mViews.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.layout_addmenu_item,null);
        TextView tvMenuName = (TextView) view.findViewById(R.id.tv_menu_name1);
        Menus menus = mMenusList.get(i);
        Log.e("aac",mMenusList.size() + " -----" );
        tvMenuName.setText(menus.getDishName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.drawable_btn,null);

        view.setPadding(20,20,20,20);

        if(view == null){
            Log.e("view","view is 空");
        }else{
            Log.e("view","view isNot 空");
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
