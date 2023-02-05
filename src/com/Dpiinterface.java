package com;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.*;

public class Dpiinterface extends JDialog{
    JMenuBar Option1=new JMenuBar();//全屏菜单栏
    JMenu fullscreenn=new JMenu("全屏(窗口当前屏幕大小)");
    JMenuBar Option2=new JMenuBar();//中屏菜单栏
    JMenu midscreenn=new JMenu("中屏(1200x960)");
    JMenuBar Option3=new JMenuBar();//小屏菜单栏
    JMenu smallscreen=new JMenu(LoginInterface.userField.getText()+"你好！");
    Point point=new Point();
    JLabel confirmlabel=new JLabel("应用");
    JLabel cancel=new JLabel("取消");
    Image image=Toolkit.getDefaultToolkit().createImage("images/confirm.png");
    ImageIcon bg=new ImageIcon("images/Set.png");
    JLabel bglabel=new JLabel(bg);
    public Dpiinterface(Gameinterface1 gameinterface1,String t,boolean a)
    {
        super(gameinterface1,t,a);
        setLayout(null);
        setSize(800,450);
        setLocationRelativeTo(null);
        setUndecorated(true);

        //背景图片
        bglabel.setSize(this.getSize());
        add(bglabel);
        
        //全屏菜单
        Option1.setSize(170,50);
        Option1.setLocation(140,183);
        Option1.setBackground(Color.orange);
        Option1.setBorder(null);
        fullscreenn.setToolTipText("窗口最大化\n(当前屏幕大小)");
        fullscreenn.setSize(120,70);
        fullscreenn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fullscreenn.setFont(new Font("方正FW筑紫黑 简 B",Font.BOLD,15));
        fullscreenn.setMaximumSize(new Dimension(180,100));
        fullscreenn.setBorder(BorderFactory.createLineBorder(Color.RED));

        Option1.add(fullscreenn);
        bglabel.add(Option1);
        
        //中屏
        Option2.setSize(130,50);
        Option2.setLocation(350,183);
        Option2.setBorder(null);
        Option2.setBackground(Color.RED);
        midscreenn.setToolTipText("窗口中等大小\n(当前屏幕大小/2)");
        midscreenn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        midscreenn.setFont(new Font("方正FW筑紫黑 简 B",Font.BOLD,15));
        midscreenn.setSize(120,70);
        midscreenn.setLocation(50,50);
        midscreenn.setMaximumSize(new Dimension(120,100));
        midscreenn.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Option2.add(midscreenn);
        bglabel.add(Option2);
        
        //小屏
        Option3.setSize(120,50);
        Option3.setLocation(520,183);
        Option3.setBorder(null);
        Option3.setBackground(Color.BLUE);
        smallscreen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        smallscreen.setToolTipText("窗口最小尺寸\n(当前屏幕大小/3)");
        smallscreen.setFont(new Font("方正FW筑紫黑 简 B",Font.BOLD,15));
        smallscreen.setMaximumSize(new Dimension(120,100));
        smallscreen.setSize(120,70);
        smallscreen.setLocation(50,50);
        smallscreen.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        Option3.add(smallscreen);
        bglabel.add(Option3);


        //确认
        confirmlabel.setOpaque(false);
        confirmlabel.setSize(150,70);
        confirmlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        confirmlabel.setBorder(BorderFactory.createLineBorder(Color.blue));
        confirmlabel.setFont(new Font("姚体",Font.BOLD,25));
        confirmlabel.setForeground(Color.orange);
        confirmlabel.setIcon(new ImageIcon(image));
        confirmlabel.setHorizontalTextPosition(SwingUtilities.CENTER);
        confirmlabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmlabel.setLocation(150,300);
        bglabel.add(confirmlabel);
        ImageIcon big=new ImageIcon(image.getScaledInstance(320,191,0));
        Font font=new Font("姚体",Font.BOLD,30);
        confirmlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1)
                {
                    dispose();
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                confirmlabel.setIcon(big);
                confirmlabel.setFont(font);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                confirmlabel.setIcon(new ImageIcon(image));
                confirmlabel.setFont(new Font("姚体",Font.BOLD,25));
            }
        });

        //取消
        cancel.setOpaque(false);
        cancel.setSize(150,70);
//        cancel.setBorder(BorderFactory.createLineBorder(Color.blue));
        cancel.setFont(new Font("姚体",Font.BOLD,25));
        cancel.setForeground(Color.orange);
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancel.setIcon(new ImageIcon(image));
        cancel.setHorizontalTextPosition(SwingUtilities.CENTER);
        cancel.setHorizontalAlignment(SwingConstants.CENTER);
        cancel.setLocation(480,300);
        bglabel.add(cancel);
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                cancel.setIcon(big);
                cancel.setFont(font);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                cancel.setIcon(new ImageIcon(image));
                cancel.setFont(new Font("姚体",Font.BOLD,25));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        setVisible(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()==KeyEvent.VK_ESCAPE)System.exit(0);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getY()<getHeight()/2) {
                    point.x = e.getX();
                    point.y = e.getY();
                }

            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (e.getY()<getHeight()/2) {
                    Point newPoint = getLocation();
                    setLocation(newPoint.x + e.getX() - point.x, newPoint.y + e.getY() - point.y);
                }
            }
        });
    }

//    public static void main(String[] args) {
//       new Dpiinterface();
//    }
}
