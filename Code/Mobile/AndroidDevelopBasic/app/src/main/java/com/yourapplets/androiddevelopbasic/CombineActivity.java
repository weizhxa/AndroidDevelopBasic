package com.yourapplets.androiddevelopbasic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class CombineActivity extends AppCompatActivity {

    private EditText et_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);

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

        et_username = findViewById(R.id.et_username);
    }

    public void btn_login(View view) {
        Intent intent = new Intent();
        intent.setClass(CombineActivity.this, WelcomeActivity.class);
        intent.putExtra("USER_NAME", et_username.getText().toString().trim());
        startActivity(intent);
        finish();
    }
}
