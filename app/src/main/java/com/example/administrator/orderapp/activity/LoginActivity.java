package com.example.administrator.orderapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.orderapp.R;
import com.example.administrator.orderapp.entry.LoginResult;
import com.example.administrator.orderapp.entry.User;
import com.example.administrator.orderapp.http.HttpManage;
import com.example.administrator.orderapp.http.RetrofitClient;
import com.example.administrator.orderapp.util.SharedPreferUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginActivity extends Activity {

    @BindView(R.id.lg_btn)Button btnLogin;
    @BindView(R.id.lg_et_username)EditText etUsername;
    @BindView(R.id.lg_et_password)EditText etPassword;

    private RetrofitClient retrofitClient;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        retrofitClient = RetrofitClient.getInstance();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

    }

    @OnClick(R.id.lg_btn)
    public void login(){
        boolean isNet = HttpManage.isNetConnected(this);
        if(!isNet){
            Toast.makeText(this, "亲，你的网络不可用！", Toast.LENGTH_SHORT).show();
            return;
        }
        final String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        final  String path =  "loginServlet?category=user&name="+username+"&paw="+password;
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "账号与密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        showPg();
        Log.e("aaa",username + "    " + password);
        Call<LoginResult> call =retrofitClient.httpApi().login(username,password);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                LoginResult loginResult = response.body();

                if(response.isSuccessful()){
                    if(loginResult.getRt().equals("200")){

                        SharedPreferences sharedPreferences = getSharedPreferences("alter",MODE_PRIVATE);
                        boolean isFirst = sharedPreferences.getBoolean("isFirst",false);
                        if(!isFirst && username.equals("login03")){
                            SharedPreferUtil.ifFirstLogin(LoginActivity.this);
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this,LoginAlterActivity.class);
                            intent.putExtra("username",username);
                            startActivity(intent);
                            finish();
                        }else{
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                          // intent.putExtra("username",username);
                            SharedPreferUtil.saveName(LoginActivity.this,username);
                            startActivity(intent);
                            finish();

                        }


                    }
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, loginResult.getRtmsg(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "访问异常", Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });
    }

    private void showPg(){
        progressDialog = ProgressDialog.show(this,null,"正在登陆");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null){
            progressDialog.dismiss();
        }

    }
}
