package com;

import com.sun.java.swing.plaf.motif.MotifTreeCellRenderer;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Supermarket extends JDialog {
    /**
     * @autor 孙振寰
     */
    final int JWidth=130,JHeight=40;
    final int Wid=120,Hei=50;
    int Red=170;
    JLabel tree=new JLabel();
    JPanel luckPanel=new JPanel();
    JLabel exit=new JLabel();
    JLabel[]jLabels=new JLabel[]{
            new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),
            new JLabel(),new JLabel(),new JLabel(),new JLabel(),new JLabel(),
    };
    Point[]points=new Point[]{
            new Point(520,10),new Point(660,10),new Point(520,80),new Point(660,80),new Point(520,150),
            new Point(660,150),new Point(520,220),new Point(660,220),new Point(520,290),new Point(660,290),
    };
    JLabel icon=new JLabel(new ImageIcon("images//抽奖背景.png"));
    static JButton single=new JButton("单抽");
    static JButton Fivesmoke=new JButton("五连抽");
    static JButton tensmoke=new JButton("十连抽");
    public Supermarket(MapFrame mapFrame,String title,boolean a)
    {
        super(mapFrame,title,a);
        setUndecorated(true);
        setSize(1000,563);
        setLayout(null);
        setLocationRelativeTo(null);
        //奖品
        for (int i = 0; i < jLabels.length; i++) {
            jLabels[i].setSize(Wid,Hei);
            jLabels[i].setBorder(BorderFactory.createLineBorder(Color.ORANGE));
            switch (i)
            {
                case 0:
                    jLabels[0].setLocation(points[i]);
                    break;
                case 1:
                    jLabels[1].setLocation(points[i]);
                    break;
                case 2:
                    jLabels[2].setLocation(points[i]);
                    break;
                case 3:
                    jLabels[3].setLocation(points[i]);
                    break;
                case 4:
                    jLabels[4].setLocation(points[i]);
                    break;
                case 5:
                    jLabels[5].setLocation(points[i]);
                    break;
                case 6:
                    jLabels[6].setLocation(points[i]);
                    break;
                case 7:
                    jLabels[7].setLocation(points[i]);
                    break;
                case 8:
                    jLabels[8].setLocation(points[i]);
                    break;
                case 9:
                    jLabels[9].setLocation(points[i]);
                    break;
                default: throw new IllegalStateException("Unexpected value: " + i);
            }
            luckPanel.add(jLabels[i]);
        }
        //tree图片标签
        tree.setSize(500,400);
        tree.setLocation(0,0);
        tree.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        luckPanel.add(tree);
        //单抽
        single.setSize(JWidth,JHeight);
        single.setFocusPainted(false);
        single.setBackground(new Color(Red,0,0));
        single.setLocation(80,491);
        single.setBorder(BorderFactory.createEtchedBorder());
        icon.add(single);
        single.addMouseListener(new Mouse(Red,single));
        //五连抽
        Fivesmoke.setSize(JWidth,JHeight);
        Fivesmoke.setFocusPainted(false);
        Fivesmoke.setLocation(290,491);
        Fivesmoke.setBorder(BorderFactory.createEtchedBorder());
        Fivesmoke.setBackground(new Color(Red,0,0));
        Fivesmoke.addMouseListener(new Mouse(Red,Fivesmoke));
        icon.add(Fivesmoke);
        //十连抽
        tensmoke.setSize(JWidth,JHeight);
        tensmoke.setFocusPainted(false);
        tensmoke.setLocation(500,491);
        tensmoke.setBorder(BorderFactory.createEtchedBorder());
        tensmoke.setBackground(new Color(Red,0,0));
        tensmoke.addMouseListener(new Mouse(Red,tensmoke));
        icon.add(tensmoke);


        //退出
        exit.setSize(30,50);
        exit.setLocation(965,-5);
        exit.setText("X");
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exit.setToolTipText("关闭");
        exit.setForeground(Color.blue);
        exit.setFont(new Font("",Font.PLAIN,40));
        icon.add(exit);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                    dispose();
            }
        });

        //背景
        icon.setSize(getSize());
        add(icon);
        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.err.println(e.getX()+"\t"+e.getY());
            }
        });
        //奖品面板
        luckPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        luckPanel.setOpaque(false);
        luckPanel.setLayout(null);
        luckPanel.setSize(800,400);
        luckPanel.setLocation(10,50);
        icon.add(luckPanel);
        setVisible(true);
    }
//    public static void main(String[] args) {
//        new Supermarket(null,"商城",false);
//    }
}
