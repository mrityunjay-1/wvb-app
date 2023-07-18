package com.example.wvb;

import android.content.Context;
import android.media.AudioManager;

public class MicManager {

    private AudioManager audioManager;

    public MicManager (Context context) {
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void muteMic () {
        audioManager.setMicrophoneMute(true);
    }

    public void unmuteMute() {
        audioManager.setMicrophoneMute(false);
    }
}
