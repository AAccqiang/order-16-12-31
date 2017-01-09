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
import com.pkmmte.view.CircularImageView;
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
    private List<Order> mOrderList;
    private Context mContext;


    public OrderDetailAdapter(Context context,List<Order> mOrderList) {
        mContext = context;
        this.mOrderList = mOrderList;

    }


    public void clearList(){
        mOrderList.clear();
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_suhedle_detail, parent, false);
        return new MyHolder(view);
    }
   boolean mBoolean = false;
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        Order order = mOrderList.get(position);
        Picasso.with(mContext).load(UrlUtil.BASE + order.getImgPath()).into(holder.mCircularImageView);
        holder.tvMenuName.setText(order.getMenuName());

        holder.btnQueren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnQueren.setBackgroundResource(mBoolean == false ? R.drawable.btn_fen_queren : R.drawable.btn_bg_queren);

                if(mBoolean == false){
                    mBoolean = true;
                }else {
                    mBoolean = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_usericon)
        CircularImageView mCircularImageView;
        @BindView(R.id.tv_menu_name)
        TextView tvMenuName;
        @BindView(R.id.btn_queren_no)
        Button btnQueren;


        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
