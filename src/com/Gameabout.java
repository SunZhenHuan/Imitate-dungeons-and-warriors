package com;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gameabout extends Dialog {
    static int Y=0;
    Point point=new Point();
    final int Width= Toolkit.getDefaultToolkit().getScreenSize().width/2;
    final int Height=Toolkit.getDefaultToolkit().getScreenSize().height/2;
    Image image=Toolkit.getDefaultToolkit().createImage("images/image8.png").getScaledInstance(Width,Height,0);
    JTextPane About=new JTextPane();
    JLabel icon=new JLabel();
    public  Gameabout(Gameinterface1 gameinterface1,String t,boolean a)
    {
        super(gameinterface1,t,a);
        //背景图片
        icon.setSize(Width,Height);
        icon.setIcon(new ImageIcon(image));
        icon.setLocation(0,0);
        //游戏关于主体
        About.setText("游戏关于");
        About.setOpaque(false);
//        About.setBorder(BorderFactory.createLineBorder(Color.blue));
        About.setLocation(0,Y);
        About.setEditable(false);
        About.setBackground(Color.RED);
        About.setForeground(Color.yellow);
        About.setFont(new Font("姚体",Font.BOLD,30));
        About.setSize(Width,Height);
        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (Y<=490) {
                            if (e.getWheelRotation() == 1) {
                                About.setLocation(0, Y += 10);
                                System.err.println(About.getY());
                            }
                        }
                        if (Y>0) {
                            if (e.getWheelRotation() == -1)
                                About.setLocation(0, Y -= 10);
                        }
                    }
                });
                thread.start();
                }
        });

        icon.add(About);
        add(icon);
        setSize(Width,Height);
        setUndecorated(true);
        setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point newPoint=getLocation();
                setLocation(newPoint.x+e.getX()-point.x,newPoint.y+e.getY()-point.y);//窗体当前位置+拖动时的位置-按下时的位置
            }
        });
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new Gameabout();
//    }
}
