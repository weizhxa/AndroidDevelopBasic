package com.yourapplets.androiddevelopbasic;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.yourapplets.androiddevelopbasic.advanced.DrawView;
import com.yourapplets.androiddevelopbasic.advanced.MusicService;

public class AdvancedActivity extends AppCompatActivity {

    private DrawView mDrawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        startService(new Intent(AdvancedActivity.this, MusicService.class));

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

        mDrawView = findViewById(R.id.drawview_draw_draw);
        mDrawView.clear();
        mDrawView.lineWithdraw();
        mDrawView.setPaintColor("#212121");
    }

    public void btn_play(View view) {
        Intent intent = new Intent(MusicService.ACTION_PLAY);
        sendBroadcast(intent);
    }

    public void btn_stop(View view) {
        Intent intent = new Intent(MusicService.ACTION_STOP);
        sendBroadcast(intent);
    }

    public void btn_clear(View view) {
        mDrawView.clear();
    }
}
