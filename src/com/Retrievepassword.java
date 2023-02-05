package com;

import com.sun.java.swing.plaf.motif.MotifTextUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicTextUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Retrievepassword extends JDialog {
    static final int Hei=480,Wid=800;
    GetMessage getMessage=new GetMessage();
    JLabel hint1=new JLabel();
    String text="请先输入忘记密码账户的用户名";
    JButton submit=new JButton("修改");
    JTextField Password=new JTextField();
    JLabel right=new JLabel(AllGetMethod.GetIcon("images//对.png",100,47,0));
    ImageIcon wrong=AllGetMethod.GetIcon("images//错.png",100,47,0);
    JTextField newPassword=new JTextField();
    JLabel hint=new JLabel();
    JTextField Scanf=new JTextField("请输入你要找回的账号");
    ImageIcon bgicon=new ImageIcon("images//密码找回.png");
    JLabel bg=new JLabel(bgicon);
    static int green=100,green1=100;
    int pwdWid=0,newpwdWid=0;
    static int flag=1,flag1=1;
    static  JButton Ok=new JButton("查找该名字");
    public Retrievepassword(LoginInterface loginInterface,String title,boolean a)
    {
        super(loginInterface,title,a);//初始化窗体
        //添加背景
        bg.setSize(800,450);
        bg.setLocation(0,0);
        //hint账户名
        hint.setSize(400,30);
        hint.setForeground(Color.CYAN);
        hint.setVisible(false);
        hint.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,18));
        hint.setLocation(280,120);
        //用于显示密保信息
        hint1.setSize(400,30);
        hint1.setForeground(Color.orange);
        hint1.setVisible(false);
        hint1.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,18));
        hint1.setLocation(280,180);
        bg.add(hint1);
        bg.add(hint);
        //输入框
        Scanf.setSize(300,40);
        Scanf.setOpaque(false);
        Scanf.setForeground(Color.RED);
        Scanf.setCaretColor(Color.BLUE);
        Scanf.setLocation(260,70);
        Scanf.setCaretPosition(Scanf.getDocument().getLength());
        Scanf.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED,Color.black,Color.BLUE,Color.black,Color.magenta));
        Scanf.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        bg.add(Scanf);
        //对。图标
        right.setSize(60,47);
//        right.setBorder(BorderFactory.createLineBorder(Color.blue));
        right.setLocation(560,215);
        right.setVisible(false);
        bg.add(right);
        //错。图片

        //答案框
        Password.setOpaque(false);
        Password.setForeground(Color.RED);
        Password.setCaretColor(Color.BLUE);
        Password.setText("输入密保答案");
        Password.setToolTipText("答案验证");
        Password.setLocation(260,220);
        Password.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,new Color(0,0,0,0),new Color(255,0,0,200)));
        Password.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        bg.add(Password);
        //新密码
        newPassword.setSize(newpwdWid,40);
        newPassword.setOpaque(false);
        newPassword.setText("请输入您的新密码");
        newPassword.setForeground(Color.orange);
        newPassword.setCaretColor(Color.BLUE);
        newPassword.setToolTipText("新密码");
        newPassword.setLocation(260,290);
        newPassword.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,new Color(0,0,0,0),new Color(0,0,255,200)));
        newPassword.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        bg.add(newPassword);
        submit.setSize(200,50);
        submit.setBackground(new Color(0,green,0));
        submit.setLocation(310,380);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.setBorder(BorderFactory.createEtchedBorder());
        bg.add(submit);
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                {
                    //获取Password框的文本(验证密码)
                    String pwd=Password.getText().trim();//去除空格的文本
                    //获取newPassword框的文本(新密码)
                    String newpwd=newPassword.getText().trim();//去除空格的文本
                    String username=Scanf.getText().trim();
                    //查数据
                    String sql="select *from game_tab where username='"+username+"'";//查询这个用户一行的数据
                    ResultSet resultSet=getMessage.cha(sql);
                    if (pwd.equals("输入密保答案")||pwd.equals(""))
                        JOptionPane.showMessageDialog(null, text);
                    else
                    {
//                        remove(new JOptionPane());
                        try {
                            //如果查不到
                            if (!resultSet.next())
                            {
                                Password.setVisible(false);
                                newPassword.setVisible(false);
                            }
                            //如果查不到
                            else
                            {
                                String answer=resultSet.getString("answer");//取得密保答案
                                //验证是否一致
                                if (pwd.equals(answer)) {
                                    right.setIcon(new ImageIcon("images//对.png"));
                                    right.setVisible(true);//显示图标
                                    newPassword.setVisible(true);
                                    Thread newpwdThread = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            while (true) {
                                                if (newpwdWid < 300) {
                                                    newPassword.setSize(newpwdWid++, 40);
                                                } else {
                                                    newPassword.setText("");
                                                    break;
                                                }
                                                try {
                                                    Thread.sleep(7);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    });
                                    newpwdThread.start();//开启新密码框的过度
                                    if (newpwd.equals("")||newpwd.equals("请输入您的新密码"))
                                    JOptionPane.showMessageDialog(null,"新密码不能为空！");
                                    else if (newpwd.length()<19)
                                    {
                                        //更新密码
                                        String sql1="update game_tab set password='"+newpwd+"' where username='"+username+"'";
                                        getMessage.ChangeValue(sql1);
                                        JOptionPane.showMessageDialog(null,"您的密码已经修改成功请牢记！");
                                        dispose();
                                    }
                                    else
                                        JOptionPane.showMessageDialog(null,"修改的密码长度不能超过19位");
                                }
                                //不等于的话显示错误图标
                                else {
                                    right.setIcon(wrong);
                                    right.setVisible(true);
                                }
                            }
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                flag1=0;
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            try {
                                if (green1<255&&flag1==0)
                                    submit.setBackground(new Color(0,green1++,0));
                                else break;
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }});
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                flag1=1;
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            try {
                                if (green1>100&&flag1==1)
                                    submit.setBackground(new Color(0,green1--,0));
                                else break;
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.start();
            }
        });
        
        //添加找回按钮
        Ok.setSize(130,40);
        //去掉按钮文字周围的焦点框
        Ok.setFocusPainted(false);
        Ok.setBackground(new Color(0,green,0));
        Ok.setLocation(600,70);
        Ok.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Ok.setBorder(BorderFactory.createEtchedBorder());
        Ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                flag=0;
            Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        if (green<255&&flag==0)
                            Ok.setBackground(new Color(0,green++,0));
                        else break;
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
            }});
        Ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                flag=1;
                Thread thread1=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true)
                        {
                            try {
                                if (green>100&&flag==1)
                                    Ok.setBackground(new Color(0,green--,0));
                                else break;
                                Thread.sleep(5);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread1.start();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                {
                    String username=Scanf.getText().trim();//去除空格后的文本
                    String sql="select *from game_tab where username='"+username+"'";//查询用户名
                    ResultSet resultSet=getMessage.cha(sql);
                    try {
                        if(username.equals("") || username.equals("请输入你要找回的账号")) {
                            hint.setText("密码必须填账户名,不能为空");
                            hint.setVisible(true);
                        }
                        else//满足上述条件的话执行else
                        { //如果查不到
                            hint.setVisible(false);
                            if (!resultSet.next()) {
                                hint.setText("没有这个用户的信息");
                                hint1.setVisible(false);
                                hint.setVisible(true);
                                Password.setVisible(false);
                                right.setVisible(false);
                                newPassword.setVisible(false);
                                pwdWid=0;
                            }
                            else//查到了
                            {
                                hint.setVisible(false);
                                String user=resultSet.getString("username");//取得这个用户这一行所有数据
                                String question=resultSet.getString("question");//取得问题
//                                if (user.equals(username)) {
                                hint.setText("查到了用户:"+user);
                                if (question.equals(""))//问题没有设置的话
                                    hint1.setText("该用户密保没有设置");
                                else
                                    hint1.setText("密保问题"+question);
                                hint.setVisible(true);
                                    //显示密保
                                hint1.setVisible(true);
                                //密保只要显示了就过度显示密码验证框
                                Password.setVisible(true);
                                text="请输入您的密保答案";
                                Thread pwdThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        while (true) {
                                            if (pwdWid < 300) {
                                                Password.setSize(pwdWid++, 40);
                                            } else {
                                                Password.setText("");
                                                break;
                                            }
                                            try {
                                                Thread.sleep(7);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                });
                                pwdThread.start();
                            }
                        }
                    } catch (SQLException sqlException) {
                        sqlException.printStackTrace();
                    }

                }
            }
        });
        bg.add(Ok);

        add(bg);
        setSize(Wid,Hei);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setVisible(true);
    }

//    public static void main(String[] args) {
//        new Retrievepassword(null,"找回密码",true);
//    }
}
