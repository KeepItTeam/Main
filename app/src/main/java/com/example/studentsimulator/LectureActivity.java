package com.example.studentsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class LectureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);
        String videoSource ="https://vk.com/doc134831640_595720901?hash=4b9d4307244bbe579c&dl=a685534228f1aca283";
        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        videoView.setVideoURI(Uri.parse(videoSource));
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus(0);
    }
}