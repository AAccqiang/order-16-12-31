package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.MessageEvent;
import com.example.administrator.orderapp.entry.Order;
import com.example.administrator.orderapp.http.UrlUtil;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/2 0002.
 */

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.MyHolder> {
    private List<List<Order>> mOrderList;

    private Context mContext;
    private DBmanager mDBmanager;
    List<String> groupList;
    private List<String> imgPathList = new ArrayList<>();

    public OrderDetailAdapter(Context context,List<String> groupList, List<List<Order>> mOrderList) {

        this.groupList = groupList;
        mContext = context;
        this.mOrderList = mOrderList;
        orders = mOrderList.get(0);
        mDBmanager = new DBmanager(context);

        for(int i = 0;i < groupList.size();i++){
            for(Order o : orders){
                if(groupList.get(i).equals(o.getOrderId())){
                    imgPathList.add(o.getImgPath());
                    notifyDataSetChanged();
                }
            }
        }
    }


    public void clearList(){
        mOrderList.clear();
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_imageview, parent, false);
        List<Order> orders = mOrderList.get(0);

        return new MyHolder(view);
    }
    List<Order> orders;
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {


        for(int i = 0;i < groupList.size();i++){
           for(Order o : orders){
               if(groupList.get(i).equals(o.getOrderId())){
                   Picasso.with(mContext).load(UrlUtil.BASE + o.getImgPath()).into(holder.iv_icon);
               }
           }
        }



    }

    @Override
    public int getItemCount() {
        return imgPathList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_imageView)
        ImageView iv_icon;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
