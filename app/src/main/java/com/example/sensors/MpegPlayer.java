package com.example.sensors;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegSurfaceView;

public class MpegPlayer extends AppCompatActivity {
    MjpegSurfaceView mjpeg;
    private static final int TIMEOUT = 5;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpeg_player);
        mjpeg=(MjpegSurfaceView) findViewById(R.id.mjpegplayer);
        url = getIntent().getStringExtra("url");
    }

    private DisplayMode calculateDisplayMode() {
        int orientation = getResources().getConfiguration().orientation;
        return orientation == Configuration.ORIENTATION_LANDSCAPE ?
                DisplayMode.FULLSCREEN : DisplayMode.BEST_FIT;
    }

    private void loadIpCam() {
        Mjpeg.newInstance()
                //.credential(getPreference(PREF_AUTH_USERNAME), getPreference(PREF_AUTH_PASSWORD))
                .open(url, TIMEOUT)
                .subscribe(
                        inputStream -> {
                            mjpeg.setSource(inputStream);
                            mjpeg.setDisplayMode(calculateDisplayMode());
                            mjpeg.showFps(true);
                        },
                        throwable -> {
                            Log.e(getClass().getSimpleName(), "mjpeg error", throwable);
                            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadIpCam();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mjpeg.stopPlayback();
    }
}
