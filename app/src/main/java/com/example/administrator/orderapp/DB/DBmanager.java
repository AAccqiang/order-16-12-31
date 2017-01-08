package com.example.administrator.orderapp.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.orderapp.entry.Menus;
import com.example.administrator.orderapp.entry.Order;
import com.example.administrator.orderapp.entry.TableBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class DBmanager {

    private DBhelper dBhelper;
    Context context;

    public DBmanager(Context context) {
        dBhelper = new DBhelper(context);
        this.context = context;
    }

    //插入数据
    public void initTableDb(Context context) {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        try {
            InputStreamReader ir = new InputStreamReader(context.getAssets().open("db/dish_db.sql"));
            BufferedReader br = new BufferedReader(ir);
            String len = null;
            while ((len = br.readLine()) != null) {
                if (len.length() != 0) {
                    db.execSQL(len);
                    System.out.println(len);
                }
            }
            br.close();
            ir.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<TableBean> getTableNum() {

        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from table_list", null);
        TableBean tableBean = null;
        List<TableBean> list = new ArrayList<>();
        list.clear();
        while (cursor.moveToNext()) {

            int tableNum = cursor.getInt(cursor.getColumnIndex("table_num"));
            String tableName = cursor.getString(cursor.getColumnIndex("table_name"));
            int tableSize = cursor.getInt(cursor.getColumnIndex("table_size"));

            list.add(new TableBean(tableNum, tableName, tableSize));
        }
        cursor.close();
        cursor = null;
        db.close();
        return list;
    }
//    //获取Dish数据集合
    public List<Menus> getDishList() {

        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from dish_list", null);
        List<Menus> list = new ArrayList<>();
        list.clear();
        while (cursor.moveToNext()) {
            String  dishId = cursor.getString(cursor.getColumnIndex("dish_id"));
            String dishName = cursor.getString(cursor.getColumnIndex("dish_name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
            String dishClass = cursor.getString(cursor.getColumnIndex("dish_class"));
            String imgDownload = cursor.getString(cursor.getColumnIndex("img_download"));
            String imgPath = cursor.getString(cursor.getColumnIndex("img_path"));
            list.add(new Menus(dishId,dishName, price, introduction, dishClass, imgDownload, imgPath));
        }
        cursor.close();
        cursor = null;
        db.close();
        return list;
    }

    public void saveMenuList(Menus menus){
        SQLiteDatabase db = dBhelper.getWritableDatabase();

        db.execSQL("insert into temp_dish_list(dish_id,dish_name,dish_class,price,perple,img_path,dish_num,dish_remark,table_num,obj,checkout) values(" +
                "?,?,?,?,?,?,?,?,?,?,?)",new String[]{menus.getDishId(),menus.getDishName(),menus.getDishClass(),menus.getPrice(),menus.getPerson()
                                               ,menus.getImgPath(),menus.getDishNum(),menus.getRemark(),menus.getTableNum(),menus.getObj(),"0"});

        db.close();

    }

    public List<Menus> getTempDishList() {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from temp_dish_list", null);

        List<Menus> list = new ArrayList<>();
        list.clear();
        while (cursor.moveToNext()) {
            String  dishId = cursor.getString(cursor.getColumnIndex("dish_id"));
            String dishName = cursor.getString(cursor.getColumnIndex("dish_name"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String perple = cursor.getString(cursor.getColumnIndex("perple"));
            String dishClass = cursor.getString(cursor.getColumnIndex("dish_class"));
            String dish_remark = cursor.getString(cursor.getColumnIndex("dish_remark"));
            String imgPath = cursor.getString(cursor.getColumnIndex("img_path"));
            String dish_num = cursor.getString(cursor.getColumnIndex("dish_num"));
            String tableNum = cursor.getString(cursor.getColumnIndex("table_num"));
            String obj = cursor.getString(cursor.getColumnIndex("obj"));
            String checkout = cursor.getString(cursor.getColumnIndex("checkout"));
            list.add(new Menus(dishId,dishName,dishClass,imgPath,dish_num,perple,dish_remark,price,tableNum,obj,checkout));

        }
        cursor.close();
        cursor = null;
        db.close();
        return list;
    }

    public void updateList(){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.execSQL("update temp_dish_list set checkout='1'");
        db.close();
    }

    public void delectList(String obj){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.execSQL("delete from temp_dish_list where obj = '" + obj + "'");
        db.close();
    }

    public void delectAllList(){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.execSQL("delete from temp_dish_list where checkout = '0'");
        db.close();
    }


    //用于进度页面
    public void saveOrders(Order order){
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        db.execSQL("insert into orders(order_id,menu_name,visitor_num,pay_num,menu_num,img_path) values(" +
                "?,?,?,?,?,?)",new String[]{order.getOrderId(),order.getMenuName(),order.getVisitorNum(),order.getPayNum(),order.getMenuNum(),order.getImgPath()});
        db.close();

    }

    public List<Order> getOrders() {
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from orders", null);

        List<Order> list = new ArrayList<>();
        list.clear();
        while (cursor.moveToNext()) {
            String  orderd = cursor.getString(cursor.getColumnIndex("order_id"));
            String menuName = cursor.getString(cursor.getColumnIndex("menu_name"));
            String visitorNum = cursor.getString(cursor.getColumnIndex("visitor_num"));
            String payNum = cursor.getString(cursor.getColumnIndex("pay_num"));
            String menuNum = cursor.getString(cursor.getColumnIndex("menu_num"));
            String imgPath = cursor.getString(cursor.getColumnIndex("img_path"));
            Log.e("order",orderd.toString());
            list.add(new Order(orderd,menuName,visitorNum,payNum,menuNum,imgPath));

        }
        cursor.close();
        cursor = null;
        db.close();
        return list;
    }
}
