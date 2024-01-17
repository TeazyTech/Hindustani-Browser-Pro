package com.LunaticApps.HindustaniBrowserPro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {
    String searchKeyword;
    WebView  webView;
    ProgressBar progressBar;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.my_dark_primary));
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        progressBar = findViewById(R.id.progressBar);

        enableWebViewSettings(webView);

        Intent intent = getIntent();
        searchKeyword = intent.getStringExtra("keyword");
        assert searchKeyword != null;
        if(searchKeyword.contains(".")){
            if(searchKeyword.contains("https://")){

                try {
                    webView.loadUrl(searchKeyword);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else if(searchKeyword.contains("http://")){

                try {
                    webView.loadUrl(searchKeyword);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }else{

                try {
                    webView.loadUrl("https://"+searchKeyword);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }else{

            try {
                webView.loadUrl(getResources().getString(R.string.mainURL) + searchKeyword);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(View.GONE);
            }

        });

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent download = new Intent(Intent.ACTION_VIEW);
                download.setData(Uri.parse(url));
                startActivity(download);

            }
        });
        

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void enableWebViewSettings(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(getCacheDir().getAbsolutePath());
        settings.setDatabaseEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setSaveFormData(true);
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }


}


