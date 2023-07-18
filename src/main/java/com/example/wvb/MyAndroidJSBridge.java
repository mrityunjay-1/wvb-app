package com.example.wvb;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class MyAndroidJSBridge {

    private Context context;

    MyAndroidJSBridge(Context context){
        this.context = context;
    }

    @JavascriptInterface
    public void getBackToPreviousActivity() {
        
    }

}
