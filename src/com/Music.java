package com;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.util.Random;

public class Music {
    Clip clip;
    AudioInputStream audioInput;
    FloatControl floatControl;

   public void playMusic(String musicLocation, int loop, float Value) {
        try {
            File musicPath = new File(musicLocation);
            if (musicPath.exists()) {
                audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                floatControl.setValue(Value);//最大6.026
                clip.start();
                clip.loop(loop);//loop等于0播放一次1播放两次，-1循环
                System.gc();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   public void adj(String musicLocation, int loop, float Value) {
       try {
           File musicPath = new File(musicLocation);
           if (musicPath.exists()) {
               audioInput = AudioSystem.getAudioInputStream(musicPath);
               clip = AudioSystem.getClip();
               clip.open(audioInput);
               floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

               floatControl.setValue((float) (6.026*(Value/100)));//最大6.026
               clip.start();
               clip.loop(loop);//loop等于0播放一次1播放两次，-1循环
               System.gc();
           }
       } catch (Exception ex) {
           ex.printStackTrace();
       }
    }
}
