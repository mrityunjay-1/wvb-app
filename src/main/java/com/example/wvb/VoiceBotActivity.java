package com.example.wvb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class VoiceBotActivity extends AppCompatActivity {
    private WebView webView;
    private MicManager micManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_bot);

        // checking for microphone access
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
        }

        getSupportActionBar().hide();

        micManager = new MicManager(this);
        micManager.unmuteMute();

        // getting data from previous activity
        String botName = getIntent().getStringExtra("botName");

        webView = findViewById(R.id.webview);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                request.grant(request.getResources());
            // super.onPermissionRequest(request);
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.loadUrl("https://talkfusion-dev-app.oriserve.com?isBotOpen=true&botName=" + botName + "&platform=android");
    }

    @Override
    public void onBackPressed() {
        webView.evaluateJavascript("javascript:window.confirm('Do you really want to cut the call ?')", (res) -> {
            //Log.d("CONFIRM_CALL_CLOSE_RES", res);

            if (res.equals( "true")) {
                //Log.d("INSIDE_IF_BLOCK", "Inside if block after getting positive confirmation.");

                webView.evaluateJavascript("javascript:window.cutCall()", (cutCallRes) -> {
                    //Log.d("CALL_CLOSE_RES_34875", cutCallRes);

                    if (cutCallRes.equals("true")) {
                        webView.evaluateJavascript("javascript:alert('Yay! Call Closed Successfully.')", null);
                    } else {
                        webView.evaluateJavascript("javascript:alert('Something went wrong while cutting the call.')", null);
                    }
                    micManager.muteMic();
                });
            }

            super.onBackPressed();
        });
    }

    

}