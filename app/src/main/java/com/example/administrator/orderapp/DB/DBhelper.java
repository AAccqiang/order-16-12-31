package com.example.administrator.orderapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "Dc.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists table_list(_id integer primary key autoIncrement,table_num text,table_name text,table_add text,table_size text,staff_num text)");
        db.execSQL("create table if not exists dish_list(_id integer primary key autoIncrement,dish_id text,dish_name text,price text,introduction text,dish_class text,dish_unit text,img_path text,load_position text default 0,img_download boolean default 0)");
        db.execSQL("create table if not exists temp_dish_list(_id integer primary key autoIncrement,dish_id text,dish_name text,price text,dish_class text,perple text,img_path text,dish_num text,dish_remark text,table_num text,obj text,checkout text)");
        db.execSQL("create table if not exists temp_orders(_id integer primary key autoIncrement,table_num text,dish_id text,count text default 1,remark text default '')");
        db.execSQL("create table if not exists orders(_id integer primary key autoIncrement,order_id text,menu_name text,visitor_num text,pay_num text,menu_num text,img_path text,checkout boolean default 0)");
     }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
