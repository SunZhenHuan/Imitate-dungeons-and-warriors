package com;

import jdk.nashorn.internal.scripts.JO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.PanelUI;
import javax.swing.plaf.multi.MultiPanelUI;
import javax.swing.plaf.synth.SynthPanelUI;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

public class Gameinterface1 extends JFrame {
    static int Y=100;
    static int Width=Toolkit.getDefaultToolkit().getScreenSize().width;
    static int Height=Toolkit.getDefaultToolkit().getScreenSize().height;
    ImageIcon buttonicon=new ImageIcon("images/选项.png");
    JLabel bglabel=new JLabel();
    JLabel Seticon=new JLabel(new ImageIcon("images/设置.png"));
    static JLabel Optionlabel=new JLabel();
    static JLabel Optionlabelone=new JLabel();
    static JLabel Optionlabeltwo=new JLabel();
    static JLabel Optionlabelthree=new JLabel();
    static JLabel Optionlabelsix=new JLabel();
    static JLabel Optionlabelfour=new JLabel();
    static JLabel Optionlabelfive=new JLabel();
    static JLabel timezone=new JLabel();
    static   Music music=new Music();
    static String[] musicArrays=new String[9];
    static Random musicnumber;
    static int rand;
    Gameinterface1 gameinterface1;
    static float Value=0f;
    static String path="images//17.wav";
    String[] StrArrays={"开始游戏","读取存档","音乐设置","游戏关于","联系作者","分辨率调整","退出"};
    static JLabel[] OptionArrays={Optionlabel,Optionlabelone,Optionlabeltwo,Optionlabelthree,Optionlabelfour,Optionlabelfive,Optionlabelsix};
    public  Gameinterface1()
    {
        //音乐播放
        //实例化八个音频文件路径
        musicArrays[0]="images/2.wav";
        musicArrays[1]="images/3.wav";
        musicArrays[2]="images/4.wav";
        musicArrays[3]="images/5.wav";
        musicArrays[4]="images/6.wav";
        musicArrays[5]="images/7.wav";
        musicArrays[6]="images/8.wav";
        musicArrays[7]="images/9.wav";
        //让这首歌出现的几率大一点
        musicArrays[8]="images/1.wav";
        //随机数0-8
        musicnumber=new Random();
        rand= musicnumber.nextInt(9);
        switch (rand)
        {
            case 0:music.adj(musicArrays[0], 0, Value);break;
            case 1:music.adj(musicArrays[1], 0, Value);break;
            case 2:music.adj(musicArrays[2], 0, Value);break;
            case 3:music.adj(musicArrays[3], 0, Value);break;
            case 4:music.adj(musicArrays[4], 0, Value);break;
            case 5:music.adj(musicArrays[5], 0, Value);break;
            case 6:music.adj(musicArrays[6], 0, Value);break;
            case 7:music.adj(musicArrays[7], 0, Value);break;
            case 8:music.adj(musicArrays[8], 0, Value);break;
        }


        //背景标签
        bglabel.setSize(Width,Height);
        bglabel.setIcon(new ImageIcon("images/image9.png"));
        this.add(bglabel);

        bglabel.add(new Period(LoginInterface.userField.getText(),1400,100,30,350,100));




        //时间标签
        timezone.setSize(220,100);
        timezone.setLocation(50,150);
        timezone.setForeground(Color.ORANGE);
        timezone.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    timezone.setText(new Date().toLocaleString());
                    timezone.setToolTipText("现在时间是"+new Date().toLocaleString());
                }
            }
        });
        thread.start();
        bglabel.add(timezone);


        //设置换壁纸
        Seticon.setSize(80,80);
        Seticon.setBorder(BorderFactory.createLineBorder(Color.RED));
        Seticon.setLocation(50,50);
        bglabel.add(Seticon);

        int []Y=new int[]{100,250,400,550,700,850,1000};
        //选项六个
        for (int i =0; i <OptionArrays.length ; i++) {
            OptionArrays[i].setToolTipText(StrArrays[i]);
            OptionArrays[i].setIcon(buttonicon);
            OptionArrays[i].setText(StrArrays[i]);
            OptionArrays[i].setForeground(Color.PINK);
            OptionArrays[i].setFont(new Font("方正新书宋简体", Font.BOLD, 30));
            OptionArrays[i].setHorizontalTextPosition(SwingUtilities.CENTER);
            OptionArrays[i].setSize(365, 85);
            OptionArrays[i].setHorizontalAlignment(SwingUtilities.CENTER);
            OptionArrays[i].setOpaque(false);
            OptionArrays[i].setLocation(800,Y[i]);
            bglabel.add(OptionArrays[i]);
            int tem = i;
            long mill = 500 + (int) Math.round(Math.random() * 1000);//0.5秒~1.5秒之间
            OptionArrays[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                        OptionArrays[tem].setCursor(new Cursor(Cursor.HAND_CURSOR));
                        music.playMusic(path, 0, 4.0f);
                }
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (tem == 0 && (e.getButton() == MouseEvent.BUTTON1)) {//开始游戏界面
                        new MapFrame();
                        music.clip.close();
                        dispose();
                    } else if (tem == 1 && (e.getButton() == MouseEvent.BUTTON1))//读取文档界面
                    {
                        JOptionPane.showMessageDialog(null, "待完工！");
                    }
                    else if (tem == 2 && (e.getButton() == MouseEvent.BUTTON1))//音乐设置
                    {
                        new Musicset(gameinterface1,"",true);
                    }
                    else if (tem == 3 && (e.getButton() == MouseEvent.BUTTON1))//游戏关于
                    {
                        new Gameabout(gameinterface1,"",true);
                    }
                    else if (tem == 4 && (e.getButton() == MouseEvent.BUTTON1))//联系作者
                    {
                        JOptionPane.showMessageDialog(null, EXIT_ON_CLOSE, "联系作者", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Toolkit.getDefaultToolkit().createImage("images/Gif1.gif").getScaledInstance(800, 500, 0)));
                    }
                    else if (tem == 5 && (e.getButton() == MouseEvent.BUTTON1))//分辨率调整
                    {
                        new Dpiinterface(gameinterface1,"",true);
                    }
                    else if (tem == 6 && (e.getButton() == MouseEvent.BUTTON1)) {//退出
                        try {
                            setSize(500, 400);
                            setLocationRelativeTo(null);
                            setCursor(new Cursor(Cursor.HAND_CURSOR));
                            Thread.sleep(mill);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        new GetMessage().ChangeValue("update game_tab set state='不在线' where id='"+LoginInterface.UUid+"'");
                        dispose();
                        music.clip.stop();
                        System.exit(0);
                    }
                }
            });
        }
        setSize(Width,Height);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("images//image5.png").getImage(),new Point(0,0),""));
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new Gameinterface1();
//    }
}
