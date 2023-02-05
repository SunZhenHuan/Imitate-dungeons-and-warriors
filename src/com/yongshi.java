package com;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.RoundRectangle2D;
import java.util.concurrent.TimeUnit;

public class yongshi extends JDialog {
    /**
     * @autor sunzhenhuan
     * @param 先用ps在里面把人物位置先放好，然后通过坐标点来设置
     * @ps 想要生成怪物什么的可以对当前图片的X轴坐标与或者人物的移动位置下手
     */
    final int Wid= Toolkit.getDefaultToolkit().getScreenSize().width;
    final int Hei=Toolkit.getDefaultToolkit().getScreenSize().height;
    ImageIcon image=new ImageIcon("images//对话背景.png");
    static String role="";
    Image[]images=new Image[]{
            Toolkit.getDefaultToolkit().createImage("images//夜晚的冈特古村背景.png"),
            Toolkit.getDefaultToolkit().createImage("images//夜晚的冈特古村前景.png"),
            Toolkit.getDefaultToolkit().createImage("images//夜晚的冈特古村近景.png"),
//            Toolkit.getDefaultToolkit().createImage("images//夜晚的冈特古村远景.png"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"右.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"跑动向左.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"跑动向右.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"右攻击.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"左.gif"),
            Toolkit.getDefaultToolkit().createImage("images//"+role+"左攻击.gif"),
    };
    int[]X={0,0,0,0,0};
    int roleX=0,roleY=40;
    final int Mid=Wid/2;
    final int MinY=30,MaxY=970;
    final int low=960;
    int flag=0;//切换人物标志
    int upflag=0;//上标志
    int downflag=0;//下标志
    int attack=0;//攻击标志
    final JPanel content=new JPanel()
    {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D g1=(Graphics2D) g;
            g1.drawImage(images[0],X[0],0,images[0].getWidth(null),images[0].getHeight(null),this);//背景
            g1.drawImage(images[2],X[2],0,images[2].getWidth(null),images[2].getHeight(null),this);//近景
            switch (flag)
            {
                case 0:g1.drawImage(images[3], roleX, roleY, images[3].getWidth(null), images[3].getHeight(null), this);break;//没有动
                case 1:g1.drawImage(images[4], roleX, roleY, images[4].getWidth(null), images[4].getHeight(null), this);break;//向左
                case 2:g1.drawImage(images[5], roleX, roleY, images[5].getWidth(null), images[5].getHeight(null), this);break;//向右
                case 3:g1.drawImage(images[6],roleX,roleY,images[6].getWidth(null),images[6].getHeight(null),this);break;//右攻击
                case 4:g1.drawImage(images[7],roleX,roleY,images[7].getWidth(null),images[7].getHeight(null),this);break;//朝左边
                case 5:g1.drawImage(images[8],roleX,roleY,images[8].getWidth(null),images[8].getHeight(null),this);break;//左攻击
            }
            g1.drawImage(images[1],X[1],0,images[1].getWidth(null),images[1].getHeight(null),this);//前景
        }
    };
    public yongshi()
    {
        setSize(Wid,Hei);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);

        //对话背景
        add(new ChatLabel(image));


        //主面板
        content.setLayout(null);
        content.setOpaque(false);
        content.setSize(getSize().width,751);
        content.setLocation(0,0);
        add(content);
        setVisible(true);
       addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char code=e.getKeyChar();
                if (code=='A'||code=='a')//按下了A
                {
                    //如果按下A
                    upflag=1;
                    downflag=1;
                    attack=1;
                    if (roleX>0)//只能大于零才能移动
                    {
                        flag=4;
                        flag=1;//左
                        roleX-=8;
                        if (X[0]<low)//如果背景坐标X小于960
                        {
                            if (X[0]<0) {//如果X轴小于0
                                X[0] += 10;
                                X[1] += 10;
                                X[2] += 10;//背景向前移动
                            }
                        }
                    }
                    repaint();
                }
                else if (code=='D'||code=='d')
                {
                    //如果按下D
                    upflag=2;
                    downflag=2;
                    attack=2;
                    if (roleX<1600)//边界根据最大屏幕1920与图片最大宽度2880得来至多小于1600边界
                    {
                        flag=0;
                        flag=2;//右
                        roleX+=8;
                        if (X[0]>(-low))//如果这时的背景X轴大于-960，因为向前走的话背景就是向后减，当减到了-960就会停止
                        {
                            X[0]-=10;
                            X[1]-=10;
                            X[2]-=10;
                        }
                    }
                    repaint();
                }
                else if (code=='W'||code=='w')
                {
                    if (roleY>MinY)//当人物的Y轴大于最小Y边界的时候才能移动
                    {
                        if (upflag==1) {
                            flag = 1;
                            roleY -= 8;
                        }
                        else if (upflag==2)
                        {
                            flag=2;
                            roleY -= 8;
                        }
                    }
                    repaint();
                }
                else if (code=='S'||code=='s') {
                    if (roleY < MaxY-images[3].getHeight(null)) {
                        //downflag=1说明A已经按下=2说明D已经按下

                            if (downflag==1) {
                                flag = 1;
                                roleY += 8;
                            }
                            else if (downflag==2)
                            {
                                flag=2;
                                roleY += 8;
                            }
                    }
                    repaint();
                }
                else if (code=='J'||code=='j')
                {
                    if (attack==1)//说明A被按下
                        flag=5;
                    else if (attack==2)//D被按下
                        flag=3;
                    repaint();
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
                   repaint();
               }
               else if (code=='d'||code=='D') {
                   flag = 0;
                   repaint();
               }
               else if (code=='w'||code=='W')
               {
                   //updownflag=1说明A已经按下=2说明D已经按下
                   if (upflag==1)
                       flag=4;
                   else if (upflag==2)
                       flag=0;
                   repaint();
               }
               else if (code=='s'||code=='S') {
                   if (downflag==1)
                       flag=4;
                   else if (downflag==2)
                       flag=0;
                   repaint();
               }
               else if (code=='j'||code=='J')
               {
                  if (attack==1)
                   flag=4;
               else if (attack==2)
                   flag=0;
                   repaint();
               }
           }
        });
        //聊天面板
    }

//    public static void main(String[] args) {
//        new yongshi();
//    }
}
