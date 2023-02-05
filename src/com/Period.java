package com;

import com.sun.java.swing.plaf.motif.MotifTreeUI;
import com.sun.java.swing.plaf.windows.WindowsTextPaneUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicDesktopIconUI;
import javax.swing.plaf.basic.BasicFormattedTextFieldUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Period extends JTextArea
{
    Music music=new Music();
    static int[]rgb=new int[]{0,0,255};
    static int blue;
    static int red;
    static int green;
    static int alpha;
    static int tem=1;
    //0-5点是凌晨,早上5-7点,上午8-10点,中午11-12点,下午13-17点,傍晚18-20点,晚上21-23
   static String[] Period={
            "现在是凌晨哦,请注意休息时间！",
            "早上好！又是新的一天.\n今天也要元气满满哦~",
            "上午好,别懒床，记得吃早餐.",
            "中午好,中午要吃好点哦",
            "下午好,记得喝下午茶.",
            "吃完晚饭记得出去散散步",
            "晚上注意早点休息哦~"
                        } ;

    public Period(String flag,int x,int y,int size,int Width,int Height)
    {
        setSize(Width,Height);
        setLocation(x,y);
        Thread thread=new Thread(() -> {
            red=rgb[new Random().nextInt(rgb.length)];
            green=rgb[new Random().nextInt(rgb.length)];
            blue=rgb[new Random().nextInt(rgb.length)];
            while (true)
            {
                try {
                if (alpha<255&&tem==1) {
                    setForeground(new Color(red,green,blue,alpha++).brighter());
                    if (alpha==254)tem=0;
                }
                else if (tem==0)
                {
                    setForeground(new Color(red,green,blue,alpha--).brighter());
                    if (alpha==0)tem=1;
                }
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        setFont(new Font("芋泥啵啵茶",Font.BOLD,size));
        setOpaque(false);
        setEditable(false);
        setLineWrap(true);
        int time=new Date().getHours();
        if (time>=0&&time<5) {
            setText(flag + "\n" + Period[0]);
            music.playMusic("images/16.wav",0,6.0f);
        }
        else if (time>=5&&time<8) {
            setText(flag + "\n" + Period[1]);
            music.playMusic("images/10.wav",0,6.0f);
        }
        else if (time>=8&&time<11) {
            setText(flag + "\n" + Period[2]);
            music.playMusic("images/11.wav",0,6.0f);
        }
        else if (time>=11&&time<13) {
            setText(flag + "\n" + Period[3]);
            music.playMusic("images/12.wav",0,6.0f);
        }
        else if (time>=13&&time<18) {
            setText(flag + "\n" + Period[4]);
            music.playMusic("images/13.wav",0,6.0f);
        }
        else if (time>=18&&time<20) {
            setText(flag + "\n" + Period[5]);
            music.playMusic("images/14.wav",0,6.0f);
        }
        else if (time>=20&&time<=23) {
            setText(flag + "\n" + Period[6]);
            music.playMusic("images/15.wav",0,6.0f);
        }
        setVisible(true);
    }
}
