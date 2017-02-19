package com.example.sensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;


public class webActivity extends AppCompatActivity {
//
//    private MjpegView mv;
//
//    private static final int MENU_QUIT = 1;
//
//    /* Creates the menu items */
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, MENU_QUIT, 0, "Quit");
//        return true;
//    }
//
//    /* Handles item selections */
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case MENU_QUIT:
//                finish();
//                return true;
//        }
//        return false;
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        String url=getIntent().getStringExtra("url");
//        setContentView(R.layout.activity_web);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        mv = new MjpegView(this);
//        setContentView(mv);
//        mv.setSource(MjpegInputStream.read(url));
//        mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
//        mv.showFps(false);
//    }
//    public void onPause() {
//        super.onPause();
//        mv.stopPlayback();
//    }
}
