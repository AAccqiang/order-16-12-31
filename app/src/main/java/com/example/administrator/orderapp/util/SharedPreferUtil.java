package com.example.administrator.orderapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.example.administrator.orderapp.entry.Menus;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.List;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class SharedPreferUtil {

    public static void ifFirstLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences("alter", Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putBoolean("isFirst", true);
        et.commit();
    }

    public static void getDBIsFirst(Context context) {
        SharedPreferences sp = context.getSharedPreferences("first", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isFirsts", false);
        editor.commit();
    }


    public static void saveName(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("isName", name);
        editor.commit();
    }

    public static String getName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        return sp.getString("isName", "");
    }


    //暂时保持桌号  用与结算时保持到数据库
    public static void saveTable(Context context, String tableNum) {
        SharedPreferences sp = context.getSharedPreferences("tableNums", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("tableNum", tableNum);
        editor.commit();

    }
    public static String getTable(Context context) {
        SharedPreferences sp = context.getSharedPreferences("tableNums", Context.MODE_PRIVATE);
        return sp.getString("tableNum", "");
    }

    //保存客人数
    public static void saveVisitorNum(Context context, String visitorNum) {
        SharedPreferences sp = context.getSharedPreferences("visitorNums", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("visitorNum", visitorNum);
        editor.commit();

    }
    public static String getVisitorNum(Context context) {
        SharedPreferences sp = context.getSharedPreferences("visitorNums", Context.MODE_PRIVATE);
        return sp.getString("visitorNum", "");
    }

    //保存图片路径集合 用,分开
    public static void saveStringImgPath(Context context, String imgpath) {
        SharedPreferences sp = context.getSharedPreferences("imagePaths", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("imgpath", imgpath);
        editor.commit();

    }
    public static String getStringImgPath(Context context) {
        SharedPreferences sp = context.getSharedPreferences("imagePaths", Context.MODE_PRIVATE);
        return sp.getString("imgpath", "");
    }
}
