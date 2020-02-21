package com.yourapplets.androiddevelopbasic.layout;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.yourapplets.androiddevelopbasic.R;

public class ConstraintLayoutActivity extends AppCompatActivity {

    private final static String TAG = ConstraintLayoutActivity.class.getSimpleName();
    private TitleBar titlebar;
    private float beginY = -1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  //加载Toolbar控件
        toolbar.setNavigationIcon(R.mipmap.bar_icon_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
