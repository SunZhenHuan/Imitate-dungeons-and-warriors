package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;

public class Frame extends JFrame {
    GetMessage getMessage = new GetMessage();
    Jlb jlb;
    Point point=new Point();
    JLabel bg = new JLabel();
    public Frame() {
        jlb=new Jlb();
        setUndecorated(true);
        Container container = getContentPane();
        setResizable(false);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("images/image5.png").getImage(), new Point(10, 10), "孙振寰"));
        pack();
        container.setLayout(null);
        bg.setSize(1280, 720);
        bg.setIcon(new ImageIcon("images/image1.png"));
        bg.add(jlb);
        Thread thread = new Thread(jlb);
        thread.start();
        container.add(bg);
        setVisible(true);
        setLocation(300, 200);
        setSize(1280, 720);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose();
            }
        });
        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int leftButton = e.getButton();
                if (leftButton == MouseEvent.BUTTON1) {
                    if (GetMessage.isConnection()) {
                        dispose();
                        JOptionPane.setRootFrame(new LoginInterface());
                    } else
                        System.exit(0);
                }
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
