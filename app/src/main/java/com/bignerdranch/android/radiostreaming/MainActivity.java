package com.bignerdranch.android.radiostreaming;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.io.IOException;

import com.chibde.visualizer.BarVisualizer;

public class MainActivity extends Activity {

    //BBC Radio
    private final static String stream1 = "http://bbcmedia.ic.llnwd.net/stream/bbcmedia_radio2_mf_p";
    // Pop 80s
    private final static String stream2 = "http://uk7.internet-radio.com:8226/;stream";
    // Smooth Jazz
    private final static String stream3 = "http://de1.internet-radio.com:8380/;stream";
    // Top 40
    private final static String stream4 = "http://uk7.internet-radio.com:8040/;stream";
    //Blues
    private final static String stream5 = "http://us1.internet-radio.com:8321/;stream";
    //Metal
    private final static String stream6 = "http://uk1.internet-radio.com:8294/live";
    //Alternative
    private final static String stream7 = "http://uk1.internet-radio.com:8355/;stream";
    //Piano
    private final static String stream8 = "http://us2.internet-radio.com:8046/;stream";

    private static final int AUDIO_PERMISSION_REQUEST_CODE = 102;
    private static final String[] WRITE_EXTERNAL_STORAGE_PERMS={Manifest.permission.RECORD_AUDIO};
    String current_stream;
    ImageButton play;
    ImageView logo;
    RelativeLayout loading;
    Button stream1_button;
    Button stream2_button;
    Button stream3_button;
    Button stream4_button;
    Button stream5_button;
    Button stream6_button;
    Button stream7_button;
    Button stream8_button;
    MediaPlayer mediaPlayer;
    boolean started = false;
    boolean prepared = false;
    private SeekBar volumeSeekbar;
    private boolean muted;
    private ImageButton muteButton;
    private BarVisualizer barVisualizer;
    private boolean visualized;
    private AudioManager audioManager;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_stream);
        initControls();


        logo = (ImageView) findViewById(R.id.logo);
        loading = (RelativeLayout) findViewById(R.id.loadingPanel);
        loading.setVisibility(View.INVISIBLE);

        play = (ImageButton) findViewById(R.id.audioPlayButton);
        play.setVisibility(View.INVISIBLE);
        play.setEnabled(false);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        barVisualizer = findViewById(R.id.visualizer);
        barVisualizer.setColor(Color.GREEN);
        barVisualizer.setDensity(150);

        setActionBar();
        initialize();


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (started) {
                    mediaPlayer.pause();
                    started = false;
                    play.setImageResource(R.drawable.ic_play);
                } else {
                    mediaPlayer.start();
                    started = true;
                    play.setImageResource(R.drawable.ic_pause);
                }

            }
        });


        stream1_button = (Button) findViewById(R.id.audioStreamBtn1);
        stream1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream1);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    logo.setImageResource(R.drawable.bbc);
                    play.setEnabled(true);
                    current_stream = stream1;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        stream2_button = (Button) findViewById(R.id.audioStreamBtn2);
        stream2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                play.setImageResource(R.drawable.ic_refresh_stream);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream2);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    play.setEnabled(true);
                    logo.setImageResource(R.drawable.dance_uk);
                    current_stream = stream2;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        // Christmas Stream
        stream3_button = (Button) findViewById(R.id.audioStreamBtn3);
        stream3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                play.setImageResource(R.drawable.ic_refresh_stream);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream3);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    play.setEnabled(true);
                    logo.setImageResource(R.drawable.smooth_jazz);
                    current_stream = stream3;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        stream4_button = (Button) findViewById(R.id.audioStreamBtn4);
        stream4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                play.setImageResource(R.drawable.ic_refresh_stream);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream4);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    play.setEnabled(true);
                    logo.setImageResource(R.drawable.radio_merge);
                    current_stream = stream4;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        stream5_button = (Button) findViewById(R.id.audioStreamBtn5);
        stream5_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                play.setImageResource(R.drawable.ic_refresh_stream);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream5);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    play.setEnabled(true);
                    logo.setImageResource(R.drawable.blues);
                    current_stream = stream5;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        stream6_button = (Button) findViewById(R.id.audioStreamBtn6);
        stream6_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream6);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    logo.setImageResource(R.drawable.metal);
                    play.setEnabled(true);
                    current_stream = stream6;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        stream7_button = (Button) findViewById(R.id.audioStreamBtn7);
        stream7_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream7);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    logo.setImageResource(R.drawable.alternative);
                    play.setEnabled(true);
                    current_stream = stream7;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        stream8_button = (Button) findViewById(R.id.audioStreamBtn8);
        stream8_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logo.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.VISIBLE);
                play.setVisibility(View.VISIBLE);
                if(visualized){barVisualizer.release(); }
                mediaPlayer.stop();
                mediaPlayer.reset();

                try {
                    mediaPlayer.setDataSource(stream8);
                    mediaPlayer.prepare();
                    play.setImageResource(R.drawable.ic_play);
                    logo.setImageResource(R.drawable.piano);
                    play.setEnabled(true);
                    current_stream = stream8;
                } catch (IOException io) {
                }
                if (started){
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause);
                }
                logo.setVisibility(View.VISIBLE);
                loading.setVisibility(View.INVISIBLE);
                barVisualizer.setPlayer(mediaPlayer);
                visualized =true;
            }
        });

        muteButton = (ImageButton) findViewById(R.id.mute_button);
        muteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!muted){
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0,0);
                    muteButton.setImageResource(R.drawable.ic_sound_off);
                    muted = true;
                } else{
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volumeSeekbar.getProgress(),0);
                    muteButton.setImageResource(R.drawable.ic_sound_on);
                    muted = false;
                }
            }
        });
    }

    private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar1);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
       /* if(started)
            mediaPlayer.pause();*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if(started)
            mediaPlayer.start();*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // mediaPlayer.release();
    }

    private void setActionBar(){
        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setPlayer(){
        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



    private class PlayTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mediaPlayer.setDataSource(strings[0]);
                mediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return prepared;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            play.setEnabled(true);
            play.setImageResource(R.drawable.ic_play);

        }
    }

    public void bar(View view){
        startActivity(BarVisualizer.class);
    }
    public void startActivity(Class clazz){
        startActivity(new Intent(this,clazz));
    }

    private void initialize(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(WRITE_EXTERNAL_STORAGE_PERMS, AUDIO_PERMISSION_REQUEST_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[]grantResults){
        switch (requestCode){
            case AUDIO_PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    setPlayer();
                } else{
                    this.finish();
                }
        }
    }

}