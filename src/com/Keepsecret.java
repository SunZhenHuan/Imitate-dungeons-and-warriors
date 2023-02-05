package com;

import com.sun.java.swing.plaf.windows.WindowsToggleButtonUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.synth.SynthButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//密保类
public class Keepsecret extends JDialog {
    String[] iconpath=new String[]{"images//找回密码背景.gif"};
    int green=200;
    int H=0;
    int flag=1;
    static JLabel hint1=new JLabel();
    static JLabel hint2=new JLabel();
    static JTextField question=new JTextField();//问题
    static JTextField answer=new JTextField();//答案
    static JButton Ok=new JButton("确认");
    public Keepsecret(regis r,String title,boolean a)
    {
        super(r,title,a);//初始化
        //添加提示
        //150,40,45,85,
        hint1.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,15));
        hint1.setVisible(false);
        hint1.setSize(220,40);
        hint1.setLocation(45,85);
        hint1.setForeground(Color.BLACK);
        //150,40,45,185
        hint2.setSize(220,40);
        hint2.setVisible(false);
        hint2.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,15));
        hint2.setLocation(45,185);
        hint2.setForeground(Color.black);
        //添加背景
        add(AllGetMethod.GetJlabel(iconpath[0],800,370,0,0));
        //添加按钮
        Ok.setSize(215,50);
        Ok.setBackground(new Color(100,green,100));
        Ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Ok.setLocation(45,280);
        Ok.setBorder(BorderFactory.createEtchedBorder());

        //问题
        question.setSize(235,50);
        question.setText("");
        question.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,15));
        question.setCaretPosition(question.getDocument().getLength());
        question.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),"密保问题",0,0,new Font("字酷堂匡山楷书 简",Font.PLAIN,15),Color.BLUE));
        question.setLocation(Ok.getLocation().x-10,50);
        question.setOpaque(false);
        question.setForeground(Color.black);
        question.setToolTipText("设置问题");
        question.setFont(new Font("姚体",Font.PLAIN,20));

        //答案
        answer.setSize(235,50);
        answer.setCaretPosition(answer.getDocument().getLength());
        answer.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,15));
        answer.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),"密保答案",0,0,new Font("字酷堂匡山楷书 简",Font.PLAIN,15),Color.BLUE));
        answer.setLocation(Ok.getLocation().x-10,150);
        answer.setOpaque(false);
        answer.setForeground(Color.black);
        answer.setToolTipText("设置答案");
        answer.setText("");
        answer.setFont(new Font("姚体",Font.PLAIN,20));
        //在AllGetMethod里面的JLabel方法里面添加了add(question);
        //        add(answer);


        Ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //密保问题框信息
                String question1=question.getText().trim();
                //密保答案框信息
                String answer1=answer.getText().trim();
                if (e.getButton()==MouseEvent.BUTTON1)
                {
                    if (question1.equals("")||answer1.equals(""))JOptionPane.showMessageDialog(null,"密保问题或密保答案不能为空！");
                    else if (question1.length()>12||answer1.length()>12)JOptionPane.showMessageDialog(null,"密保问题或密保答案长度不能超过12位！");
                    else {
                        regis.question=question1;
                        regis.answer=answer1;
                        JOptionPane.showMessageDialog(null,"你的密保已经成功设置！");
                        dispose();
                    }
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                flag=0;
                //颜色加深
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            try {
                                if (green<255&&flag==0) {
                                    Ok.setBackground(new Color(0, green++, 0));
                                    }
                                else   break;
                                Thread.sleep(5);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                flag=1;
                //颜色减淡
                Thread thread2=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            try {
                                if (green>200&&flag==1) {
                                    Ok.setBackground(new Color(100, green--, 100));
                                }
                                else break;
                                Thread.sleep(5);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    }
                });
                thread2.start();
            }});
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        if (H<402) {
                            setSize(600, H +=2);
                            setLocationRelativeTo(null);
                        }
                        else break;
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        setResizable(false);
        setLayout(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                int Op=JOptionPane.showConfirmDialog(null,"是否要退出密保设置？");
                if (Op==JOptionPane.OK_OPTION)
                    dispose();
            }
        });

    }

//    public static void main(String[] args) {
//        new Keepsecret(null,"",true);
//    }
}
