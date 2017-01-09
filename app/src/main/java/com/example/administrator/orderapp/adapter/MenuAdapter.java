package com.example.administrator.orderapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.MenuActivity;
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
 * Created by Administrator on 2016/12/28 0028.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {

    private List<Menus> menus = new ArrayList<>();

    private Context mContext;
    private MenuActivity mMenuActivity;

    public MenuAdapter(Context context) {
        menus.clear();
        mMenuActivity = new MenuActivity();
        mContext = context;

    }

    public void clearList() {
        menus.clear();
        notifyDataSetChanged();
    }

    public void addList(List<Menus> list) {
        menus.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final Menus menu = menus.get(position);
        holder.tvName.setText(menu.getDishName());
        holder.tvPay.setText("￥ " + menu.getPrice() + "/份");
        Picasso.with(mContext).load(UrlUtil.BASE + menu.getImgPath()).into(holder.ivMenu);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent event = new MessageEvent();
                event.setType(MessageEvent.TYPE_MENU_FRAGMENT);
                event.setMenus(menu);
                EventBus.getDefault().post(event);
                notifyDataSetChanged();

            }
        });
        //Glide.with(mContext).load(path).into(holder.ivMenu);
        //Toast.makeText(mContext,path , Toast.LENGTH_SHORT).show();


        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType(MessageEvent.TYPE_DIALOG_MENU);
                messageEvent.setMenus(menu);
                EventBus.getDefault().post(messageEvent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_menu)
        ImageView ivMenu;
        @BindView(R.id.tv_menu_name)
        TextView tvName;
        @BindView(R.id.btn_add)
        ImageButton btnAdd;
        @BindView(R.id.tv_menu_pay)
        TextView tvPay;

        //       private MyOnItemOnClickListener mMyOnItemOnClickListener;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //          this.mMyOnItemOnClickListener = mMyOnItemOnClickListener;
        }

//        @Override
//        public void onClick(View view) {
//            mMyOnItemOnClickListener.onItemClick();
//        }
    }

//    public interface MyOnItemOnClickListener{
//        void onItemClick();
//    }

    public interface OnItemClickListener {
        void onItemClick(View view);

    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
