package com.example.hwang1.avengerslabsoak;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoCore extends Activity implements
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback {

    String[] videos = new String[] { "3gp.3gp", "avi.avi", "mp4.mp4", "wmv.wmv" };
    public static final String PREFS_NAME = "MyPrefsFile";


    private static final String TAG = "MediaPlayer";
    private static final int[] SURFACE_RES_IDS = { R.id.video_1_surfaceview, R.id.video_2_surfaceview, R.id.video_3_surfaceview, R.id.video_4_surfaceview };

    private MediaPlayer[] mMediaPlayers = new MediaPlayer[SURFACE_RES_IDS.length];
    private SurfaceView[] mSurfaceViews = new SurfaceView[SURFACE_RES_IDS.length];
    private SurfaceHolder[] mSurfaceHolders = new SurfaceHolder[SURFACE_RES_IDS.length];
    private boolean[] mSizeKnown = new boolean[SURFACE_RES_IDS.length];
    private boolean[] mVideoReady = new boolean[SURFACE_RES_IDS.length];

    @Override protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        String allinone_br_stat = prefs.getString("allinone_br_stat", "0");
        if(allinone_br_stat.equals("00"))
        {
            Log.d("Done", "VideoCore");

            Intent intent = new Intent(getApplicationContext(), GraphicalApps.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_core);

        // create surface holders
        for (int i=0; i<mSurfaceViews.length; i++) {
            mSurfaceViews[i] = (SurfaceView) findViewById(SURFACE_RES_IDS[i]);
            mSurfaceHolders[i] = mSurfaceViews[i].getHolder();
            mSurfaceHolders[i].addCallback(this);
            mSurfaceHolders[i].setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
    }

    public void onBufferingUpdate(MediaPlayer player, int percent) {
        Log.d(TAG, "MediaPlayer(" + indexOf(player) + "): onBufferingUpdate percent: " + percent);
    }

    public void onCompletion(MediaPlayer player) {
        Log.d(TAG, "MediaPlayer(" + indexOf(player) + "): onCompletion called");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.start();
    }

    public void onVideoSizeChanged(MediaPlayer player, int width, int height) {
        Log.v(TAG, "MediaPlayer(" + indexOf(player) + "): onVideoSizeChanged called");
        if (width == 0 || height == 0) {
            Log.e(TAG, "invalid video width(" + width + ") or height(" + height + ")");
            return;
        }

        int index = indexOf(player);
        if (index == -1) return; // sanity check; should never happen
        mSizeKnown[index] = true;
        if (mVideoReady[index] && mSizeKnown[index]) {
            startVideoPlayback(player);
        }
    }

    public void onPrepared(MediaPlayer player) {
        Log.d(TAG, "MediaPlayer(" + indexOf(player) + "): onPrepared called");

        int index = indexOf(player);
        if (index == -1) return; // sanity check; should never happen
        mVideoReady[index] = true;
        if (mVideoReady[index] && mSizeKnown[index]) {
            startVideoPlayback(player);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int i, int j, int k) {
        Log.d(TAG, "SurfaceHolder(" + indexOf(holder) + "): surfaceChanged called");
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "SurfaceHolder(" + indexOf(holder) + "): surfaceDestroyed called");
    }


    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "SurfaceHolder(" + indexOf(holder) + "): surfaceCreated called");

        int index = indexOf(holder);
        if (index == -1) return; // sanity check; should never happen
        try {
            mMediaPlayers[index] = new MediaPlayer();
            AssetFileDescriptor afd;

            /*
            Random rand = new Random();
            int randomNum = rand.nextInt(4);
            int[] rand_list = new int[4];
            for(int count = 0; count < 4; count++)
            {
                if()
                rand_list[count] = randomNum;
            }*/

            if(index == 0){
                //Random rand = new Random();
                //int randomNum = rand.nextInt(4);
                //afd = getAssets().openFd(videos[randomNum]);
                afd = getAssets().openFd(videos[0]);

                mMediaPlayers[index].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mMediaPlayers[index].setDisplay(mSurfaceHolders[index]);
                mMediaPlayers[index].prepare();
                mMediaPlayers[index].setOnBufferingUpdateListener(this);
                mMediaPlayers[index].setOnCompletionListener(this);
                mMediaPlayers[index].setOnPreparedListener(this);
                mMediaPlayers[index].setOnVideoSizeChangedListener(this);
                mMediaPlayers[index].setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            else if(index == 1){
                //Random rand = new Random();
                //int randomNum = rand.nextInt(4);
                //afd = getAssets().openFd(videos[randomNum]);
                afd = getAssets().openFd(videos[1]);

                mMediaPlayers[index].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mMediaPlayers[index].setDisplay(mSurfaceHolders[index]);
                mMediaPlayers[index].prepare();
                mMediaPlayers[index].setOnBufferingUpdateListener(this);
                mMediaPlayers[index].setOnCompletionListener(this);
                mMediaPlayers[index].setOnPreparedListener(this);
                mMediaPlayers[index].setOnVideoSizeChangedListener(this);
                mMediaPlayers[index].setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            else if(index == 2){
                //Random rand = new Random();
                //int randomNum = rand.nextInt(4);
                //afd = getAssets().openFd(videos[randomNum]);
                afd = getAssets().openFd(videos[2]);

                mMediaPlayers[index].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mMediaPlayers[index].setDisplay(mSurfaceHolders[index]);
                mMediaPlayers[index].prepare();
                mMediaPlayers[index].setOnBufferingUpdateListener(this);
                mMediaPlayers[index].setOnCompletionListener(this);
                mMediaPlayers[index].setOnPreparedListener(this);
                mMediaPlayers[index].setOnVideoSizeChangedListener(this);
                mMediaPlayers[index].setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            else if(index == 3){
                //Random rand = new Random();
                //int randomNum = rand.nextInt(4);
                //afd = getAssets().openFd(videos[randomNum]);
                afd = getAssets().openFd(videos[3]);

                mMediaPlayers[index].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mMediaPlayers[index].setDisplay(mSurfaceHolders[index]);
                mMediaPlayers[index].prepare();
                mMediaPlayers[index].setOnBufferingUpdateListener(this);
                mMediaPlayers[index].setOnCompletionListener(this);
                mMediaPlayers[index].setOnPreparedListener(this);
                mMediaPlayers[index].setOnVideoSizeChangedListener(this);
                mMediaPlayers[index].setAudioStreamType(AudioManager.STREAM_MUSIC);
            }


            //AssetFileDescriptor afd = getAssets().openFd("mp4.mp4");
            /*
            mMediaPlayers[index].setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mMediaPlayers[index].setDisplay(mSurfaceHolders[index]);
            mMediaPlayers[index].prepare();
            mMediaPlayers[index].setOnBufferingUpdateListener(this);
            mMediaPlayers[index].setOnCompletionListener(this);
            mMediaPlayers[index].setOnPreparedListener(this);
            mMediaPlayers[index].setOnVideoSizeChangedListener(this);
            mMediaPlayers[index].setAudioStreamType(AudioManager.STREAM_MUSIC);*/
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    @Override protected void onPause() {
        super.onPause();
        releaseMediaPlayers();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayers();
    }

    private void releaseMediaPlayers() {
        for (int i=0; i<mMediaPlayers.length; i++) {
            if (mMediaPlayers[i] != null) {
                mMediaPlayers[i].release();
                mMediaPlayers[i] = null;
            }
        }
    }


    private void startVideoPlayback(MediaPlayer player) {
        Log.v(TAG, "MediaPlayer(" + indexOf(player) + "): startVideoPlayback");
        try {
            Thread.sleep((indexOf(player)+1)*2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.start();
    }

    private int indexOf(MediaPlayer player) {
        for (int i=0; i<mMediaPlayers.length; i++) if (mMediaPlayers[i] == player) return i;
        return -1;
    }

    private int indexOf(SurfaceHolder holder) {
        for (int i=0; i<mSurfaceHolders.length; i++) if (mSurfaceHolders[i] == holder) return i;
        return -1;
    }



    }

