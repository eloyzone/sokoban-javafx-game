package com.github.eloyzone.sokobanjavafxgame.util;

import javafx.scene.media.AudioClip;

public class SoundLoader
{
    private static AudioClip audioClipCrash = null;

    public static void playCrashSound()
    {
        if (audioClipCrash == null)
            new SoundLoader().initialize();

        if (audioClipCrash != null)
            audioClipCrash.play();
    }

    private void initialize()
    {
        audioClipCrash = new AudioClip(getClass().getResource("/crash.wav").toExternalForm());
    }
}
