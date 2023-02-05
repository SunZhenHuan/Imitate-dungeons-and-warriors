package com;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DownLogin extends JDialog {
   static JLabel bglabel=new JLabel();
   Point point=new Point();
   static JLabel autolabel=new JLabel();//已经下载文件标签
   Image image=Toolkit.getDefaultToolkit().createImage("ThemeImages/image8.png");
   ImageIcon icon=new ImageIcon(image.getScaledInstance(1400,900,0));
    ProgressBar progressBar=new ProgressBar();
    public  static  JLabel Downlabel=new JLabel();//正在下载文件标签
    public DownLogin(){
        setUndecorated(true);
        this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ThemeImages/image5.png").getImage(),new Point(0,0),"孙振寰"));
        this.setSize(1400,900);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        //背景
        bglabel.setSize(1400,900);
        bglabel.setFont(new Font("",Font.BOLD,25));
        bglabel.setLocation(0,0);
        bglabel.setIcon(icon);
        bglabel.setBorder(BorderFactory.createEtchedBorder(DO_NOTHING_ON_CLOSE,Color.blue,Color.BLUE));
        this.add(bglabel);


        //下载的个数
        autolabel.setSize(400,100);
        autolabel.setLocation(900,750);
        autolabel.setForeground(Color.white);
        autolabel.setFont(new Font("姚体",Font.BOLD,25));

        //正在下载文件标签
        Downlabel.setSize(400,100);
        Downlabel.setLocation(20,20);
        Downlabel.setForeground(Color.blue);
        Downlabel.setFont(new Font("姚体",Font.BOLD,25));


        bglabel.add(autolabel);
        bglabel.add(Downlabel);
        if (progressBar.getValue()==progressBar.getMaximum())dispose();
        bglabel.add(progressBar);
        Thread thread=new Thread(progressBar);
        thread.start();
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point newPoint=getLocation();
                setLocation(newPoint.x+e.getX()-point.x,newPoint.y+e.getY()-point.y);//窗体当前位置+拖动时的位置-按下时的位置
            }
        });


    }
}
