package com.felight.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends AppCompatActivity {
    private WebView wb;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wb= (WebView) findViewById(R.id.wb);
        pb=(ProgressBar)findViewById(R.id.pb);
    }

    @Override
    protected void onStart() {
        super.onStart();
        wb.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb.setVisibility(View.INVISIBLE);
            }
        });
        WebSettings webSetting =wb.getSettings();
        webSetting.setJavaScriptEnabled(true);
        wb.loadUrl("https://www.google.co.in/search?q=mahabaleshwar");
    }
}
