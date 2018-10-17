package nl.mira.mayla.student_portal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class WebView extends AppCompatActivity {

    private android.webkit.WebView view_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        getSupportActionBar().setTitle("Portal");

        //final Portal portalView = getIntent().getParcelableExtra(MainActivity.LINK);

        Bundle bundle = getIntent().getExtras();

        android.webkit.WebView myWebView = (android.webkit.WebView) findViewById(R.id.webview);

        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(bundle.getString(MainActivity.LINK));
    }

}
