package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.OrderScheduleDetailActivity;
import com.example.administrator.orderapp.entry.Order;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class SuheduleAdapter  extends BaseExpandableListAdapter   {

    private List<String> groupList;
    private List<List<Order>> childredList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public SuheduleAdapter(Context context,List<String> groupList,List<List<Order>> childredList) {
        this.context = context;
        this.groupList = groupList;
        this.childredList = childredList;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    public void clearList(){
        groupList.clear();
        notifyDataSetChanged();
    }

    private void removeList(int i){
        groupList.remove(i);
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childredList.get(i).get(i1);
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
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.layout_suhedule,null);
        TextView tvOrderId = (TextView) view.findViewById(R.id.tv_orderId);
        tvOrderId.setText(groupList.get(i));



        String name = SharedPreferUtil.getName(context);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(name);

        //设置订单的日期
        String data = groupList.get(i);
        String dataStr = data.substring(0,4)+ "-";
        dataStr = dataStr + data.substring(4,6) + "-";
        dataStr += data.substring(6,8);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvDate.setText(dataStr);

        //设置桌号
        String tableNum = data.substring(12,15);
        TextView tvTable = (TextView) view.findViewById(R.id.tv_tableNum);
        tvTable.setText(tableNum);

        Button btnQuery = (Button) view.findViewById(R.id.btn_query);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderScheduleDetailActivity.class);
                intent.putExtra("thisOrderId",groupList.get(i));
                Log.e("thisOrderId",groupList.get(i));
                context.startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        view = mLayoutInflater.inflate(R.layout.layout_suhedule_item,null);
        TextView tvPeople = (TextView) view.findViewById(R.id.tv_PeopleNum);
        TextView tvMenuNum = (TextView) view.findViewById(R.id.tv_menuNum);
        TextView tvMenuPlay = (TextView) view.findViewById(R.id.tv_menu_pay);

        List<Order> orders = childredList.get(0);

        for(Order order : orders){
            if(groupList.get(i).equals(order.getOrderId())){

                tvPeople.setText("客人共:"+order.getVisitorNum()+"人");
                tvMenuNum.setText("菜单共:" + order.getMenuNum() +"份 (计算了已加份的)");
                tvMenuPlay.setText("总价格:"+order.getPayNum()+"元");
            }
        }


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }




}
