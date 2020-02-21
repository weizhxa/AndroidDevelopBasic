package com.yourapplets.androiddevelopbasic;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yourapplets.androiddevelopbasic.network.LoginRequest;
import com.yourapplets.androiddevelopbasic.network.NetWorkManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WelcomeActivity extends AppCompatActivity {

    private TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TitleBar titlebar = findViewById(R.id.titlebar);
        titlebar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });

        String userName = getIntent().getStringExtra("USER_NAME");
        user_name = findViewById(R.id.user_name);

        getPermission();

        login(userName);
    }

    private void getPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.INTERNET)
                .subscribe(permission -> {
                    if (permission.granted) {
                        return;
                    }
                    if (permission.shouldShowRequestPermissionRationale) {
                        return;
                    }
                });
    }

    private void login(String userName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.parse(NetWorkManager.BASE_URL))//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .build();
        LoginRequest apiService = retrofit.create(LoginRequest.class);
        Call<LoginResponse> observable = apiService.login(userName);
        observable.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //测试数据返回
                LoginResponse loginResponse = response.body();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        user_name.setText("你好：" + loginResponse.getName());
                    }
                });
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Error", "Error");
            }
        });
    }
}
