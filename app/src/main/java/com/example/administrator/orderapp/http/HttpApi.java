package com.example.administrator.orderapp.http;

import com.example.administrator.orderapp.entry.LoginResult;
import com.example.administrator.orderapp.entry.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/12/23 0023.
 */

public interface HttpApi {
    //登陆
    @GET("loginServlet?category=user")
    Call<LoginResult> login(@Query("name") String username, @Query("paw") String password);
    //修改密码
//    @POST("loginServlet")
//    Call<LoginResult> alter(@Body User user);
    @FormUrlEncoded
    @POST("loginServlet")
    Call<LoginResult> alter(@Field("name")String name,@Field("opaw")String opaw,@Field("npaw")String npaw);
    @GET("{imgPath}")
    Call<String> loadImg(@Path("imgPath") String imgPath);
}
