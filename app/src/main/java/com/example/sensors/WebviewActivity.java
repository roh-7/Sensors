package com.example.sensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity  {
    WebView webviewcam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webviewcam = (WebView) findViewById(R.id.webviewcam);
        WebSettings webSettings = webviewcam.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url=getIntent().getStringExtra("url");
        
    }
}
