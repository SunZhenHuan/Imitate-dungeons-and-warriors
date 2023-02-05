package com;

import com.sun.deploy.panel.AdvancedNetworkSettingsDialog;
import com.sun.java.swing.plaf.motif.MotifTextUI;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;


import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class MapFrame extends JFrame implements ActionListener{
//    public static void main(String[] args) {
//        new MapFrame();
//    }
    static final int Width=Toolkit.getDefaultToolkit().getScreenSize().width;
    static final int Height=Toolkit.getDefaultToolkit().getScreenSize().height;
    final JPanel content=new JPanel();
    final JTextArea chatArea=new JTextArea();
    final JLabel Shopping=new JLabel("商城");
    final JScrollPane scrollPane=new JScrollPane();
    final JPanel chatPanel=new JPanel();
    final JTextField input=new JTextField();
    final JTextArea display=new JTextArea();
    final JScrollPane scroll=new JScrollPane();
    Sql sql=new Sql();
    String[] str={"在线","不在线"};
    JLabel money=new JLabel("金币");
    JLabel gold=new JLabel();
    Thread t;
    String info="";
    int[] state =new int [9999999];
    String[]strings=new String[]{"好","友","列","表"};
    JButton friendlist=new JButton()
    {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(0,210,0,180));
            g.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,30));
            g.drawString(strings[0],10,30);
            g.drawString(strings[1],10,80);
            g.drawString(strings[2],10,130);
            g.drawString(strings[3],10,180);
        }
    };
    JPanel friendPanel=new JPanel();
    int alpha=0,alpha1=100;
    int green=100;
    int flag=1,flag1=1,flag2=1;
    int friendpanelWid=0;
    JTextField search=new JTextField("输入你想搜索好友的名字");
    JButton addto=new JButton("搜索");
    PrintWriter out=null;// 声明输出流对象
    GetMessage getMessage=new GetMessage();
    JLabel icon=new JLabel(AllGetMethod.GetIcon("images/地图flag.gif",700,700, 0));
    JLabel show=new JLabel(AllGetMethod.GetIcon("images/显示地图.png",200,200,0));//显示地图标签
    JLabel putaway=new JLabel(AllGetMethod.GetIcon("images/收起地图.png",200,200,0));//显示地图标签
    JLa time=new JLa(0,550,400,200,10);
    JLabel bgicon=new JLabel(AllGetMethod.GetIcon("images//地图背景.png",Width,Height,0));
    JLabel map=new JLabel(AllGetMethod.GetIcon("images//地图.png",1000,600,0));
    //图片名字
    String[]filename=new String[]
            {
                    "images/场景(1).png",
                    "images/场景(2).png",
                    "images/场景(3).png",
                    "images/场景(4).png",
                    "images/场景(5).png",
                    "images/场景(6).png",
                    "images/场景(7).png",
                    "images/场景(8).png",
                    "images/场景(9).png",
                    "images/场景(10).png",
                    "images/场景(11).png",
                    "images/场景(12).png",
                    "images/场景(13).png",
                    "images/场景(14).png",
                    "images/场景(15).png",
                    "images/场景(16).png",
            };
    //十六个坐标
    Point[]points=new Point[]
            {
                    new Point(748,462),
                    new Point(432,82),
                    new Point(415,235),
                    new Point(735,237),
                    new Point(598,243),
                    new Point(38,263),
                    new Point(202,265),
                    new Point(325,300),
                    new Point(688,292),
                    new Point(770,305),
                    new Point(650,345),
                    new Point(735,360),
                    new Point(855,353),
                    new Point(5,380),
                    new Point(660,385),
                    new Point(740,401)

            };
    JLabel[]Scenes=new JLabel[16];
    public void createClientSocket() {
        try {
            String ip= InetAddress.getLocalHost().getHostAddress();
            Socket socket = new Socket(ip, 4399);// 创建套接字对象
            out = new PrintWriter(socket.getOutputStream(), true);// 创建输出流对象
            new ClientThread(socket).start();// 创建并启动线程对象
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class ClientThread extends Thread {
        Socket socket;
        public ClientThread(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));// 创建输入流对象
                while (true) {
                    String info = in.readLine();// 读取信息
                    display.append(new Date().toLocaleString()+"\n"+info+"\n");// 在文本域中显示信息
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void send() {
        info=input.getText();// 获得输入的信息
        if (info.equals("")) {
            return;// 如果没输入信息则返回，即不发送
        }
        out.println(info);// 发送信息
        out.flush();// 刷新输出缓冲区
        display.append(new Date().toLocaleString()+"\n"+LoginInterface.userField.getText()+info+"\n");
        input.setText(null);// 清空文本框
    }
    public MapFrame()
    {
        super();
        EventQueue.invokeLater(() -> {
            try {
                createClientSocket();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        state[0]=0;
        //先查人物名称
        String s="select *from rolename_tab where id='"+LoginInterface.UUid+"'";//查到这个人的id一条结果
        ResultSet rs=getMessage.cha(s);
        try {
            if (rs.next())//查到了
            {
                String rolename= rs.getString("rolename");//取得人物名字
                chusheng.role=rolename;//赋给这个用户大厅的人物
                yongshi.role=rolename;
            }
        }catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        content.setSize(650,470);
        content.setLocation(1220,594);
        content.setOpaque(false);
        content.setBackground(Color.RED);
        content.setLayout(null);
        //文本域
        Thread thread=new Thread(new Runnable() {
            String name="";
            @Override
            public void run() {
                while (true)
                {

                    try {
                        int i=0;
                        while(true) {
                            if (alpha < 255 && flag == 1) {
                                chatArea.setForeground(new Color(0, 0, 255, alpha++).brighter());
                                if (alpha == 254) flag = 0;
                                i += 10;
                                if (i == 2000) break;
                            } else {
                                chatArea.setForeground(new Color(0, 0, 255, alpha--).brighter());
                                if (alpha == 0) flag = 1;
                                i += 10;
                                if (i == 2000) break;
                            }
                            Thread.sleep(10);
                        }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                        String state="在线";
                    ResultSet resultSet=getMessage.cha("select *from game_tab  where state='"+state+"'");
                    try {
                        while(resultSet.next())
                        {
                             name=name+resultSet.getString("username")+"已上线"+resultSet.getString("Logintime")+"\n";

                        }

                        chatArea.setText(name);
                        name="";
                         }catch (SQLException sqlException)
                         {
                             sqlException.printStackTrace();
                         }
                }
            }
        });
        thread.start();
        chatArea.setFont(new Font("芋泥啵啵茶",Font.PLAIN,20));
        chatArea.setBackground(Color.darkGray);
        chatArea.setCaretColor(Color.MAGENTA);
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
        chatArea.setEditable(false);
        chatArea.setCaret(new MotifTextUI.MotifCaret());
        chatArea.setLineWrap(true);
        //滚动面板
        scrollPane.setSize(650,470);
        scrollPane.setOpaque(false);
        scrollPane.setViewportView(chatArea);
        scrollPane.getVerticalScrollBar().setUI(new WindowsScrollBarUI());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setLocation(0,0);
        content.add(scrollPane);
        bgicon.add(content);


        bgicon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.err.println(e.getX()+"\t"+e.getY());
            }
        });
        //flag标志
        icon.setSize(660,490);
        icon.setLocation(1200,50);
        icon.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel text=new JLabel(LoginInterface.userField.getText());
        text.setSize(300,50);
        text.setFont(new Font("姚体",Font.BOLD,30));
        text.setForeground(Color.red);
        text.setHorizontalTextPosition(SwingUtilities.CENTER);
        text.setLocation(80,80);
//        text.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        icon.add(text);
//        icon.setBorder(new MatteBorder(MAXIMIZED_BOTH,MAXIMIZED_BOTH,MAXIMIZED_BOTH,MAXIMIZED_BOTH,new ImageIcon("images/Set.png")));
        bgicon.add(icon);

        //商城
        Shopping.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        Shopping.setSize(60,50);
        Shopping.setLocation(1733,16);
        Shopping.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Shopping.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        bgicon.add(Shopping);
        Shopping.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1) {
                    if (flag2==1)
                    new Supermarket(null, "商城", true);
                }
            }
        });


        //搜索好友框
        search.setSize(230,30);
        search.setLocation(1240,549);
        search.setOpaque(false);
        search.setForeground(Color.blue);
        search.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,20));
        bgicon.add(search);
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1)
                {
                    String text=search.getText().trim();
                    if (text.equals("输入你想搜索好友的名字"))
                        search.setText("");
                }
            }
        });
        //添加朋友
        //去掉按钮文字周围的焦点框
        addto.setFocusPainted(false);
        addto.setSize(52,30);
        addto.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        addto.setForeground(Color.BLACK);
        addto.setLocation(1475,550);
        addto.setBorder(null);
        addto.setBackground(Color.RED);
        bgicon.add(addto);
        addto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==MouseEvent.BUTTON1) {
                    String text = search.getText().trim();
                    String sql = "select *from game_tab where username='" + text + "'";
                    ResultSet rs = getMessage.cha(sql);
                    if (!text.equals("")&&!text.equals("输入你想搜索好友的名字"))//如果不等于空
                    {
                        try {
                            //如果搜到了
                            if (rs.next()) {
                                addto.setText("添加");
                            } else {
                                addto.setText("搜索");
                                JOptionPane.showMessageDialog(null, "没有你搜索的用户哦~~");
                            }
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }
                    }
                }

            }
        });
        //金币标签
        money.setSize(90,50);
        money.setForeground(Color.BLUE);
        money.setLocation(1228,15);
        money.setFont(new Font("字酷堂匡山楷书 简",Font.BOLD,40));
        bgicon.add(money);
        gold.setSize(200,50);
        gold.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        gold.setLocation(1325,15);
        gold.setForeground(Color.red);
        gold.setFont(new Font("字酷堂匡山楷书 简",Font.BOLD,40));
        bgicon.add(gold);
        //背景图片
        bgicon.setSize(Width,Height);
        t=new Thread(time);
        bgicon.add(time);
        t.start();

        //聊天面板
        chatPanel.setSize(460,330);
        chatPanel.setLayout(null);
        chatPanel.setBorder(null);
        chatPanel.setLocation(0,750);
        chatPanel.setOpaque(false);

        display.setBackground(Color.BLACK);
        display.setEditable(false);
        display.setCaretPosition(display.getDocument().getLength());
        display.setForeground(new Color(0,255,0).brighter());
        scroll.setSize(chatPanel.getSize().width,chatPanel.getSize().height-30);
        scroll.setViewportView(display);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUI(new WindowsScrollBarUI());
        input.setSize(chatPanel.getSize().width-80,30);
        input.setLocation(0,300);
        input.setForeground(Color.green);
        input.addActionListener(this);
        input.setOpaque(false);
        final JButton button = new JButton();
        button.setSize(80,30);
        button.setText("Enter");
        button.setFocusPainted(false);
        button.addActionListener(this);
        button.setLocation(380,300);
        button.setBackground(Color.GREEN);
        chatPanel.add(button);
        chatPanel.add(input);
        chatPanel.add(scroll);
        bgicon.add(chatPanel);
        //好友面板
        friendPanel.setLayout(null);
        friendPanel.setSize(friendpanelWid,bgicon.getHeight()/2);
        friendPanel.setLocation(1420,0);
        friendPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        friendPanel.setBackground(new Color(0,0,0,150));
        //好友按钮
        friendlist.setSize(50,200);
        friendlist.setToolTipText("好友列表");
        friendlist.setContentAreaFilled(false);
        friendlist.setCursor(new Cursor(Cursor.HAND_CURSOR));
        friendlist.setBorder(BorderFactory.createEtchedBorder());
        friendlist.setBackground(new Color(green, 0, 0,alpha1+100));
        friendlist.setLocation(bgicon.getWidth()-friendlist.getWidth(),bgicon.getHeight()/2-friendlist.getHeight()/2);
        friendlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (flag1==1) {
                    Thread thread1 = new Thread(() -> {
                        while (true) {
                            if (friendpanelWid < 500)
                                friendPanel.setSize(friendpanelWid++, bgicon.getHeight() / 2);
                            else {
                                flag1=0;
                                flag2=0;
                                strings[0]="";
                                strings[1]="返";
                                strings[2]="回";
                                strings[3]="";
                                break;
                            }
                            repaint();
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    });
                    thread1.start();
                }
                if (flag1==0)
                {
                    Thread thread1=new Thread(() -> {
                        while (true)
                        {
                            if (friendpanelWid>=0)
                                friendPanel.setSize(friendpanelWid--, bgicon.getHeight()/2);
                            else
                            {
                                flag1=1;
                                flag2=1;
                                strings[0]="好";
                                strings[1]="友";
                                strings[2]="列";
                                strings[3]="表";
                                break;
                            }
                            try {
                                repaint();
                                Thread.sleep(1);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    });
                    thread1.start();
                }
                }
        });
        add(friendPanel);
        this.add(bgicon);
        bgicon.add(friendlist);
        setLayout(null);
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("images//image5.png").getImage(),new Point(0,0),""));
        setUndecorated(true);
        setSize(Width,Height);
        setVisible(true);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    String sql1 = "select *from game_tab where id='" + LoginInterface.UUid + "'";
                    ResultSet rs = sql.cha(sql1);
                    while (rs.next())
                    {
                        int money=rs.getInt("money");
                        gold.setText(String.valueOf(money));
                    }
                }catch (SQLException sqlException)
                {
                    sqlException.printStackTrace();
                }
                String sql2 = "update game_tab set state='" + str[0] + "' where id='" + LoginInterface.UUid + "'";
                sql.statmevalue(sql2);

            }
        });
        //关闭时更新金币数据
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String sql1 = "update game_tab set state='" + str[1] + "' where id='" + LoginInterface.UUid + "'";
                String sql2 = "update game_tab set money='" + gold.getText() + "' where id='" + LoginInterface.UUid + "'";
                sql.statmevalue(sql1);
                sql.statmevalue(sql2);
                JOptionPane.setRootFrame(new Gameinterface1());
            }
        });
        //场景
        for (int i = 0; i <Scenes.length ; i++) {
            Scenes[i]=new JLabel(AllGetMethod.GetIcon(filename[i],70,50,0));
            Scenes[i].setSize(80,70);
            Scenes[i].setLocation(points[i].getLocation());
            switch (i){
                case 0:
                    Scenes[0].setToolTipText("血族巢穴");
                    Scenes[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[0].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        Scenes[0].setIcon(AllGetMethod.GetIcon(filename[0],70,55,0));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        Scenes[0].setIcon(AllGetMethod.GetIcon(filename[0],70,50,0));
                    }
                });
                break;
                case 1:
                    Scenes[1].setToolTipText("极北之地");
                    Scenes[1].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[1].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[1].setIcon(AllGetMethod.GetIcon(filename[1],80,70,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[1].setIcon(AllGetMethod.GetIcon(filename[1],70,50,0));
                        }
                    });
                    break;
                case 2:
                    Scenes[2].setToolTipText("北夷部落");
                    Scenes[2].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[2].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[2].setIcon(AllGetMethod.GetIcon(filename[2],80,70,0));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[2].setIcon(AllGetMethod.GetIcon(filename[2],70,50,0));
                        }
                    });
                    break;
                case 3:
                    Scenes[3].setToolTipText("起源之地");
                    Scenes[3].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[3].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[3].setIcon(AllGetMethod.GetIcon(filename[3],90,60,0));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[3].setIcon(AllGetMethod.GetIcon(filename[3],80,50,0));
                        }
                    });
                    break;
                case 4:
                    Scenes[4].setToolTipText("长安城");
                    Scenes[4].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[4].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[4].setIcon(AllGetMethod.GetIcon(filename[4],75,60,0));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[4].setIcon(AllGetMethod.GetIcon(filename[4],70,50,0));
                        }
                    });
                    break;
                case 5:
                    Scenes[5].setToolTipText("勇士之地");
                    Scenes[5].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[5].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            dispose();
                            new yongshi();
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[5].setIcon(AllGetMethod.GetIcon(filename[5],75,60,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[5].setIcon(AllGetMethod.GetIcon(filename[5],70,50,0));
                        }
                    });
                    break;
                case 6:
                    Scenes[6].setToolTipText("王者峡谷");
                    Scenes[6].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[6].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[6].setIcon(AllGetMethod.GetIcon(filename[6],75,60,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[6].setIcon(AllGetMethod.GetIcon(filename[6],70,50,0));
                        }
                    });
                    break;
                case 7:
                    Scenes[7].setToolTipText("西域");
                    Scenes[7].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[7].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[7].setIcon(AllGetMethod.GetIcon(filename[7],75,60,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[7].setIcon(AllGetMethod.GetIcon(filename[7],70,50,0));
                        }
                    });
                    break;
                case 8:
                    Scenes[8].setToolTipText("秦");
                    Scenes[8].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[8].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[8].setIcon(AllGetMethod.GetIcon(filename[8],75,60,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[8].setIcon(AllGetMethod.GetIcon(filename[8],70,50,0));
                        }
                    });
                    break;
                case 9:
                    Scenes[9].setToolTipText("稷下");
                    Scenes[9].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[9].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[9].setIcon(AllGetMethod.GetIcon(filename[9],65,50,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[9].setIcon(AllGetMethod.GetIcon(filename[9],60,45,0));
                        }
                    });
                    break;
                case 10:
                    Scenes[10].setToolTipText("楚汉之地");
                    Scenes[10].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[10].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[10].setIcon(AllGetMethod.GetIcon(filename[10],75,60,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[10].setIcon(AllGetMethod.GetIcon(filename[10],60,50,0));
                        }
                    });
                    break;
                case 11:
                    Scenes[11].setToolTipText("魏");
                    Scenes[11].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[11].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[11].setIcon(AllGetMethod.GetIcon(filename[11],65,50,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[11].setIcon(AllGetMethod.GetIcon(filename[11],55,40,0));
                        }
                    });
                    break;
                case 12:
                    Scenes[12].setToolTipText("扶桑");
                    Scenes[12].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[12].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[12].setIcon(AllGetMethod.GetIcon(filename[12],65,50,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[12].setIcon(AllGetMethod.GetIcon(filename[12],55,40,0));
                        }
                    });
                    break;
                case 13:
                    Scenes[13].setToolTipText("出生入口");
                    Scenes[13].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[13].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            new chusheng();
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[13].setIcon(AllGetMethod.GetIcon(filename[13],75,55,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[13].setIcon(AllGetMethod.GetIcon(filename[13],70,50,0));
                        }
                    });
                    break;
                case 14:
                    Scenes[14].setToolTipText("蜀");
                    Scenes[14].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[14].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[14].setIcon(AllGetMethod.GetIcon(filename[14],60,45,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[14].setIcon(AllGetMethod.GetIcon(filename[14],50,40,0));
                        }
                    });
                    break;
                case 15:
                    Scenes[15].setToolTipText("吴");
                    Scenes[15].setCursor(new Cursor(Cursor.HAND_CURSOR));
                    Scenes[15].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            Scenes[15].setIcon(AllGetMethod.GetIcon(filename[15],75,60,0));
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                            Scenes[15].setIcon(AllGetMethod.GetIcon(filename[15],65,50,0));
                        }
                    });
                    break;
                    
            }
            int tem=i;
            Scenes[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(Scenes[tem].getToolTipText());
                }
            });
            map.add(Scenes[i]);
            }
        //地图图片
        //收起地图
        putaway.setSize(100,60);
        putaway.setLocation(900,0);
        map.add(putaway);
        putaway.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    map.setVisible(false);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                putaway.setCursor(new Cursor(Cursor.HAND_CURSOR));
                putaway.setIcon(AllGetMethod.GetIcon("images//收起地图.png",230,230,0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                putaway.setCursor(null);
                putaway.setIcon(AllGetMethod.GetIcon("images//收起地图.png",200,200,0));

            }
        });
        map.setLocation(0,0);
        map.setVisible(false);
        map.setSize(1000,600);
        map.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                map.setBorder(new WindowsBorders.ProgressBarBorder(Color.ORANGE,Color.MAGENTA));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                map.setBorder(null);

            }
        });
        bgicon.add(map);

        //显示地图按钮
        show.setSize(100,60);
        show.setLocation(82,64);
        bgicon.add(show);
        show.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON1) {
                    map.setVisible(true);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                show.setCursor(new Cursor(Cursor.HAND_CURSOR));
                show.setIcon(AllGetMethod.GetIcon("images/显示地图.png",230,230,0));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                show.setCursor(null);
                show.setIcon(AllGetMethod.GetIcon("images/显示地图.png",200,200,0));

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        send();
    }
}
