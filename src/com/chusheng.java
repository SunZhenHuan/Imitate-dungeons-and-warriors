package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class chusheng extends JFrame{
    final int width= Toolkit.getDefaultToolkit().getScreenSize().width;
    final int height=Toolkit.getDefaultToolkit().getScreenSize().height;
    final int low=960;
    int rolestopX,rolestopY=40;

    int roleleftX,roleleftY=40;
    int rolerightX,rolerightY=40;
    final int mid=width/2;
    int[]imageX=new int[]{0,0,0,0};
    int flag=0;
    int upflag=0;//上标志
    int downflag=0;//下标志
    int attack=0;//攻击标志
    GetMessage getMessage=new GetMessage();
    static String role="";
    static int flag0=-1,flag1=-1,flag2=-1;//用于判断人物对话是否完成
    int HOURS=new Date().getHours();//获取时
    static JLabel[]friend=new JLabel[]{
            new JLabel(),new JLabel(),new JLabel(),new JLabel(),
    };
    String[]Message={
            "我是詹姆斯",
            "我是卢本伟",
            "我是XXXX",
    };//人物信息
    ImageIcon image=new ImageIcon("images//对话背景.png");
    final Image[]images=new Image[]{
            Toolkit.getDefaultToolkit().createImage("images//学校大厅_近景.png"),
            Toolkit.getDefaultToolkit().createImage("images//学校大厅_前景.png"),//普通主题
            Toolkit.getDefaultToolkit().createImage("images//学校大厅_远景.png"),//普通天空
            Toolkit.getDefaultToolkit().createImage("images//"+role+"右.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"跑动向左.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"跑动向右.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"右攻击.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"左.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"左攻击.gif"),
            Toolkit.getDefaultToolkit().createImage("images//学校大厅_圣诞.png"),//圣诞主题（圣诞节专属）
            Toolkit.getDefaultToolkit().createImage("images//学校大厅_光影效果背景.png"),//光影效果(早6到下午六点)
            Toolkit.getDefaultToolkit().createImage("images//学校大厅_背景天空.png"),//不同的天空

    };
    Point[]friendPoint=new Point[]
            {
                    new Point(573,330),new Point(1000,330),new Point(1500,330),new Point(images[0].getWidth(null)-200,330),
            };
    final JPanel content=new JPanel()
    {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g1=(Graphics2D) g;
            //画的背景
            g1.drawImage(images[11],imageX[1],0,images[11].getWidth(null),images[11].getHeight(null),this);
            g1.drawImage(images[0],imageX[0],0,images[0].getWidth(null),images[0].getHeight(null),this);
            g1.drawImage(images[9],imageX[2],0,images[9].getWidth(null),images[9].getHeight(null),this);
            if (HOURS>=6&&HOURS<=18)//如果现在的时间大于6点与小于下午18点
                g1.drawImage(images[10],imageX[3],0,images[10].getWidth(null),images[10].getHeight(null),this);
            //边界线
//            g1.setColor(Color.red);//横
//            g1.drawLine(1,450,1920,450);
//            g1.setColor(Color.BLUE);//竖
//            g1.drawLine(mid,0,mid,height);
            //画的人物
            switch (flag) {
                case 0:g1.drawImage(images[3], rolestopX, rolestopY, images[3].getWidth(null), images[3].getHeight(null), this);break;//没有动
                case 1:g1.drawImage(images[4], rolestopX, rolestopY, images[4].getWidth(null), images[4].getHeight(null), this);break;//向左
                case 2:g1.drawImage(images[5], rolestopX, rolestopY, images[5].getWidth(null), images[5].getHeight(null), this);break;//向右
                case 3:g1.drawImage(images[6],rolestopX,rolestopY,images[6].getWidth(null),images[6].getHeight(null),this);break;//右攻击
                case 4:g1.drawImage(images[7],rolestopX,rolestopY,images[7].getWidth(null),images[7].getHeight(null),this);break;//朝左边
                case 5:g1.drawImage(images[8],rolestopX,rolestopY,images[8].getWidth(null),images[8].getHeight(null),this);break;//左攻击
            }
        }
    };
    public chusheng()
    {
        setSize(width,height);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("images//image5.png").getImage(),new Point(0,0),""));
        setLocationRelativeTo(null);
        setUndecorated(true);
        //对话背景
        add(new ChatLabel(image));
        //大厅朋友标签
        for (int i=0;i<friend.length;i++)
        {
            friend[i].setBorder(BorderFactory.createLineBorder(Color.blue));
            friend[i].setIcon(new ImageIcon(images[3]));
            friend[i].setSize(120,225);
            friend[i].setVerticalAlignment(SwingUtilities.TOP);
            friend[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            switch (i)
            {
                case 0:
                    friend[0].setLocation(friendPoint[0]);
                    friend[0].setName("詹姆斯");
                    friend[0].setIcon(AllGetMethod.GetIcon("images//问话_詹姆斯.gif",840,680,0));
                        friend[0].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
//                                if (flag0==-1) {//如果一开始点了这个人//一定要注意任何操作都是先点了才能执行的不能放在鼠标事件外面
                                    ChatLabel.rolename.setText(friend[0].getName());//改变人名
                                    ChatLabel.show.setText(Message[0]);//改变下方面板里面的信息
//                                    flag1 = 0;
//                                    flag2 = 0;//则其它两个人不能点击
//                                }
                            }
                        });
                    break;
                case 1:
                    friend[1].setLocation(friendPoint[1]);
                    friend[1].setName("卢本伟");
                    friend[1].setIcon(AllGetMethod.GetIcon("images//问话_詹姆斯.gif",840,680,0));
                        friend[1].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
//                                if (flag1==-1) {//如果点了这个人
                                    ChatLabel.rolename.setText(friend[1].getName());//改变人名
                                    ChatLabel.show.setText(Message[1]);//改变下方面板里面的信息
//                                    flag0 = 0;
//                                    flag2 = 0;//则其它两个人不能点击
//                                }
                            }
                        });
                    break;
                case 2:
                    friend[2].setName("wdwadw");
                    friend[2].setLocation(friendPoint[2]);
                        friend[2].addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                super.mouseClicked(e);
//                                if (flag2 == -1) {//如果点了这个人
                                    ChatLabel.rolename.setText(friend[2].getName());//改变人名
                                    ChatLabel.show.setText(Message[2]);//改变下方面板里面的信息
//                                    flag0 = 0;
//                                    flag1 = 0;//则其它两个人不能点击需要等对话结束后才能回复
//                                }
                            }
                        });
                    break;
                case 3:
                    friend[3].setLocation(friendPoint[3]);
                    break;
            }
            add(friend[i]);
        }

        //背景面板
        content.setSize(width,751);
        content.setOpaque(false);//因为背景在面板中用的Graphics画的所以想要在画的背景上添加东西必须要把面板设置为透明的,但是不能用颜色填充面板了。
        content.setLayout(null);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char code=e.getKeyChar();
                if (code=='a'||code=='A') {
                    //如果按下A
                    upflag=1;
                    downflag=1;
                    attack=1;
                    if (rolestopX >=10) {
                        flag=4;
                        flag=1;//左
                        rolestopX-=8;
                            if (imageX[0]<=low) {
                                if (imageX[0]<0) {
                                    imageX[0] +=10;
                                    imageX[1] += 10;
                                    imageX[2] += 10;
                                    imageX[3]+=10;
                                    friend[0].setLocation(friendPoint[0].x+=10,friendPoint[0].y);
                                    friend[1].setLocation(friendPoint[1].x+=10,friendPoint[1].y);
                                    friend[2].setLocation(friendPoint[2].x+=10,friendPoint[2].y);
                                }
                            }
                    }
                }
                else if (code=='d'||code=='D') {
                    //如果按下D
                    upflag=2;
                    downflag=2;
                    attack=2;
                    if (rolestopX < 1600) {
                        flag=0;
                        flag=2;//右
                        rolestopX +=8;
                            if (imageX[0]>(-low)) {
                                imageX[0] -= 10;
                                imageX[1]-=10;
                                imageX[2]-=10;
                                imageX[3]-=10;
                                friend[0].setLocation(friendPoint[0].x-=10,friendPoint[0].y);
                                friend[1].setLocation(friendPoint[1].x-=10,friendPoint[1].y);
                                friend[2].setLocation(friendPoint[2].x-=10,friendPoint[2].y);
                            }
                    }
                }
                else if (code=='W'||code=='w')
                {
                    //updownflag=1说明A已经按下=2说明D已经按下
                    if (rolestopY>40) {
                        if (upflag==1) {
                            flag = 1;
                            rolestopY -= 10;
                        }
                        else if (upflag==2)
                        {
                            flag=2;
                            rolestopY -= 10;
                        }
                    }
                }
                else if (code=='S'||code=='s')
                {//downflag=1说明A已经按下=2说明D已经按下
                    if (rolestopY<230) {
                        if (downflag==1) {
                            flag = 1;
                            rolestopY += 10;
                        }
                        else if (downflag==2)
                        {
                            flag=2;
                            rolestopY += 10;
                        }
                    }
                }
                else if (code=='J'||code=='j')
                {
                    if (attack==1)//说明A被按下
                        flag=5;
                    else if (attack==2)//D被按下
                        flag=3;
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //0没有动
                //1向左
                //2向右
                //3右攻击
                //4朝左
                //5左攻击
                super.keyReleased(e);
                char code=e.getKeyChar();
                if (code=='a'||code=='A') {
                    flag = 4;
                }
                else if (code=='d'||code=='D') {
                    flag = 0;
                }
                else if (code=='w'||code=='W')
                {
                    //updownflag=1说明A已经按下=2说明D已经按下
                    if (upflag==1)
                        flag=4;
                    else if (upflag==2)
                        flag=0;
                }
                else if (code=='s'||code=='S') {
                    if (downflag==1)
                        flag=4;
                    else if (downflag==2)
                        flag=0;
                }
                else if (code=='j'||code=='J')
                {
                    if (attack==1)
                        flag=4;
                    else if (attack==2)
                        flag=0;
                }
                repaint();
                }
        });
        add(content);
        setLayout(null);
        setVisible(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.err.println(e.getX()+"\t"+e.getY());
            }
        });
    }
//    public static void main(String[] args) {
//        new chusheng();
//    }
}
