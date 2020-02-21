package com.example.td3_tp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.MimeTypeFilter;

import android.media.MediaMetadata;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.music_name)
    TextView musicNameTv;

    @BindView(R.id.play_audio_button)
    ImageButton playAudioButton;

    @BindView(R.id.pause_audio_button)
    ImageButton pauseAudioButton;

    @BindView(R.id.exit_button)
    Button exitButton;

    private MediaPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        audioPlayer = MediaPlayer.create(this, R.raw.tchaikovsky);

        int duree = audioPlayer.getDuration();
        int seconds = duree / 1000;
        int minute = seconds / 60;

        TypedValue value = new TypedValue();
        getResources().getValue(R.raw.tchaikovsky, value, true);

        String nomMusic = value.string.toString().split("/")[2];
        String time = "( " + minute + ":" + (seconds -  minute*60) + " )";
        musicNameTv.setText(nomMusic + " " + time);

        playAudioButton.setOnClickListener(new PlayAudio());
        playAudioButton.setOnClickListener(new PlayAudio());
        pauseAudioButton.setOnClickListener(new PauseAudio());
        exitButton.setOnClickListener(new ExitApplication());
    }

    private class PlayAudio implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            audioPlayer.start();
        }
    }

    private class PauseAudio implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            audioPlayer.pause();
        }
    }

    private class ExitApplication implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            audioPlayer.stop();
            finish();
        }
    }
}
