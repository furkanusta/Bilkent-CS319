package com.oo.conquest;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by eksi on 4/30/16.
 */
public class SoundManager {

    Clip clip;
    SoundManager() {
        this("data/sound.wav");
    }

    SoundManager(String path) {
        try {
            File soundFile = new File(path);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        clip.start();
    }
    public void stop() {
        clip.stop();
    }
}
