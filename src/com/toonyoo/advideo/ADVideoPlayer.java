package com.toonyoo.advideo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class ADVideoPlayer extends Activity implements MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {
    public static final String TAG = "VideoPlayer";
    private VideoView mVideoView;
    private Uri mUri;
    private int mPositionWhenPaused = -1;

    private MediaController mMediaController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ad_video_player);

        //Set the screen to landscape.
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mVideoView = (VideoView)findViewById(R.id.video_view);

        //Video file
        mUri = Uri.parse(Environment.getExternalStorageDirectory() + "/funparty.mp4");

        //Create media controller，组件可以控制视频的播放，暂停，回复，seek等操作，不需要你实现
        mMediaController = new MediaController(this);
        mMediaController.setKeepScreenOn(true);
        mMediaController.setVisibility(View.INVISIBLE);
        mVideoView.setMediaController(mMediaController);
        
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {  
        	  
            @Override  
            public void onPrepared(MediaPlayer mediaPlayer) {  
            	mediaPlayer.start();
            	mediaPlayer.setLooping(true);  
            }  
        });  
        
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {  

            @Override  
            public void onCompletion(MediaPlayer mediaPlayer) {  
            	mVideoView.setVideoURI(mUri);
            	mVideoView.start();  
            }  
        });  
    }

    public void onStart() {
        // Play Video
        mVideoView.setVideoURI(mUri);
        mVideoView.start();

        super.onStart();
    }

    public void onPause() {
        // Stop video when the activity is pause.
        mPositionWhenPaused = mVideoView.getCurrentPosition();
        mVideoView.stopPlayback();

        super.onPause();
    }

    public void onResume() {
        // Resume video player
        if(mPositionWhenPaused >= 0) {
            mVideoView.seekTo(mPositionWhenPaused);
            mPositionWhenPaused = -1;
        }

        super.onResume();
    }

    public boolean onError(MediaPlayer player, int arg1, int arg2) {
        return false;
    }

    public void onCompletion(MediaPlayer mp) {
        this.finish();
    }
}