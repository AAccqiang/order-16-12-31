package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.LoginResult;
import com.example.administrator.orderapp.entry.User;
import com.example.administrator.orderapp.http.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/24 0024.
 */

public class LoginAlterActivity extends Activity {

    @BindView(R.id.alter_tv_username)TextView tvName;
    @BindView(R.id.alter_et_oldpass)EditText etOldPassword;
    @BindView(R.id.alter_et_newpass)EditText etNewPassword;
    @BindView(R.id.alter_et_newspass)EditText etNewsPassword;
    Intent intent;
    String name;
    RetrofitClient retrofitClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_alter);
        ButterKnife.bind(this);
        setName();
        retrofitClient = RetrofitClient.getInstance();
    }

    private void setName(){
        intent = getIntent();
        name = intent.getStringExtra("username");
        tvName.setText(name);
    }

    @OnClick(R.id.lg_btn)
    public void alter(){

        String oldPassword = etOldPassword.getText().toString();
        String newPassword = etNewPassword.getText().toString();
        String newsPassword = etNewsPassword.getText().toString();

        if(TextUtils.isEmpty(oldPassword) || TextUtils.isEmpty(newPassword)){
            Toast.makeText(this, "旧密码与新密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        if(oldPassword.equals(newPassword)){
            Toast.makeText(this, "旧密码与新密码相同，修改失败！", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!newPassword.equals(newsPassword)){
            Toast.makeText(this, "亲，重复新密码要一致！", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(name,oldPassword,newPassword);
//        final Map<String, String> map =new HashMap<String, String>();
//        map.put("name","login03");
//        map.put("opaw",oldPassword);
//        map.put("npaw",newPassword);

        Call<LoginResult> call = retrofitClient.httpApi().alter("login03",oldPassword,newPassword);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult loginResult = response.body();
                String rt = loginResult.getRt();
                if(rt.equals("200")){
                    Intent intent = new Intent(LoginAlterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginAlterActivity.this, "修改密码成功，请重新登陆！", Toast.LENGTH_SHORT).show();
                }
                Log.e("a",rt+"  " + loginResult.getRtmsg());
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e("b","bbbbbbbbbbbb");
            }
        });



    }
}
