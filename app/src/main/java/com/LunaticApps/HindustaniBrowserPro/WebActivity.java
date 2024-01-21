package com.LunaticApps.HindustaniBrowserPro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
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
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                String fileExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimetype);

                if(fileExtension == null || fileExtension.isEmpty()){
                    fileExtension = "mp4";
                }

                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setMimeType(mimetype);

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, fileExtension));
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

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


