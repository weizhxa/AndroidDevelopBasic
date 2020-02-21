package com.yourapplets.androiddevelopbasic.advanced;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawView extends View {
    private Path mPath;//路径
    private Paint mLinePaint = new Paint();//路径画笔
    private float mStokeWidth = 8;// 画笔线的大小
    private int mPathColor = Color.parseColor("#212121");//画笔颜色
    private int mPreX;//手指位置x
    private int mPreY;//手指位置y
    private int mColor;
    ArrayList<Integer> colors = new ArrayList<>();
    ArrayList<Path> paths = new ArrayList<>();
    private final static String TAG = "DrawView";

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context);
    }

    public void init() {//自定义初始化画笔函数
        mLinePaint.setColor(mPathColor);//设置画笔颜色
        mLinePaint.setStrokeWidth(mStokeWidth);//画笔宽度
        mLinePaint.setStyle(Paint.Style.STROKE);//画笔填充风格
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//监听滑动
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mColor = mPathColor;
            mPath = new Path();
            mPreX = (int) event.getX();
            mPreY = (int) event.getY();
            mPath.moveTo(mPreX, mPreY);
            Log.d(TAG, "onTouchEvent: down");
            colors.add(mColor);
            paths.add(mPath);
            return true;

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int endX = ((int) event.getX() + mPreX) / 2;
            int endY = ((int) event.getY() + mPreY) / 2;
            mPath.quadTo(mPreX, mPreY, endX, endY);//用贝济埃曲线
            mPreX = (int) event.getX();
            mPreY = (int) event.getY();
            Log.d(TAG, "onTouchEvent: move");
            postInvalidate();
        }


        return super.onTouchEvent(event);
    }


    //更换画笔颜色
    public void setPaintColor(String color){
       mPathColor = Color.parseColor(color);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < paths.size(); i++) {
            mLinePaint.setColor(colors.get(i));
            canvas.drawPath(paths.get(i), mLinePaint);
        }
    }

    // 清屏
    public void clear() {
        colors.clear();
        paths.clear();
        postInvalidate();
    }

    //撤销
    public void lineWithdraw() {
        if (paths.size() > 0) {
            colors.remove(colors.size() - 1);
            paths.remove(paths.size() - 1);
            postInvalidate();
        }
    }
}
