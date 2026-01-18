package org.example.asteroid;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    public static void playSound(){
        AudioClip sound = new AudioClip("file:asteroid-impact.mp3");
        sound.play();
    }
}
