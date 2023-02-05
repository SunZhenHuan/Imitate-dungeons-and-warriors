package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class regis  extends JDialog {
    int green=190;
    //用户名密码框
    JTextField userField=new JTextField();
    GetMessage getMessage=new GetMessage();
    JPasswordField passwordField=new JPasswordField();
    //背景图片
    ImageIcon icon=new ImageIcon("images//注册背景.png");
    JLabel bg=new JLabel(icon);
    //性别单选按钮
    JRadioButton man=new JRadioButton("男");
    JRadioButton men=new JRadioButton("女");
    //按钮组
    ButtonGroup GenderGroup=new ButtonGroup();
    //密保问题
    JLabel keep=new JLabel("密保问题(可选)");
    JButton submit=new JButton("Submit");
    static String UUid="";
    static String gender="";
    static String Hostname="";
    static String question="";
    static String answer="";
    static int pc_number=0;
    public regis(LoginInterface l,String title,boolean a)
    {
        super(l,title,a);
        try {
            Hostname= InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //背景
        bg.setSize(889,500);
        add(bg);

        setSize(889,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        //添加用户框
        userField.setSize(230,35);
        userField.setLocation(340,100);
        userField.setOpaque(false);
        userField.setForeground(Color.BLUE);
        userField.setBorder(BorderFactory.createEtchedBorder());
        userField.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,20));
        //添加密码框
        passwordField.setSize(230,35);
        passwordField.setLocation(340,180);
        passwordField.setForeground(Color.BLUE);
        passwordField.setBorder(BorderFactory.createEtchedBorder());
        passwordField.setOpaque(false);
        passwordField.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,20));
        //男单选
        man.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,20));
        man.setSize(50,40);
        man.setForeground(Color.blue);
        man.setOpaque(false);
        man.setLocation(380,230);
        man.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                    gender = man.getText();
            }
        });
        GenderGroup.add(man);
        //女单选
        men.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,20));
        men.setSize(50,40);
        men.setOpaque(false);
        men.setForeground(Color.blue);
        men.setLocation(450,230);
        GenderGroup.add(men);
        men.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                    gender=men.getText();
            }
        });
        //密保标签
        keep.setSize(150,50);
        keep.setOpaque(false);
        keep.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,20));
        keep.setForeground(Color.magenta);
        keep.setCursor(new Cursor(Cursor.HAND_CURSOR));
        keep.setLocation(390,290);
        keep.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                    new Keepsecret(null,"密保问题",true);
            }
        });
        //添加提交按钮
        //去掉按钮文字周围的焦点框
        submit.setFocusPainted(false);
        submit.setSize(220,40);
        submit.setLocation(340,370);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setFont(new Font("",Font.BOLD,25));
        submit.setBorder(BorderFactory.createEtchedBorder());
        submit.setBackground(new Color(0,green,0));
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true)
                        {
                            try {
                                if (green<255)
                                    submit.setBackground(new Color(0,green++,0));
                                else break;
                                Thread.sleep(7);
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
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            try {
                            if (green>190)
                                submit.setBackground(new Color(0,green--,0));
                            else break;
                                Thread.sleep(7);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        });
        bg.add(submit);
        bg.add(men);
        bg.add(keep);
        bg.add(man);
        bg.add(passwordField);
        bg.add(userField);
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                {
                    //获取信息
                    String username=userField.getText().trim();//获取去掉空格的字符
                    String password=passwordField.getText().trim();
                    UUid=new UUid().toString();//uuid唯一的
                    String time=new Date().toLocaleString();
                    String state="不在线";
                    int money=100;
                    String Logintime=new Date().toLocaleString();
                    String repeat="select *from game_tab where username='"+username+"'";//用户名一样的话
                    String allow="Yes";
                    String send="insert into game_tab values('"+UUid+"','"+username+"','"+password+"','"+time+"','"+gender+"','"+state+"','"+money+"','"+Hostname+"','"+Logintime+"','"+question+"','"+answer+"')";//UUid,username,pwd,time,gender,state,money,Hostname,Logintime,question,answer
                    //判断
                    if (username.equals(""))//判断用户名或者密码是否为空
                        JOptionPane.showMessageDialog(null,"用户名不能为空！");
                    else if (password.equals(""))
                        JOptionPane.showMessageDialog(null,"密码不能为空！");
                    else if (username.length()>15)
                        JOptionPane.showMessageDialog(null,"用户名长度不能超过15位");
                    else if (password.length()>15)
                        JOptionPane.showMessageDialog(null,"密码长度不能超过15位");
                    //如果都满足
                    else if (gender.equals(""))
                    JOptionPane.showMessageDialog(null,"性别不能不选");
                    else
                    {//查注册规则
                        String sql="select *from game_rule where allow='"+allow+"'";//始终查一条allow=Yes的
                        ResultSet rs=getMessage.cha(sql);
                        try {
                            if (rs.next())
                            {
                                String rule=rs.getString("allow");//取得允许注册规则Yes
                                //判断规则是否可以注册
                                if (rule.equals("Yes"))
                                    //如果等于Yes
                                {
                                    ResultSet resultSet=getMessage.cha(repeat);
                                    if (resultSet.next())//如果查到
                                        JOptionPane.showMessageDialog(null,"已经有相同账号！请换一个试试。");
                                    else
                                    {
                                        String sql1="select *from pc_name where pc_name='"+Hostname+"'";//查主机
                                        ResultSet resultSet1=getMessage.cha(sql1);
                                        if (!resultSet1.next())//如果没有这台主机
                                        {
                                            String s="insert into pc_name values('"+Hostname+"',1)";
                                            getMessage.chaValue(s);
                                        }
                                        else //如果有
                                        {
                                            //取得count
                                            pc_number= resultSet1.getInt("count");
                                            if (pc_number==3) {
                                                JOptionPane.showMessageDialog(null, "您的主机已经最大注册个数！");
                                            }
                                            else {
                                                getMessage.register(send);//注册账号
                                                pc_number++;
                                                String s1 = "update pc_name set count='" + pc_number + "'";//改变count
                                                getMessage.ChangeValue(s1);
                                                String s2="insert into numberstate values('"+UUid+"',0)";
                                                getMessage.chaValue(s2);
                                                dispose();
                                            }
                                        }
                                    }

                                }
                                //不等于则执行这个
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"现在是未开放时间！");
                                }
                            }
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }

                    }
                }
            }
        });
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new regis(null,"注册",true);
//    }
}
