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
         //��ʼ������� �����������
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);    
        surfaceHolder.setFixedSize(320,240);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();
    }
    public void onClick(View v) {
        if(v == play_Button){//���²��ŵ�Ӱ��ť
            isPause = false;
            playVideo(path);
        }
        else if(v == pause_Button){//������ͣ��ť��
            if(isPause == false){//������ڲ���������ͣ
                mediaPlayer.pause();
                isPause = true;
            }
            else{//�����ͣ������������
                mediaPlayer.start();
                isPause = false;
            }
        }
    }
    private void playVideo(String strPath){//�Զ��岥��ӰƬ���� 
        if(mediaPlayer.isPlaying()==true){
            mediaPlayer.reset();
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDisplay(surfaceHolder);//����VideoӰƬ��SurfaceHolder���� 
        try{ 
            mediaPlayer.setDataSource(strPath);   //����MediaPlayer������Դ
            mediaPlayer.prepare();                  //׼��
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