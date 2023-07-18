package com.example.wvb;

import static android.webkit.PermissionRequest.RESOURCE_AUDIO_CAPTURE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

//    private static Button callButton;
    private static Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);

        getSupportActionBar().hide();


        // spinner work
        spinner = findViewById(R.id.botNameSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,  R.layout.activity_main , new String[]{"edtech", "tourism", "tesla"});
        spinner.setAdapter(arrayAdapter);


//        callButton = findViewById(R.id.callButton);

//        callButton.setOnClickListener((View view) -> {
//            EditText et = findViewById(R.id.botEditText);
//            String botName = et.getText().toString();
//
//            Intent intent = new Intent(this, VoiceBotActivity.class);
//            intent.putExtra("botName", botName);
//            startActivity(intent);
//        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Permission result", "Permission granted for microphone");
            } else {
                Log.d("Permission denied", "Permission denied for microphone");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);

            }
        }
    }
}