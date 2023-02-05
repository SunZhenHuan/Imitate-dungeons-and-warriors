package com;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class figureinterface extends JFrame{
    ImageIcon[]imageIcons=new ImageIcon[]{new ImageIcon("images//女武神右.gif"),new ImageIcon("images//湖中仙子右.gif")};
    JPanel rolePanel=new JPanel();
    JPanel[]role=new JPanel[]{
            new JPanel(),new JPanel(),new JPanel(),
            new JPanel(),new JPanel(),new JPanel(),
    };//人物数组
    JLabel bgLabel=new JLabel();
    GetMessage getMessage=new GetMessage();
    static String[] state =new String[]{"在线","不在线"};
    static int width=Toolkit.getDefaultToolkit().getScreenSize().width;
    static int height=Toolkit.getDefaultToolkit().getScreenSize().height;
    Image image,Poinerimage;
    ImageIcon bgicon=new ImageIcon("images/image7.png"),smallicon;
    ImageIcon Musicoff=new ImageIcon("images/Gif5.gif");
    ImageIcon Musicon=new ImageIcon("images/Gif6.gif");
    JLabel timelabel;
    ImageIcon starticon=new ImageIcon("images/Gif7.gif");
    JLabel startlabel=new JLabel();
    Music music=new Music();
    String musicArrays[]=new String[11];
    JLabel statelabel=new JLabel();
    public static int count =1;
    public static int i=0;
    JLabel cancellation=new JLabel("注销此账号");
    static int flag=1;
    int roleY=30;
    JLabel hit=new JLabel("已经到顶咯~");
    int hitY=-5;
    String name="";
    int alpha=0;
    int[]roleflag=new int[]{0,0,0,0,0,0};
    JLabel[]roleArrays=new JLabel[]{
      new JLabel(),new JLabel(),new JLabel(),
      new JLabel(),new JLabel(),new JLabel(),
    };
    JLabel hit1=new JLabel();
    String name1="";
//    public static void main(String[] args) {
//        new figureinterface();
//    }
    public figureinterface()
    {

        //实例化八个音频文件路径
        musicArrays[0]="images/2.wav";
        musicArrays[1]="images/3.wav";
        musicArrays[2]="images/4.wav";
        musicArrays[3]="images/5.wav";
        musicArrays[4]="images/6.wav";
        musicArrays[5]="images/7.wav";
        musicArrays[6]="images/8.wav";
        musicArrays[7]="images/9.wav";
        //让这首歌出现的几率大一点
        musicArrays[8]="images/1.wav";
        musicArrays[9]="images/1.wav";
        musicArrays[10]="images/1.wav";
        //随机数0-10
        Random musicnumber=new Random();
        int rand= musicnumber.nextInt(11);


        //实例化ProgressBar的外部类JLabel类并开启线程
        JLa timeJLabel=new JLa(50,30,400,400,30);
        //加入到Thread
        Thread t=new Thread(timeJLabel);
        t.start();//开启线程
        bgLabel.add(timeJLabel);//加入到Main中
        music.playMusic(musicArrays[rand],-1,2.0f);//设置进入的音乐
        ImageIcon Musicicon=new ImageIcon("images/image6.png");
        JLabel Musiclabel=new JLabel();
        JLabel Musicstate=new JLabel("音乐开");
        Poinerimage=new ImageIcon("images/image5.png").getImage();
        image=bgicon.getImage().getScaledInstance(width,height,1);
        smallicon=new ImageIcon(image);




        //登入人姓名及状态标签
        statelabel.setOpaque(false);
        statelabel.setSize(800,100);
        statelabel.setIcon(new ImageIcon("images/在线.png"));
        statelabel.setForeground(Color.RED);
        statelabel.setFont(new Font("姚体",Font.BOLD,20));
        statelabel.setLocation(250,430);
        statelabel.setText(LoginInterface.userField.getText()+state[0]);
        bgLabel.add(new Period(LoginInterface.userField.getText(),130,550,40,500,200));
        bgLabel.add(statelabel);

        //人物面板
        rolePanel.setSize(850,500);
        rolePanel.setBorder(new WindowsBorders.ProgressBarBorder(new Color(0,0,0,70),new Color(0,255,0,70)));
        rolePanel.setOpaque(false);
        rolePanel.setLocation(618,302);
        rolePanel.setLayout(null);

        //hit
        hit.setSize(120,30);
        hit.setLocation(350,hitY);
        hit.setForeground(Color.ORANGE);
        hit.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,20));
        hit.setVisible(false);
        rolePanel.add(hit);

        //人物1
        role[0].setSize(250,430);
        role[0].setName("女武神");
        role[0].setBackground(new Color(0,0,0,90));
        role[0].setLayout(null);
        role[0].setLocation(20,roleY);
        role[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1&&roleflag[0]==0) {
                    roleflag[0]=1;
                    name = role[0].getName();
                    name1=role[0].getName();
                    roleArrays[0].setBorder(new MetalBorders.Flush3DBorder());
                    roleArrays[1].setBorder(null);
                    roleArrays[2].setBorder(null);
                    hit1.setText("");
                    alpha=0;
                }
                else if (roleflag[0]==1)
                {
                    roleflag[0]=0;
                    name="";
                    name1="";
                    roleArrays[0].setBorder(null);
                    roleArrays[1].setBorder(null);
                    roleArrays[2].setBorder(null);
                }
            }
        });

        //人物图像
        roleArrays[0].setSize(250,430);
        roleArrays[0].setOpaque(false);
        roleArrays[0].setLocation(20,roleY);
        roleArrays[0].setIcon(imageIcons[0]);
        //人物2
        role[1].setSize(role[0].getSize());
        role[1].setName("湖中仙子");
        role[1].setBackground(new Color(0,0,0,90));
        role[1].setLayout(null);
        role[1].setLocation(290,roleY);
        role[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1&&roleflag[1]==0) {
                    roleflag[1]=1;
                    name = role[1].getName();
                    name1=role[1].getName();
                    System.err.println(name);
                    roleArrays[0].setBorder(null);
                    roleArrays[1].setBorder(new MetalBorders.Flush3DBorder());
                    roleArrays[2].setBorder(null);
                    hit1.setText("");
                    alpha=0;
                }
                else if (roleflag[1]==1)
                {
                    roleflag[1]=0;
                    name="";
                    name1="";
                    roleArrays[0].setBorder(null);
                    roleArrays[1].setBorder(null);
                    roleArrays[2].setBorder(null);
                }
            }
        });
        roleArrays[1].setSize(250,430);
        roleArrays[1].setOpaque(false);
        roleArrays[1].setLocation(290,roleY);
        roleArrays[1].setIcon(imageIcons[1]);
        //人物3
        role[2].setSize(role[0].getSize());
        role[2].setName("人物3");
        role[2].setBackground(new Color(0,0,0,90));
        role[2].setLayout(null);
        role[2].setLocation(560,roleY);
        role[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1&&roleflag[2]==0) {
                    roleflag[2]=1;
                    name = role[2].getName();
                    name1=role[2].getName();
                    System.err.println(name);
                    roleArrays[0].setBorder(null);
                    roleArrays[1].setBorder(null);
                    roleArrays[2].setBorder(new MetalBorders.Flush3DBorder());
                    hit1.setText("");
                    alpha=0;
                }
                else if (roleflag[2]==1)
                {
                    roleflag[2]=0;
                    name="";
                    name1="";
                    roleArrays[0].setBorder(null);
                    roleArrays[1].setBorder(null);
                    roleArrays[2].setBorder(null);
                }
            }
        });
        roleArrays[2].setSize(250,430);
        roleArrays[2].setOpaque(false);
        roleArrays[2].setLocation(560,roleY);
        roleArrays[2].setIcon(imageIcons[0]);
        rolePanel.add(roleArrays[0]);
        rolePanel.add(roleArrays[1]);
        rolePanel.add(roleArrays[2]);
        rolePanel.add(role[0]);
        rolePanel.add(role[1]);
        rolePanel.add(role[2]);
        bgLabel.add(rolePanel);
        rolePanel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                if (e.getWheelRotation()==1) {
                    if (role[0].getLocation().y<=30&&role[1].getLocation().y<=30&&role[2].getLocation().y<=30) {
                        role[0].setLocation(20, role[0].getLocation().y += 20);
                        role[1].setLocation(290, role[1].getLocation().y += 20);
                        role[2].setLocation(560, role[2].getLocation().y += 20);
                        roleArrays[0].setLocation(roleArrays[0].getLocation().x,roleArrays[0].getLocation().y+=20);
                        roleArrays[1].setLocation(roleArrays[1].getLocation().x,roleArrays[1].getLocation().y+=20);
                        roleArrays[2].setLocation(roleArrays[2].getLocation().x,roleArrays[2].getLocation().y+=20);
                        hit.setLocation(hit.getLocation().x,hitY+=20);
                    }
                    else
                    {
                        Thread thread=new Thread(() -> {
                            while(true)
                            {
                                hit.setVisible(role[0].getLocation().y > 30 && role[1].getLocation().y > 30 && role[2].getLocation().y > 30);
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }
                }
                else
                {
                    role[0].setLocation(20, role[0].getLocation().y-=20);
                    role[1].setLocation(290,role[1].getLocation().y-=20);
                    role[2].setLocation(560,role[2].getLocation().y-=20);
                    roleArrays[0].setLocation(roleArrays[0].getLocation().x,roleArrays[0].getLocation().y-=20);
                    roleArrays[1].setLocation(roleArrays[1].getLocation().x,roleArrays[1].getLocation().y-=20);
                    roleArrays[2].setLocation(roleArrays[2].getLocation().x,roleArrays[2].getLocation().y-=20);
                    hit.setLocation(hit.getLocation().x,hitY-=20);
                }
            }

        });
//        role[0].addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                super.mousePressed(e);
//                point.y=e.getY();
//            }
//        });
//        role[0].addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                super.mouseDragged(e);
//                Point point1 = role[0].getLocation();
//                if (role[0].getLocation().y>0&&role[1].getLocation().y>0&&role[2].getLocation().y>0) {
//                    role[0].setLocation(20, point1.y + e.getY() - point.y);
//                    role[1].setLocation(290, point1.y + e.getY() - point.y);
//                    role[2].setLocation(560, point1.y + e.getY() - point.y);
//                }
//            }
//        });
        bgLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.err.println(e.getX()+"\t"+e.getY());
            }
        });
        //提示没选人物无法进入
        hit1.setSize(470,50);
        hit1.setForeground(new Color(255,0,0,alpha));
        hit1.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        hit1.setLocation(790,880);
        bgLabel.add(hit1);
        //注销账号
        cancellation.setSize(130,50);
        cancellation.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        cancellation.setForeground(Color.green);
        cancellation.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancellation.setLocation(900,925);
        bgLabel.add(cancellation);
        cancellation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                {
                    int op=JOptionPane.showConfirmDialog(null,"注销账号会导致你的所有物品全部删除"+"\n"+"你确定要注销账号吗？","注销",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (op==JOptionPane.OK_OPTION)
                    {
                        String count="select *from pc_name where pc_name='"+LoginInterface.GetHostName(LoginInterface.Hostname)+"'";//查询这台主机名
                        ResultSet rs=getMessage.cha(count);
                        try {
                            if (rs.next())
                            {
                                int pc_name_count=rs.getInt("count");//取得count数
                                pc_name_count--;
                                String update="update pc_name set count='"+(pc_name_count)+"'";
                                getMessage.ChangeValue(update);

                                getMessage.deleteid("delete from game_tab where id='"+LoginInterface.UUid+"'");
                                getMessage.deleteid("delete from numberstate where id='"+LoginInterface.UUid+"'");
                                getMessage.delete("delete from rolename_tab where id='"+LoginInterface.UUid+"'");
                                System.exit(0);
                            }

                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }
                    }
                }
            }
        });

        //开始游戏标签
        startlabel.setSize(265,55);
        startlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startlabel.setLocation(850,1000);
        startlabel.setOpaque(true);
        startlabel.setIcon(starticon);
        bgLabel.add(startlabel);



        //音乐开关状态
        Musicstate.setFont(new Font("姚体",Font.BOLD,20));
        Musicstate.setSize(180,100);
        Musicstate.setForeground(Color.GREEN);
        Musicstate.setLocation(1570,30);
        bgLabel.add(Musicstate);




        //添加音乐默认图
        Musiclabel.setSize(200,150);
        Musiclabel.setLocation(1520,50);
        Musiclabel.setIcon(Musicicon);
//        Musiclabel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY,Color.LIGHT_GRAY));
        bgLabel.add(Musiclabel);

        //设置鼠标样式
        Cursor PointerCursor=Toolkit.getDefaultToolkit().createCustomCursor(Poinerimage,new Point(10,10),"norm");
        this.setCursor(PointerCursor);


        bgLabel.setSize(width,height);
        bgLabel.setIcon(smallicon);
        this.add(bgLabel);
        this.setFocusable(true);
        this.setUndecorated(true);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ResultSet resultSet=getMessage.cha("select *from rolename_tab where id='"+LoginInterface.UUid+"'");
        try {
            if (resultSet.next())//如果查到了
            {
                String rolename=resultSet.getString("rolename");
                if (!rolename.equals("女武神")) {
                    roleArrays[0].setVisible(false);
                    role[0].setName("");
                }
                if (!rolename.equals("湖中仙子")) {
                    roleArrays[1].setVisible(false);
                    role[1].setName("");
                }
                if (!rolename.equals("人物3")) {
                    roleArrays[2].setVisible(false);
                    role[2].setName("");
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        this.setVisible(true);
        startlabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!name1.equals("")) {
                    if (count == 1) {
                        count = 0;
                        startlabel.setCursor(PointerCursor);
                        music.clip.close();
                        Sql sql = new Sql();
                        String sql1 = "update game_tab set state='" + state[0] + "' where id='" + LoginInterface.UUid + "'";
                        sql.statmevalue(sql1);
                        ResultSet resultSet1=getMessage.cha("select *from rolename_tab where id='"+LoginInterface.UUid+"'");
                        try {
                            if (!resultSet1.next())//如果没有查到相同名字
                            {
                                String sql2="insert into rolename_tab values('"+LoginInterface.UUid+"','"+name1+"')";//向表插入该用户选择的角色
                                getMessage.chaValue(sql2);
                            }
                            else//如果查到
                            {
                                String sql3="update rolename_tab set rolename='"+name1+"' where id='"+LoginInterface.UUid+"'";
                                getMessage.ChangeValue(sql3);//则更新值
                            }
                            dispose();
                            JOptionPane.setRootFrame(new Gameinterface1());
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }
                    }
                }
                else
                {
                    hit1.setText("没有选择人物是不能进入游戏世界的哦~");
                    Thread thread=new Thread(()->{
                            while (true)
                            {
                                try {
                                if (alpha<255)
                                    hit1.setForeground(new Color(255,0,0,alpha++));
                                    Thread.sleep(5);
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                            }
                    });
                    thread.start();
                }
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Sql sql=new Sql();
                String sql1 = "update game_tab set state='" + state[1] + "' where id='" + LoginInterface.UUid + "'";
                sql.statmevalue(sql1);
                System.exit(0);
            }
        });

        Musiclabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int button=e.getButton();
                if (button==MouseEvent.BUTTON1&&flag==1) {
                    flag=0;
                    Musiclabel.setIcon(Musicoff);
                    Musicstate.setText("音乐关");
                    music.clip.stop();
                }
                else if (button==MouseEvent.BUTTON1)
                {
                    flag=1;
                    Musiclabel.setIcon(Musicon);
                    Musicstate.setText("音乐开");
                    music.clip.start();
                }
            }
        });
    }

    }

