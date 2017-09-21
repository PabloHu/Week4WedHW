package com.example.admin.week4wedhw;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    MediaPlayer mediaPlayer;
boolean songLoaded = false;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Log.d(TAG, "onStartCommand: ");
//-----------

        // R.raw.alltheboys);

        String a = intent.getStringExtra("data");

        if (a.equals("play")) {
            //Toast.makeText(this, "Play working", Toast.LENGTH_SHORT).show();
/*
            if(!mediaPlayer.isLooping()) {
                mediaPlayer = MediaPlayer.create(this, R.raw.alltheboys);// Settings.System.DEFAULT_RINGTONE_URI);
                mediaPlayer.start();
            }
            else
               mediaPlayer.start();
*/
            if (songLoaded != true) {
                mediaPlayer = MediaPlayer.create(this, R.raw.alltheboys);
                songLoaded = true;
                // songSeekbar.setEnabled(true);
                //initControls();
            }

            if (mediaPlayer.isPlaying() == true)
                mediaPlayer.pause();
            else if (mediaPlayer.isPlaying() == false) {
                mediaPlayer.start();
            }



        }
        if (a.equals("pause")) {
           // Toast.makeText(this, mediaPlayer.isPlaying(), Toast.LENGTH_SHORT).show();
            if (mediaPlayer.isPlaying() == true)
                mediaPlayer.pause();
           // Toast.makeText(this, "Pause working", Toast.LENGTH_SHORT).show();
            //mediaPlayer = MediaPlayer.create(this, R.raw.alltheboys);// Settings.System.DEFAULT_RINGTONE_URI);
           // mediaPlayer.pause();

        }
        //  mediaPlayer.pause();
        //------------
        //Log.d(TAG, "onStartCommand: "+ intent.getStringExtra("data"));
        //to kill itself
        //  stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        try {
            mediaPlayer.stop();
            songLoaded = false;
        } catch (Exception e) {

        }
        //   Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }
}
