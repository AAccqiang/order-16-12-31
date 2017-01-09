package com.example.administrator.orderapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.orderapp.DB.DBmanager;
import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.activity.MenuActivity;
import com.example.administrator.orderapp.adapter.MenuAdapter;
import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.util.SpaceItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/28 0028.
 */

public abstract class BaseFragment extends Fragment  {

    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    MenuAdapter mMenuAdapter;
    DBmanager mDBmanager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(b,container,false);
        ButterKnife.bind(this,view);
        viewPager();
        spacing();
        setData();

        return view;
    }

    public abstract int setId();
    int b = setId();
    private List<Menus> mMenusList;

    //菜集合
    private List<Menus> getList(String m){
        mMenusList = mDBmanager.getDishList();
        List<Menus> list = new ArrayList<>();
        for(Menus menus : mMenusList){
            if(menus.getDishClass().equals(m)){
                list.add(menus);

            }else if(menus.getDishClass().equals(m)){
                list.add(menus);

            }else if(menus.getDishClass().equals(m)){
                list.add(menus);

            }else if(menus.getDishClass().equals(m)){
                list.add(menus);

            }else if(menus.getDishClass().equals(m)){
                list.add(menus);

            }
        }

        return list;

    }
    //菜分类后的集合
    private List<Menus> getMenusList(){

        if(b == R.layout.fragment_hot){
            return getList("热菜");
        }else if(b == R.layout.fragment_cold){

            return getList("凉菜");
        }else if(b == R.layout.fragment_soup){

            return getList("汤类");
        }else if(b == R.layout.fragment_main){

            return getList("主食");
        }else if(b == R.layout.fragment_other){

            return getList("其他");
        }
        return null;
    }

    private void viewPager(){
        mMenuAdapter = new MenuAdapter(getContext());
        mDBmanager = new DBmanager(getContext());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    private void spacing(){
        int spacingInPoxels = getResources().getDimensionPixelSize(R.dimen.spaciing);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPoxels));
    }

    private void setData(){
        //List<Menus> mMenusList = mDBmanager.getDishList();

                mMenusList = getMenusList();
                mMenuAdapter.addList(mMenusList);
                mMenuAdapter.notifyDataSetChanged();
                Log.e("aa0","----------------"+mMenusList.size());
                mRecyclerView.setAdapter(mMenuAdapter);


        //接口会掉
//                mMenuAdapter.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int postion) {
//                        Toast.makeText(getContext(), "点击了" + postion, Toast.LENGTH_SHORT).show();
//                        // new DishFragment().show(getFragmentManager(),"aaa");
//                /*new DishFragment();*/
//                        //  getFragmentManager().beginTransaction().replace(R.id.viewPager,new DishFragment());
//
//                    }
//                });


    }





}
