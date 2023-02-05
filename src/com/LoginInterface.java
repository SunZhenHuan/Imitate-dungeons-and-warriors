package com;

import com.mysql.cj.jdbc.SuspendableXAConnection;
import com.sun.java.swing.plaf.motif.MotifTextUI;
import javafx.scene.layout.Border;

import javax.management.remote.rmi._RMIConnection_Stub;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LoginInterface extends JFrame {
    Point point=new Point();
    ImageIcon bgicon;
    Image bgiconimage=new ImageIcon("images/Gif1.gif").getImage().getScaledInstance(figureinterface.width/2,figureinterface.height/2,0);
    JLabel bglbel;
    JLabel userlabel, passwordlabel;
    public static JTextField userField = new JTextField();
    JLabel LoginButton;
    JPasswordField passwordField = new JPasswordField();
    ImageIcon LoginImage;
    JLabel register;
    GetMessage getMessage=new GetMessage();
    JLabel timelabel;
    ImageIcon Exiticon, smallicon;
    JLabel Exitlabel;
    JLabel rtpasswordLabel=new JLabel("忘记密码？点击找回");
    static Music music = new Music();
    Image small;
    static String Hostname;
    public static String UUid ="";
//    public static void main(String[] args) {
//        new LoginInterface();
//    }
//获取主机名
public static String GetHostName(String Hostname)
{
    try {
        Hostname=InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
        e.printStackTrace();
    }
    return Hostname;
}
    public LoginInterface() {
        //设置登入背景
        bgicon = new ImageIcon(bgiconimage);
        bglbel = new JLabel();
        bglbel.setIcon(bgicon);
        bglbel.setSize(bgicon.getIconWidth(),bgicon.getIconHeight());
        this.add(bglbel);

        //添加时间段
        bglbel.add(new Period("",this.bglbel.getWidth()-230,80,22,230,100));

        //找回密码标签
        rtpasswordLabel.setSize(300,30);
        rtpasswordLabel.setForeground(Color.BLUE);
        rtpasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rtpasswordLabel.setFont(new Font("姚体",Font.BOLD,25));
        rtpasswordLabel.setLocation(380,480);
        bglbel.add(rtpasswordLabel);
        rtpasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rtpasswordLabel.setForeground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                rtpasswordLabel.setForeground(Color.blue);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                    new Retrievepassword(null,"找回密码",true);
            }
        });



        //时间标签
        timelabel = new JLabel();
        timelabel.setForeground(Color.RED);
        timelabel.setSize(400, 100);
        timelabel.setFont(new Font("方正字迹-龙吟体 简", Font.BOLD, 20));
        timelabel.setLocation(20, 30);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String timezone = s.format(new Date());
                    timelabel.setText(timezone);
                }
            }
        });
        thread.start();//开启线程显示时间
        bglbel.add(timelabel);


        //注册标签
        register = new JLabel("[点击注册]");
        register.setForeground(Color.YELLOW);
        register.setFont(new Font("姚体", Font.BOLD, 20));
        register.setSize(100, 30);
        register.setLocation(465, 410);
        bglbel.add(register);

        //设置退出按钮
        Exiticon = new ImageIcon("images/image3.png");
        small = Exiticon.getImage().getScaledInstance(50, 50, 1);//缩小图片
        smallicon = new ImageIcon(small);
        //退出图片
        Exitlabel = new JLabel();
        Exitlabel.setSize(50, 50);
        Exitlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Exitlabel.setLocation(910, 0);
        Exitlabel.setIcon(smallicon);

        bglbel.add(Exitlabel);

        //登入按钮
        LoginButton = new JLabel();
        LoginImage = new ImageIcon("images/Gif2.gif");
        LoginButton.setIcon(LoginImage);
        LoginButton.setSize(180, 35);
        LoginButton.setLocation(420, 350);
        LoginButton.setOpaque(false);
        bglbel.add(LoginButton);


        //用户框
        userField.setSize(230, 25);
        userField.setLocation(420, 150);
        userField.setForeground(Color.yellow);
        userField.setToolTipText("输入用户名");
        userField.setFont(new Font("方正赵孟頫楷书 简繁",Font.BOLD,20));
        userField.setOpaque(false);
        bglbel.add(userField);
        userField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                userField.setBorder(BorderFactory.createLineBorder(Color.green,MAXIMIZED_HORIZ,true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                userField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
        });
        //用户标签
        userlabel = new JLabel("账户");
        userlabel.setSize(100, 50);
        userlabel.setLocation(375, 135);
        userlabel.setForeground(Color.CYAN);
        userlabel.setFont(new Font("姚体", Font.BOLD, 20));
        bglbel.add(userlabel);


        //密码框
        passwordField.setToolTipText("输入密码");
        passwordField.setSize(230, 25);
        passwordField.setForeground(Color.yellow);
        passwordField.setFont(new Font("方正赵孟頫楷书 简繁",Font.BOLD,20));
        passwordField.setEchoChar('*');
        passwordField.setLocation(420, 250);
        passwordField.setOpaque(false);
        bglbel.add(passwordField);
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                passwordField.setBorder(BorderFactory.createLineBorder(Color.green,MAXIMIZED_HORIZ,true));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                passwordField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
        });
        //密码标签
        passwordlabel = new JLabel("密码");
        passwordlabel.setSize(100, 50);
        passwordlabel.setLocation(375, 235);
        passwordlabel.setForeground(Color.CYAN);
        passwordlabel.setFont(new Font("姚体", Font.BOLD, 20));
        bglbel.add(passwordlabel);


        setUndecorated(true);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("images/image5.png").getImage(), new Point(10, 10), "孙振寰"));
        setSize(figureinterface.width/2, figureinterface.height/2);
        setIconImage(new ImageIcon("images/游戏图标1.png").getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

//背景音乐
        music.playMusic("images/8.wav",-1,2.0f);
        //注册鼠标事件
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    /*
                     InetAddress inetAddress = InetAddress.getLocalHost();
                    String name = inetAddress.getHostName();//获取本机名
                    String sql="select *from game_iphostname";//查结果
                    String sql1="insert into game_iphostname values('"+id1+"','"+name+"','"+count+"')";
                     */
                new regis(null,"注册",true);
//                new register();
//                music.clip.stop();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                register.setCursor(new Cursor(Cursor.HAND_CURSOR));
                register.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                register.setForeground(Color.yellow);
            }
        });
        Exitlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int op = JOptionPane.showConfirmDialog(null,"确定要退出吗？","提示",JOptionPane.YES_NO_OPTION);
                if (JOptionPane.YES_OPTION==op)
                    System.exit(0);
            }
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        LoginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int tag=0;
                String s = userField.getText();//获取输入的账户
                String s1 = passwordField.getText();//获取输入的密码
                Sql sql = new Sql();
                String s_1 = "select * from game_tab where username='" + s + "' and password='" + s1 + "'";//检查账户和密码是否与注册相同
                String s_2 = "select * from game_tab where password='" + s1 + "'";//检查密码是否与数据库一样
                String s_3 = "select * from game_tab where username='" + s + "'";//检查账户是否与数据库一样
                String[] select = new String[]{s_1, s_2, s_3};//三个查询语句存入数组
//                String s_1="select * from game_tab where username='"+s+"' and password='"+s1+"'";
                for (int i = 0; i < 3; i++) {
                    ResultSet rs = sql.cha(select[i]);
                    try {
                        if (rs.next()) {
                            String ss, sql_1 = "在线";
                            ss = rs.getString("state");//ss表示状态信息
                            if (ss.equals("在线"))  //如果数据库中显示的是在线，则不能登录
                            {
                                String sql3="update numberstate set state=1 where id='"+rs.getString("id")+"'";
                                try {
                                    new Sql().statmevalue(sql3);
                                    Thread.sleep(500);
                                    sql3="select * from numberstate where id ='"+rs.getString("id")+"'";
                                    ResultSet rr=getMessage.cha(sql3);
                                    rr.next();
                                    if(rr.getInt("state")!=2)
                                        tag=1;
                                    else {
                                        tag=2;
                                        JOptionPane.showMessageDialog(null, "登录失败\n该账号已登录", "账号已登录", JOptionPane.QUESTION_MESSAGE);
                                        userField.setText("");
                                        passwordField.setText("");
                                    }
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }

                            }
                                 if(tag!=2)
                                {
                                    UUid = rs.getString("id");
                                String sql1 = "update game_tab set state='" + sql_1 + "' where id='" + UUid + "'";
                                sql.statmevalue(sql1);

                                JOptionPane.showMessageDialog(null, "登录成功");
                                new Runtime1().GetRuntime();
                                    System.err.println("socket服务器开启成功！");
                                //只有登录成功了才会更新主机名
                                String sql2 = "update game_tab set HostName='" + GetHostName(Hostname) + "' where id='" + UUid + "'";
                                String sq="update game_tab set Logintime='"+new Date().toLocaleString()+"' where id='"+UUid+"'";
                                sql.statmevalue(sql2);
                                sql.statmevalue(sq);
                                music.clip.close();
                                 String sql3="update numberstate set state=0 where id='"+LoginInterface.UUid+"'";
                                 new Sql().statmevalue(sql3);
                                 Thread1 mc=new Thread1();
                                 Thread run=new Thread(mc);
                                 run.start();
                                 new figureinterface();
                                 new Runtime1().GetRuntime();
                                 dispose();
                                }else {
                                    String sql3="update numberstate set state=0 where id='"+LoginInterface.UUid+"'";
                                     new Sql().statmevalue(sql3);

                                 }

                        } else if(userField.getText().trim().equals("")||passwordField.getText().trim().equals("")) {
                            JOptionPane.showMessageDialog(null, "账号或密码不能为空！");
                        }
                           else JOptionPane.showMessageDialog(null, "登录失败请检查账号和密码是否正确", "账户或密码问题", JOptionPane.QUESTION_MESSAGE);
                        break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        LoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                LoginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                LoginButton.setCursor(null);
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
