package com.yourapplets.androiddevelopbasic;

import android.Manifest;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ListView lv_main;
    private final static String[] TITLES = new String[]{"生命周期", "布局", "基础控件", "综合案例", "高级案例"};
    private final static Class[] Clazzs = new Class[]{LifeCycleActivity.class, LayoutActivity.class, BasicControlActivity.class, CombineActivity.class, AdvancedActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        getPermission();
    }

    private void getPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permission -> {
                    if (permission.granted) {
                        return;
                    }
                    if (permission.shouldShowRequestPermissionRationale) {
                        return;
                    }
                });
    }

    private void init() {
        lv_main = findViewById(R.id.lv_main);
        lv_main.setAdapter(new MainAdapter());
    }

    class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Object getItem(int position) {
            return TITLES[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main, null);
            TextView tv_item_main = view.findViewById(R.id.tv_item_main);
            tv_item_main.setText(TITLES[position]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Clazzs[position]);
                    startActivity(intent);
                }
            });
            return view;
        }
    }
}
