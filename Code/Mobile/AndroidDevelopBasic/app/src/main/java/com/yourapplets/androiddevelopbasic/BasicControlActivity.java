package com.yourapplets.androiddevelopbasic;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.io.File;

public class BasicControlActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_control);

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

        ScrollView scrollview = findViewById(R.id.scrollview);

        mVideoView = findViewById(R.id.videoview);
        initVideoView();

        RadioGroup rg = findViewById(R.id.rg);
        RadioButton rb1 = findViewById(R.id.rb1);
        RadioButton rb2 = findViewById(R.id.rb2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb1){
                    rb2.setChecked(false);
                }else{
                    rb1.setChecked(false);
                }
            }
        });
    }

    private void initVideoView() {
        File file = new File(Environment.getExternalStorageDirectory(), "demo.mp4");
        if (!file.exists()) {
            Toast.makeText(this, "视频不存在", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        mVideoView.setVideoPath(file.getAbsolutePath());//设置视频文件
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //视频加载完成,准备好播放视频的回调

            }
        });
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //视频播放完成后的回调

            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                //异常回调
                return false;//如果方法处理了错误，则为true；否则为false。返回false或根本没有OnErrorListener，将导致调用OnCompletionListener。
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    //信息回调
                    //                what 对应返回的值如下
                    //                public static final int MEDIA_INFO_UNKNOWN = 1;  媒体信息未知
                    //                public static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700; 媒体信息视频跟踪滞后
                    //                public static final int MEDIA_INFO_VIDEO_RENDERING_START = 3; 媒体信息\视频渲染\开始
                    //                public static final int MEDIA_INFO_BUFFERING_START = 701; 媒体信息缓冲启动
                    //                public static final int MEDIA_INFO_BUFFERING_END = 702; 媒体信息缓冲结束
                    //                public static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703; 媒体信息网络带宽（703）
                    //                public static final int MEDIA_INFO_BAD_INTERLEAVING = 800; 媒体-信息-坏-交错
                    //                public static final int MEDIA_INFO_NOT_SEEKABLE = 801; 媒体信息找不到
                    //                public static final int MEDIA_INFO_METADATA_UPDATE = 802; 媒体信息元数据更新
                    //                public static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901; 媒体信息不支持字幕
                    //                public static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902; 媒体信息字幕超时

                    return false; //如果方法处理了信息，则为true；如果没有，则为false。返回false或根本没有OnInfoListener，将导致丢弃该信息。
                }
            });
        }

        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null)
            mVideoView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.stopPlayback();//停止播放视频,并且释放
            mVideoView.suspend();//在任何状态下释放媒体播放器
        }
    }
}
