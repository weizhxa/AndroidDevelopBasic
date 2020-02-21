package com.yourapplets.androiddevelopbasic;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.yourapplets.androiddevelopbasic.layout.ConstraintLayoutActivity;
import com.yourapplets.androiddevelopbasic.layout.FrameLayoutActivity;
import com.yourapplets.androiddevelopbasic.layout.LinearLayoutActivity;
import com.yourapplets.androiddevelopbasic.layout.RelativeLayoutActivity;

public class LayoutActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ListView listview;
    private final static String[] TITLES = new String[]{"LinearLayout(线性布局)", "FrameLayout(帧布局)", "RelativeLayout(相对布局)", "ConstraintLayout(约束布局)", "GridLayout", "TableLayout"};
    private final static Class[] Clazzs = new Class[]{LinearLayoutActivity.class, FrameLayoutActivity.class, RelativeLayoutActivity.class, ConstraintLayoutActivity.class, null, null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        init();

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
    }

    private void init() {
        listview = findViewById(R.id.listview);
        listview.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

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
            View view = LayoutInflater.from(LayoutActivity.this).inflate(R.layout.item_layout, null);
            TextView tv_item = view.findViewById(R.id.tv_item);
            tv_item.setText(TITLES[position]);
            if (Clazzs[position] == null) {
                tv_item.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Clazzs[position] != null) {
                        Intent intent = new Intent(LayoutActivity.this, Clazzs[position]);
                        startActivity(intent);
                    }
                }
            });
            return view;
        }
    }
}
