package com;

import javax.swing.*;
import java.awt.*;

class Jlb extends JLabel implements Runnable{
    int i=20+(int) Math.round(Math.random()*10);//20---40
    public Jlb()
    {
        setVisible(true);
        setForeground(Color.RED);
        setFont(new Font("姚体",Font.ITALIC,30));
        setLocation(0,0);
        setSize(1280,720);
    }

    @Override
    public void run() {
        while (i>=0)
        {
            try {
                if (i!=0)
                    repaint();
                else {
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i--;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.setFont(new Font("姚体",Font.BOLD,40));
        if (GetMessage.isConnection()) {
            g.drawString("数据库连接成功！" + i, 500, 360);
            g.setColor(Color.ORANGE);
            g.setFont(new Font("姚体",Font.BOLD,40));
            g.drawString("点击窗口任意地方关闭",500,500);
            if (i==1) {
                g.setColor(Color.red);
                g.setFont(new Font("姚体",Font.BOLD,40));
                g.drawString("连接超时，请点击面板任意位置进入",480,650);
            }
        }
        else{
            g.setFont(new Font("姚体",Font.BOLD,40));
            g.drawString("请检查网络设置" + i, 50, 50);
            g.setColor(Color.ORANGE);
            g.setFont(new Font("姚体",Font.BOLD,40));
            g.drawString("点击窗口任意地方关闭",50,100);
            if (i<=1)
                System.exit(0);
        }


    }
}