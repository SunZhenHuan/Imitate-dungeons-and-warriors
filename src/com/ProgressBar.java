package com;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


//进度条类
public class ProgressBar extends JProgressBar implements Runnable{
    int count=0;
public static int tag=0;
    public ProgressBar()
    {

        setOpaque(false);
        setValue(0);
        setMaximum(100);
        setMinimum(0);
        setSize(1400,30);
        setLocation(0,870);
        setBorderPainted(true);
        setForeground(Color.GREEN);
        setStringPainted(true);
    }

    @Override
    public void run() {
        while (true)
        {
            count=(int)((FileDownloadLogin.count1/ FileDownloadLogin.count) *100);
            setValue(count);
         //   System.out.println(count);
            setToolTipText("当前进度为"+count+"%");
            if (this.getValue()==getMaximum()) {
                { JOptionPane.showMessageDialog(null, "更新完成！", "完成", JOptionPane.WARNING_MESSAGE);
                    tag=1;
                    JOptionPane.setRootFrame(new Frame());}
                break;
            }
        }
    }
}


//JLabel time时间
class JLa extends JLabel implements Runnable{
    int RectX=50,RectY=100;
    public JLa(int X,int Y,int WID,int HEI,int SIZE)
    {
        setSize(WID,HEI);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.RED));
        setLocation(X,Y);
        setForeground(Color.PINK);
        setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,SIZE));
    }

    @Override
    public void run() {
        while (true)
        {
            RectX+=20;
               if (RectX==390)RectX=50;
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Color[]colors=new Color[]{Color.BLUE,Color.RED,Color.GREEN,Color.MAGENTA};
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timezone=s.format(new Date());
        g.setColor(Color.BLUE);
        g.setFont(new Font("姚体",Font.ITALIC,30));
        g.drawString(timezone,50,180);
        g.setColor(Color.MAGENTA);
        g.fillRect(RectX,RectY,20,20);
    }
}
