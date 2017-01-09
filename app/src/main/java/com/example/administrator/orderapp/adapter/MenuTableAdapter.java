package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.MessageEvent;
import com.example.administrator.orderapp.entry.Order;
import com.example.administrator.orderapp.entry.TableBean;
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

public class MenuTableAdapter extends RecyclerView.Adapter<MenuTableAdapter.MyHolder> {


    private List<TableBean> mTableList;
    private Context mContext;

    public MenuTableAdapter(List<TableBean> tableList, Context context) {
        mTableList = tableList;
        mContext = context;
    }

    public void addList(List<TableBean> list){
        mTableList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_table, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final TableBean tableBean = mTableList.get(position);
        holder.btnTable.setText(tableBean.getNum() +"");
        holder.btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType(MessageEvent.TYPE_DIALOG_TABLE);
                messageEvent.setNum(tableBean.getNum());
                EventBus.getDefault().post(messageEvent);
                listener.onItemClick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTableList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_table)Button btnTable;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view,int positity);

    }

    private MenuTableAdapter.OnItemClickListener listener;

    public void setOnItemClickListener(MenuTableAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

}
