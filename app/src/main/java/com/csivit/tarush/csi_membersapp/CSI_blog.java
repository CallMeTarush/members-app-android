package com.csivit.tarush.csi_membersapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class CSI_blog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csi_blog);
        WebView myWebView = (WebView) findViewById(R.id.id_blog);
        myWebView.loadUrl("http://www.csi-india.org/");
    }
}
