package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyHolder> {
    private List<Menus> mMenusList;

    private Context mContext;
    private DBmanager mDBmanager;

    public OrderAdapter(Context context, List<Menus> menus) {
        mContext = context;
        mMenusList = menus;
        mDBmanager = new DBmanager(context);
    }

    public void clearList(){
        mMenusList.clear();
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_menu_order_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final Menus menus = mMenusList.get(position);
        holder.btnZ.setText(position + "");
        Log.e("c", menus.getImgPath());
        Picasso.with(mContext).load(UrlUtil.BASE + menus.getImgPath()).into(holder.ivMenu);
        holder.tvName.setText(menus.getDishName() + "(" + menus.getDishClass() + ")");
        holder.btnNum.setText("1");
        holder.tvMenuPay.setText("￥ " + menus.getPrice());
        final int c = Integer.parseInt(menus.getPrice().toString());
        Log.e("cccccccc",c + "");
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType(MessageEvent.TYPE_Menu_LEFT);
                messageEvent.setMenus(mMenusList.get(position));
                messageEvent.setPay(c);
                messageEvent.setTypeNum(3);
                messageEvent.setNum(Integer.parseInt(holder.btnNum.getText().toString()));
                EventBus.getDefault().post(messageEvent);
                mDBmanager.delectList(menus.getObj());

                mMenusList.remove(position);
                notifyDataSetChanged();
            }
        });



        holder.btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(holder.btnNum.getText().toString());


                if (a > 1) {
                    int b = a - 1;
                    holder.btnNum.setText(b + "");
                    int d = Integer.parseInt(holder.btnNum.getText().toString());
                    holder.tvMenuPay.setText("￥ " + c * d);

                    MessageEvent messageEvent = new MessageEvent();


                    messageEvent.setType(MessageEvent.TYPE_Menu_LEFT);
                    messageEvent.setNum(d);
                    messageEvent.setPay(c);
                    messageEvent.setTypeNum(1);
                    messageEvent.setLorR(true);
                    EventBus.getDefault().post(messageEvent);


                }
            }
        });

        holder.btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(holder.btnNum.getText().toString());
                int b = a + 1;

                holder.btnNum.setText(b + "");
                holder.tvMenuPay.setText("￥ " + c * b);


                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setLorR(false);
                messageEvent.setType(MessageEvent.TYPE_Menu_LEFT);
                messageEvent.setPay(c);
                messageEvent.setTypeNum(2);
                if (b == 1) {
                    messageEvent.setNum(b + 1);
                } else {
                    messageEvent.setNum(b);
                }

                EventBus.getDefault().post(messageEvent);

            }
        });

//        String gg = holder.btnNum.getText().toString();
//        int g = Integer.parseInt(gg);

        menus.setDishNum(holder.btnNum.getText().toString());
        menus.setRemark("");

    }

    @Override
    public int getItemCount() {
        return mMenusList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.btn_z)
        Button btnZ;
        @BindView(R.id.iv_menu)
        ImageView ivMenu;
        @BindView(R.id.tv_menu_name)
        TextView tvName;
        @BindView(R.id.btn_remark)
        Button btnRemark;
        @BindView(R.id.btn_left)
        Button btnLeft;
        @BindView(R.id.btn_right)
        Button btnRight;
        @BindView(R.id.btn_num)
        Button btnNum;
        @BindView(R.id.tv_menu_pay)
        TextView tvMenuPay;
        @BindView(R.id.btn_delete)
        Button btnDelete;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
