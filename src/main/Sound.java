package main;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    Clip clip;
    URL soundurl[]=new URL[10];
    public Sound() {
        soundurl[0]=getClass().getResource("/resources/sound/Music1.wav");
        soundurl[1]=getClass().getResource("/resources/sound/shoot.wav");
        soundurl[2]=getClass().getResource("/resources/sound/boom.wav");
        soundurl[3]=getClass().getResource("/resources/sound/jump.wav");
        soundurl[4]=getClass().getResource("/resources/sound/tileBreak.wav");
    }
    public void setfile(int i) {
        try {
            AudioInputStream ais=AudioSystem.getAudioInputStream(soundurl[i]);
            try {
                clip=AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                clip.open(ais);
            } catch (LineUnavailableException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
