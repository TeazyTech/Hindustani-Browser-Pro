package com.LunaticApps.HindustaniBrowserPro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton l1button1, l1button2, l1button3, l1button4, l1button5, l1button6;
    ImageButton l2button1, l2button2, l2button3, l2button4, l2button5, l2button6;
    ImageButton l3button1, l3button2, l3button3, l3button4, l3button5, l3button6;
    SearchView searchView;
    LinearLayout linearLayout, appsLinearLayout;
    private WebView webView;
    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.my_dark_primary));
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.newsWeb);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        setupWebView();
        setupNestedScrollView();

        linearLayout = findViewById(R.id.parentLinearLayout);
        appsLinearLayout = findViewById(R.id.appsLinearLayout);

        l1button1 = findViewById(R.id.line1Button1);
        l1button2 = findViewById(R.id.line1Button2);
        l1button3 = findViewById(R.id.line1Button3);
        l1button4 = findViewById(R.id.line1Button4);
        l1button5 = findViewById(R.id.line1Button5);
        l1button6 = findViewById(R.id.line1Button6);

        l2button1 = findViewById(R.id.line2Button1);
        l2button2 = findViewById(R.id.line2Button2);
        l2button3 = findViewById(R.id.line2Button3);
        l2button4 = findViewById(R.id.line2Button4);
        l2button5 = findViewById(R.id.line2Button5);
        l2button6 = findViewById(R.id.line2Button6);

        l3button1 = findViewById(R.id.line3Button1);
        l3button2 = findViewById(R.id.line3Button2);
        l3button3 = findViewById(R.id.line3Button3);
        l3button4 = findViewById(R.id.line3Button4);
        l3button5 = findViewById(R.id.line3Button5);
        l3button6 = findViewById(R.id.line3Button6);

        l1button1.setOnClickListener(this);
        l1button2.setOnClickListener(this);
        l1button3.setOnClickListener(this);
        l1button4.setOnClickListener(this);
        l1button5.setOnClickListener(this);
        l1button6.setOnClickListener(this);

        l2button1.setOnClickListener(this);
        l2button2.setOnClickListener(this);
        l2button3.setOnClickListener(this);
        l2button4.setOnClickListener(this);
        l2button5.setOnClickListener(this);
        l2button6.setOnClickListener(this);

        l3button1.setOnClickListener(this);
        l3button2.setOnClickListener(this);
        l3button3.setOnClickListener(this);
        l3button4.setOnClickListener(this);
        l3button5.setOnClickListener(this);
        l3button6.setOnClickListener(this);

        searchView = findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setIconified(false);

            }
        });

        sendSearchToNextScreen(searchView);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupNestedScrollView() {
        nestedScrollView.setOnTouchListener((v, event) -> {
            // Dispatch the touch event to the WebView
            webView.dispatchTouchEvent(event);
            return false;
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        webView.setBackgroundColor(0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        try {
            webView.loadUrl(getResources().getString(R.string.newsURL));
        }catch (Exception e){
            e.printStackTrace();
        }

        webView.setWebViewClient(new WebViewClient());
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setBuiltInZoomControls(true);

        settings.setDatabaseEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent download = new Intent(Intent.ACTION_VIEW);
                download.setData(Uri.parse(url));
                startActivity(download);

            }
        });

    }

    private void sendSearchToNextScreen(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("keyword", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, WebActivity.class);
        int id = v.getId();
        if(id==R.id.line1Button1) {
            intent.putExtra("keyword", getResources().getString(R.string.l1Button1URL));
            startActivity(intent);
        } else if (id==R.id.line1Button2) {
            intent.putExtra("keyword", getResources().getString(R.string.l1Button2URL));
            startActivity(intent);
        }else if (id==R.id.line1Button3) {
            intent.putExtra("keyword", getResources().getString(R.string.l1Button3URL));
            startActivity(intent);
        }else if (id==R.id.line1Button4) {
            intent.putExtra("keyword", getResources().getString(R.string.l1Button4URL));
            startActivity(intent);
        }else if (id==R.id.line1Button5) {
            intent.putExtra("keyword", getResources().getString(R.string.l1Button5URL));
            startActivity(intent);
        }else if (id==R.id.line1Button6) {
            intent.putExtra("keyword", getResources().getString(R.string.l1Button6URL));
            startActivity(intent);
        } else if (id==R.id.line2Button1) {
            intent.putExtra("keyword", getResources().getString(R.string.l2Button1URL));
            startActivity(intent);
        }else if (id==R.id.line2Button2) {
            intent.putExtra("keyword", getResources().getString(R.string.l2Button2URL));
            startActivity(intent);
        }else if (id==R.id.line2Button3) {
            intent.putExtra("keyword", getResources().getString(R.string.l2Button3URL));
            startActivity(intent);
        }else if (id==R.id.line2Button4) {
            intent.putExtra("keyword", getResources().getString(R.string.l2Button4URL));
            startActivity(intent);
        }else if (id==R.id.line2Button5) {
            intent.putExtra("keyword", getResources().getString(R.string.l2Button5URL));
            startActivity(intent);
        }else if (id==R.id.line2Button6) {
            intent.putExtra("keyword", getResources().getString(R.string.l2Button6URL));
            startActivity(intent);
        } else if (id==R.id.line3Button1) {
            intent.putExtra("keyword", getResources().getString(R.string.l3Button1URL));
            startActivity(intent);
        }else if (id==R.id.line3Button2) {
            intent.putExtra("keyword", getResources().getString(R.string.l3Button2URL));
            startActivity(intent);
        }else if (id==R.id.line3Button3) {
            intent.putExtra("keyword", getResources().getString(R.string.l3Button3URL));
            startActivity(intent);
        }else if (id==R.id.line3Button4) {
            intent.putExtra("keyword", getResources().getString(R.string.l3Button4URL));
            startActivity(intent);
        }else if (id==R.id.line3Button5) {
            intent.putExtra("keyword", getResources().getString(R.string.l3Button5URL));
            startActivity(intent);
        }else if (id==R.id.line3Button6) {
            intent.putExtra("keyword", getResources().getString(R.string.l3Button6URL));
            startActivity(intent);
        }
    }
}