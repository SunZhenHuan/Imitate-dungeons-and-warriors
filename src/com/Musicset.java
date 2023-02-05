package com;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Random;

class Musicset extends JDialog implements ActionListener{
    static float dB=2f;
    static int flag1=1;
    int alpha=0;
    Hashtable<Integer, JLabel> hashtable;
    static JLabel Value = new JLabel();
    JLabel music = new JLabel("音量");
    static int flag = 1;
    Thread colorT;
    Color colortest;
    Point point=new Point();
    int Wid = Gameinterface1.Width / 2 - 5, Hei = Gameinterface1.Height / 2 - 30;
    JLabel bgicon = new JLabel();
    JLabel returnLabel = new JLabel(new ImageIcon("images/返回.png"));
    static float Opacity = 0.0f;

    String[] path = new String[]{"images//上一首.png", "images//下一首.png", "images//静音.png", "images//音乐库.png",};//路径
    Image[] icons = new Image[]{
            Toolkit.getDefaultToolkit().createImage(path[0]).getScaledInstance(40, 40, 0),
            Toolkit.getDefaultToolkit().createImage(path[1]).getScaledInstance(45, 45, 0),
            Toolkit.getDefaultToolkit().createImage(path[2]).getScaledInstance(40, 40, 0),
            Toolkit.getDefaultToolkit().createImage(path[3]).getScaledInstance(40, 40, 0)
    };
    JLabel[] set = new JLabel[]{new JLabel(), new JLabel(), new JLabel(), new JLabel()};  //播放标签

    //播放
    Image playimage = new ImageIcon("images//播放.png").getImage().getScaledInstance(40, 40, 0);
    ImageIcon playicon = new ImageIcon(playimage);

    JLabel stop = new JLabel();//暂停标签
    Image stopimage = new ImageIcon("images/暂停.png").getImage().getScaledInstance(40, 40, 0);
    ImageIcon stopicon = new ImageIcon(stopimage);
    static JSlider musicslider = new JSlider();
    ImageIcon iconpath = new ImageIcon("images/音乐背景设置.png");
    public Musicset(Gameinterface1 gameinterface1,String t,boolean a) {
        super(gameinterface1,t,a);
        //背景
        bgicon.setSize(Wid, Hei);
        bgicon.setIcon(iconpath);
        add(bgicon);
        //音量值
        music.setSize(100, 100);
        music.setLocation(760, 30);
        music.setFont(new Font("姚体", Font.BOLD, 25));
        music.setForeground(Color.ORANGE);
        bgicon.add(music);

        //播放图片
        stop.setSize(55, 50);
        stop.setCursor(new Cursor(Cursor.HAND_CURSOR));
        stop.setLocation(700, 52);
        stop.setIcon(stopicon);
        bgicon.add(stop);
        stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (flag == 1) {
                        stop.setIcon(playicon);
                        Gameinterface1.music.clip.stop();
                        flag = 0;
                    } else if (flag == 0) {
                        stop.setIcon(stopicon);
                        Gameinterface1.music.clip.start();
                        flag = 1;
                    }
                }
            }
        });

        //如果是每次用System。exe（0）退出的话可以用for循环来添加组件，如果是dispose关闭的话不要用for因为每次再进去的时候会再循环把组件位子挤掉
        //上一首
        set[0].setSize(60, 60);
        set[0].setHorizontalAlignment(SwingUtilities.CENTER);
        set[0].setIcon(new ImageIcon(icons[0]));
        set[0].setLocation(300, 50);
        set[0].setOpaque(false);
        bgicon.add(set[0]);
        set[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                set[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
                Image icontem = icons[0].getScaledInstance(50, 50, 0);
                set[0].setIcon(new ImageIcon(icontem));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                set[0].setIcon(new ImageIcon(icons[0]));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Gameinterface1.music.clip.close();
                if (Gameinterface1.rand <=9) {
                    Gameinterface1.music.adj(Gameinterface1.musicArrays[Gameinterface1.rand -= 1], 1, 0f);
                }
            }
        });
        //下一首
        set[1].setSize(60, 60);
        set[1].setHorizontalAlignment(SwingUtilities.CENTER);
        set[1].setIcon(new ImageIcon(icons[1]));
        set[1].setLocation(400, 50);
        set[1].setOpaque(false);
        bgicon.add(set[1]);
        set[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                set[1].setCursor(new Cursor(Cursor.HAND_CURSOR));
                Image icontem = icons[1].getScaledInstance(55, 55, 0);
                set[1].setIcon(new ImageIcon(icontem));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                set[1].setIcon(new ImageIcon(icons[1]));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                Gameinterface1.music.clip.close();
                if (Gameinterface1.rand <=9) {
                    Gameinterface1.music.adj(Gameinterface1.musicArrays[Gameinterface1.rand += 1], 1, 0f);
                }
            }
        });
        //静音
        set[2].setSize(60, 60);
        set[2].setHorizontalAlignment(SwingUtilities.CENTER);
        set[2].setIcon(new ImageIcon(icons[2]));
        set[2].setLocation(500, 50);
        set[2].setOpaque(false);
        bgicon.add(set[2]);
        set[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                set[2].setCursor(new Cursor(Cursor.HAND_CURSOR));
                Image icontem = icons[2].getScaledInstance(50, 50, 0);
                set[2].setIcon(new ImageIcon(icontem));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                set[2].setIcon(new ImageIcon(icons[2]));
            }
        });
        //音乐库菜单

        JMenuBar jMenuBar=new JMenuBar();
        JMenu jMenu=new JMenu("音乐库");
        jMenu.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem=new JMenuItem("Radio Edit");
        jMenuItem.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem1=new JMenuItem("Night Cruising");
        jMenuItem1.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem2=new JMenuItem("Home");
        jMenuItem2.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem3=new JMenuItem("Umbrella");
        jMenuItem3.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem4=new JMenuItem("即使那就是你的幸福");
        jMenuItem4.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem5=new JMenuItem("天气之子");
        jMenuItem5.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem6=new JMenuItem("Love is Gone");
        jMenuItem6.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem7=new JMenuItem("哪里都是你");
        jMenuItem7.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        JMenuItem jMenuItem8=new JMenuItem("Memories");
        jMenuItem8.setFont(new Font("方正字迹-龙吟体 简",Font.BOLD,20));
        jMenuBar.setSize(70,28);
        jMenuBar.setVisible(false);
        jMenuBar.setBorder(null);
        jMenuBar.setLocation(600,30);
        jMenuBar.setBackground(colortest);
        jMenu.setMaximumSize(new Dimension(130,56));
        jMenu.setMenuLocation(-20,76);
        jMenuItem.setBackground(Color.magenta);
        jMenuItem.setActionCommand(jMenuItem.getText());
        jMenuItem.setBorder(null);
        jMenuItem.addActionListener(this);
        jMenuItem1.setBackground(Color.magenta);
        jMenuItem1.setActionCommand(jMenuItem1.getText());
        jMenuItem1.addActionListener(this);
        jMenuItem1.setBorder(null);
        jMenuItem2.setBackground(Color.magenta);
        jMenuItem2.setActionCommand(jMenuItem2.getText());
        jMenuItem2.addActionListener(this);
        jMenuItem2.setBorder(null);
        jMenuItem3.setBackground(Color.magenta);
        jMenuItem3.setActionCommand(jMenuItem3.getText());
        jMenuItem3.addActionListener(this);
        jMenuItem3.setBorder(null);
        jMenuItem4.setBackground(Color.magenta);
        jMenuItem4.setActionCommand(jMenuItem4.getText());
        jMenuItem4.addActionListener(this);
        jMenuItem4.setBorder(null);
        jMenuItem5.setBackground(Color.magenta);
        jMenuItem5.setActionCommand(jMenuItem5.getText());
        jMenuItem5.addActionListener(this);
        jMenuItem5.setBorder(null);
        jMenuItem6.setBackground(Color.magenta);
        jMenuItem6.setActionCommand(jMenuItem6.getText());
        jMenuItem6.addActionListener(this);
        jMenuItem6.setBorder(null);
        jMenuItem7.setBackground(Color.magenta);
        jMenuItem7.setActionCommand(jMenuItem7.getText());
        jMenuItem7.addActionListener(this);
        jMenuItem7.setBorder(null);
        jMenuItem8.setBackground(Color.magenta);
        jMenuItem8.setActionCommand(jMenuItem8.getText());
        jMenuItem8.addActionListener(this);
        jMenuItem8.setBorder(null);
        jMenu.add(jMenuItem);
        jMenu.add(jMenuItem1);
        jMenu.add(jMenuItem2);
        jMenu.add(jMenuItem3);
        jMenu.add(jMenuItem4);
        jMenu.add(jMenuItem5);
        jMenu.add(jMenuItem6);
        jMenu.add(jMenuItem7);
        jMenu.add(jMenuItem8);
        jMenuBar.add(jMenu);
        jMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                jMenu.setBorder(BorderFactory.createLineBorder(Color.blue));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                jMenu.setBorder(null);
            }
        });
        set[3].setSize(60, 60);
        set[3].setHorizontalAlignment(SwingUtilities.CENTER);
        set[3].setIcon(new ImageIcon(icons[3]));
        set[3].setLocation(600, 50);
        set[3].setOpaque(false);
        bgicon.add(jMenuBar);
        bgicon.add(set[3]);
        set[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    if (flag1==1) {
                        flag1=0;
                        jMenuBar.setVisible(true);
                        colorT = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                while (true) {
                                    try {
                                        if (alpha<255) {
                                            colortest = new Color(0, 0, 255,alpha+=5);
                                            jMenuBar.setBackground(colortest);
                                        } else
                                            break;
                                        Thread.sleep(50);
                                    } catch (InterruptedException interruptedException) {
                                        interruptedException.printStackTrace();
                                    }
                                }
                            }
                        });colorT.start();
                    }
                    else if (flag1==0) {
                        flag1=1;
                        colorT.stop();
                        jMenuBar.setVisible(false);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                set[3].setCursor(new Cursor(Cursor.HAND_CURSOR));
                Image icontem = icons[3].getScaledInstance(50, 50, 0);
                set[3].setIcon(new ImageIcon(icontem));

            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                set[3].setIcon(new ImageIcon(icons[3]));
            }
        });
//        //滑块值
//        Value.setSize(100, 100);
//        Value.setLocation(820, 30);
//        Value.setText("100");
//        Value.setFont(new Font("姚体", Font.BOLD, 25));
//        Value.setForeground(Color.ORANGE);
//        bgicon.add(Value);
//        //添加滑块方法
//        musicslider.setSize(500,45);
//        musicslider.setOpaque(false);
//        musicslider.setLocation(250,250);
//        musicslider.setMaximum(100);
//        musicslider.setMinimum(0);
//        musicslider.setFont(new Font("姚体",Font.BOLD,10));
//        musicslider.setForeground(Color.blue);
//        musicslider.setValue(100);
//        musicslider.setMinorTickSpacing(10);
//        musicslider.setMajorTickSpacing(5);
//        musicslider.setPaintTicks(true);
//        musicslider.setPaintTrack(true);
//        musicslider.setPaintLabels(true);
//        bgicon.add(musicslider);
//        musicslider.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                Value.setText(String.valueOf(musicslider.getValue()));
//                musicslider.setValue(musicslider.getValue());
//                if (musicslider.getValue()<100)
//                    dB-=5;
//                musicslider.setToolTipText(String.valueOf(musicslider.getValue()));
//            }
//        });
        /*
         *
         *
         */

        //给指定的刻度值显示自定义标签
//        hashtable = new Hashtable<Integer, JLabel>();
//        hashtable.put(0, new JLabel("静音")); // 0 刻度位置，显示 "Start"
//        hashtable.put(50, new JLabel("中")); //50 刻度位置，显示 "Middle"
//        hashtable.put(100, new JLabel("高")); //100 刻度位置，显示 "End"
//        //将刻度值和自定义标签的对应关系设置到滑块(设置后不再显示默认的刻度值)
//        musicslider.setLabelTable(hashtable);

        //返回标签
        returnLabel.setSize(120, 90);
        returnLabel.setLocation(0, 20);
        returnLabel.setText("返回主界面");
        returnLabel.setFont(new Font("方正字迹-龙吟体 简", Font.BOLD, 10));
        returnLabel.setHorizontalTextPosition(SwingUtilities.CENTER);
        bgicon.add(returnLabel);
        returnLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                returnLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                returnLabel.setFont(new Font("方正字迹-龙吟体 简", Font.BOLD, 15));
                returnLabel.setIcon(new ImageIcon("images/返回big.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                returnLabel.setIcon(new ImageIcon("images/返回.png"));
                returnLabel.setFont(new Font("方正字迹-龙吟体 简", Font.BOLD, 10));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        setSize(Wid, Hei);
        setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                if (Opacity != (float)1.0) {
                                    Opacity += (float)0.1;
                                    setOpacity(Opacity);
                                } else
                                    break;
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                thread.setDaemon(true);
                thread.start();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.drawRoundRect(0, 0, Wid, Hei, 30, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Gameinterface1.music.clip.close();
        String command=e.getActionCommand();
        switch (command) {
            case "Radio Edit":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[8], 0, dB);
                break;
            case "Night Cruising":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[0], 0, dB);
                break;
            case "Home":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[1], 0, dB);
                break;
            case "Umbrella":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[2], 0, dB);
                break;
            case "即使那就是你的幸福":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[3], 0, dB);
                break;
            case "天气之子":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[4], 0, dB);
                break;
            case "Love is Gone":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[5], 0, dB);
                break;
            case "哪里都是你":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[6], 0, dB);
                break;
            case "Memories":
                Gameinterface1.music.adj(Gameinterface1.musicArrays[7], 0, dB);
                break;
        }
    }

//    public static void main(String[] args) {
//        new Musicset();
//    }
}