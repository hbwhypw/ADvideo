package com.toonyoo.advideo;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ADPlayer extends Activity implements OnClickListener,SurfaceHolder.Callback{
    //String path = "/sdcard/Nature.mp4";
    //String path = "/sdcard/Helical.mp4";
    //String path = "/sdcard/Prism.mp4";
    String path = "/sdcard/funparty~1.mp4";
    Button play_Button;
    Button pause_Button;
    boolean isPause = false;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    SurfaceView surfaceView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        play_Button = (Button) findViewById(R.id.play2_Button);
        play_Button.setOnClickListener(this);
        pause_Button = (Button) findViewById(R.id.pause2_Button);
        pause_Button.setOnClickListener(this);
        getWindow().setFormat(PixelFormat.UNKNOWN);
         //初始化相关类 设置相关属性
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);    
        surfaceHolder.setFixedSize(320,240);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();
    }
    public void onClick(View v) {
        if(v == play_Button){//按下播放电影按钮
            isPause = false;
            playVideo(path);
        }
        else if(v == pause_Button){//按下暂停按钮，
            if(isPause == false){//如果正在播放则将其暂停
                mediaPlayer.pause();
                isPause = true;
            }
            else{//如果暂停中怎继续播放
                mediaPlayer.start();
                isPause = false;
            }
        }
    }
    private void playVideo(String strPath){//自定义播放影片函数 
        if(mediaPlayer.isPlaying()==true){
            mediaPlayer.reset();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);//设置Video影片以SurfaceHolder播放 
        try{ 
            mediaPlayer.setDataSource(strPath);   //设置MediaPlayer的数据源
            mediaPlayer.prepare();                  //准备
        }
        catch (Exception e){ 
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }
    public void surfaceCreated(SurfaceHolder arg0) {
    }
    public void surfaceDestroyed(SurfaceHolder arg0) {
    }
}