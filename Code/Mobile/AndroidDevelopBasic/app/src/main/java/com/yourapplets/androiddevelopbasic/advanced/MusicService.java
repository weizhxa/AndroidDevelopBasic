package com.yourapplets.androiddevelopbasic.advanced;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MusicService extends Service {

    public final static String ACTION_PLAY = "com.yourapplets.androiddevelopbasic.advanced.MusicService.ACTION_PLAY";
    public final static String ACTION_STOP = "com.yourapplets.androiddevelopbasic.advanced.MusicService.ACTION_STOP";

    private final static String PATH = Environment.getExternalStorageDirectory() + File.separator + "王菲 - 红豆 (伴奏).mp3";

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    try {
                        play();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ACTION_STOP:
                    stop();
                    break;
            }
        }
    };

    public MusicService() {
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_PLAY);
        filter.addAction(ACTION_STOP);
        registerReceiver(receiver, filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver();
        return super.onStartCommand(intent, flags, startId);
    }

    public void play() throws IOException {
        File tempFile = new File(PATH);
        FileInputStream fis = new FileInputStream(tempFile);
        mediaPlayer.reset();
        mediaPlayer.setDataSource(fis.getFD());
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        unregisterReceiver(receiver);
    }
}
