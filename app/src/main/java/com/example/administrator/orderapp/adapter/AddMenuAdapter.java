package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/29 0029.
 */

public class AddMenuAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Menus> mMenusList;
    private List<List<View>> mViews = new ArrayList<>();
    private LayoutInflater mLayoutInflater;

    public AddMenuAdapter(Context context) {
        this.context = context;
        mMenusList = new ArrayList<>();
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void clearList(){
        mMenusList.clear();
        notifyDataSetChanged();
    }

    private void removeList(int i){
        mMenusList.remove(i);
        notifyDataSetChanged();
    }

    public void addList(List<Menus> m){
        mMenusList.addAll(m);

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

   //     view = mLayoutInflater.inflate(R.layout.layout_add_item,null);
        view = LayoutInflater.from(context).inflate(R.layout.layout_addmenu_item,null);
        TextView tvMenuName = (TextView) view.findViewById(R.id.tv_menu_name1);
        TextView tvPay = (TextView) view.findViewById(R.id.tv_menu_pay);
  //      Menus menus = new Menus();
        Menus menus = mMenusList.get(i);
//        Log.e("aa",mMenusList.size() + "   mMenusList");
//        Log.e("aa",i + "   i");
     //   Log.e("aa",menus.toString());
        tvMenuName.setText(menus.getDishName());
        tvPay.setText(menus.getPrice()+"元/份");
//        tvMenuName.setText("aaa");
//        tvPay.setText("bbb");


//        ViewHolder holder;
//        if(view == null){
//            view = LayoutInflater.from(context).inflate(R.layout.layout_addmenu_item,null);
//            holder = new ViewHolder();
//            ButterKnife.bind(this,view);
//
//            view.setTag(holder);
//        }else{
//            holder = (ViewHolder) view.getTag();
//        }
//        Log.e("aa",mMenusList.size() + "   mMenusList");
//        Log.e("aa",i + "   i");
       // Menus menus = mMenusList.get(i);
    //    holder.tvMenuName.setText("aaa");

        return view;
    }

    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.drawable_btn,null);
        Button button = (Button) view.findViewById(R.id.btn_remove);
        view.setPadding(20,20,20,20);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mMenusList.remove(i);
//                notifyDataSetChanged();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType(MessageEvent.TYPE_MENU_REMOVE);
                messageEvent.setMenus(mMenusList.get(i));
                Log.e("aa",mMenusList.get(i).toString() + "发送过去了");
                EventBus.getDefault().post(messageEvent);
            }
        });


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

//    class ViewHolder{
//        @BindView(R.id.tv_menu_name1)
//        TextView tvMenuName;
//
//        @BindView(R.id.tv_menu_pay)
//        TextView tvPay;
//    }


}
