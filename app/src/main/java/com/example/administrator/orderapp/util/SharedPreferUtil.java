package com.example.administrator.orderapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class SharedPreferUtil {

    public static void ifFirstLogin(Context context){
        SharedPreferences sp = context.getSharedPreferences("alter",Context.MODE_PRIVATE);
        SharedPreferences.Editor et = sp.edit();
        et.putBoolean("isFirst",true);
        et.commit();
    }

    public static void getDBIsFirst(Context context){
        SharedPreferences sp = context.getSharedPreferences("first",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isFirsts",false);
        editor.commit();
    }


    public static void saveName(Context context,String name){
        SharedPreferences sp = context.getSharedPreferences("name",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("isName",name);
        editor.commit();
    }

    public static String getName(Context context){
        SharedPreferences sp = context.getSharedPreferences("name",Context.MODE_PRIVATE);

       return   sp.getString("isName","");
    }

}
