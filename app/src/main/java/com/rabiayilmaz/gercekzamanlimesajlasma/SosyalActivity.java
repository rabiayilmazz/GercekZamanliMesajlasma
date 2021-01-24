package com.rabiayilmaz.gercekzamanlimesajlasma;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class SosyalActivity extends AppCompatActivity {

    String url;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosyal);
        url = getIntent().getExtras().getString("instagram");
        url = getIntent().getExtras().getString("twitter");
        Log.i("deneme", url);
        webView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        /*if(url.equals("instagram")) {
            webView.loadUrl("https://www.instagram.com/teknotower");
            webView.setWebViewClient(new WebViewClient());
        }else if(url.equals("twitter")){
            webView.loadUrl("https://www.twitter.com/tekno_tower");
            webView.setWebViewClient(new WebViewClient());
        }*/

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        } else
            super.onBackPressed();
    }
}