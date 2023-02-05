package com;

import com.sun.java.swing.plaf.motif.MotifTextUI;
import com.sun.java.swing.plaf.windows.WindowsProgressBarUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.plaf.basic.BasicTextUI;
import javax.swing.plaf.metal.MetalProgressBarUI;
import javax.swing.plaf.multi.MultiPanelUI;
import javax.swing.plaf.multi.MultiProgressBarUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UpdateHitFrame extends JDialog {
    Point point=new Point();
    int hit1Y=0;
    int flag=1;
    int threadflag=1;
    double value=0.00;
    JLabel hit=new JLabel();
    static String MB="";
    static JProgressBar exeBar=new JProgressBar();
    JLabel[]hits=new JLabel[]{
            new JLabel(""),new JLabel("已经到顶啦~"),
    };
    Image image=Toolkit.getDefaultToolkit().createImage("ThemeImages//更新背景.png");
    JTextArea textArea=new JTextArea(10,10);
    JButton Ok=new JButton("点击更新");
    JPanel BBSPanel=new JPanel();
    int green=180;
    static String Message="";
    JPanel updatePanel=new JPanel()
    {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D=(Graphics2D) g;
            graphics2D.drawImage(image,0,0,this);
        }
    };
    public UpdateHitFrame(Boolean Visible,String hostname,String Message) {
        UpdateHitFrame.Message=Message;
        setSize(1000,664);
        setUndecorated(true);
//        setOpacity(0.8f);
        setLocationRelativeTo(null);
        setLayout(null);
        //hit提示
        hit.setSize(250,30);
        hit.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        hit.setForeground(Color.PINK);
        hit.setFont(new Font("字酷堂匡山楷书 简",Font.BOLD,20));
        hit.setLocation(740,528);
        //exe文件进度条
        exeBar.setSize(1000,40);
        exeBar.setOpaque(false);
        exeBar.setLocation(0,getSize().height-40);
        exeBar.setBorder(BorderFactory.createLineBorder(new Color(0,150,0,150)));
        exeBar.setMinimum(0);
        exeBar.setStringPainted(true);
        exeBar.setMaximum(100);
        exeBar.setForeground(Color.blue);
        ResultSet rs=new GetMessage().cha("select *from bbs_tab where Hostname='"+hostname+"'");//查询这台主机的版本
                try {
                    if (rs.next())//查到了
                    {
                        String version=rs.getString("version");//取出版本
                        ResultSet rs1=new GetMessage().cha("select *from rulebbs_tab where Hostname='"+hostname+"'");
                        if (rs1.next())
                        {
                            String version1=rs1.getString("version");
                            if (!version.equals(version1))
                            {
                                ResultSet resultSet=new GetMessage().cha("select *from image_tab where name='求最大最小.exe'");
                                if (resultSet.next())
                                {
                                    byte[]bytes=resultSet.getBytes("file");//byte[1024]=1KB,byte[1024*1024]=1MB
                                    String name=resultSet.getString("name");//取得文件名
                                    int a=bytes.length;
                                    MB=MathUtil.round(a,4);//计算出MB兆数
                                    double a1=Double.parseDouble(MB)/10.00;//a1=0.0006
                                   Thread thread=new Thread(() -> {
                                        while (true)
                                        {
                                            if (exeBar.getValue()<exeBar.getMaximum()) {
                                                exeBar.setValue((int) (value += a1));
                                                exeBar.setString("已经下载|"+(exeBar.getValue())+"%");
                                                hit.setText("已下载"+exeBar.getValue()+"%"+"总计"+MB+"MB");
                                            }
                                            else
                                            {
                                                DownExeFile.downexe(name);
                                                System.err.println("下载成功！");
                                                dispose();
                                                new GetMessage().ChangeValue("update bbs_tab set BBS='' where Hostname='"+hostname+"'");//变为空
                                                new GetMessage().ChangeValue("update bbs_tab set version='"+version1+"' where Hostname='"+hostname+"'");
                                                new FileDownloadLogin();
                                                break;
                                            }
                                            try {
                                                Thread.sleep(1);
                                            } catch (InterruptedException e1) {
                                                e1.printStackTrace();
                                            }
                                        }
                                    });
                                thread.start();
                                }
                            }
                        }
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
        //更新提示标签
//        hits[0].setBorder(BorderFactory.createLineBorder(Color.GREEN));
        hits[0].setHorizontalAlignment(SwingConstants.CENTER);
        hits[0].setFont(new Font("字酷堂匡山楷书 简",Font.BOLD,30));
        hits[0].setText("更新提示");
        hits[0].setForeground(Color.CYAN);
        hits[0].setSize(getSize().width,40);
        hits[0].setLocation(0,0);
        //到顶提示
        hits[1].setSize(80,20);
//        hits[1].setBorder(BorderFactory.createLineBorder(Color.BLUE));
        hits[1].setLocation(224,hit1Y);
        hits[1].setVisible(false);
        hits[1].setForeground(Color.PINK);
        //文本域
        textArea.setSize(532,99999999);
        textArea.setForeground(Color.PINK);
        textArea.setFont(new Font("字酷堂匡山楷书 简",Font.ITALIC,30));
        textArea.setEditable(false);
        textArea.setText(UpdateHitFrame.Message);
//        textArea.setText(GetMessage(UpdateHitFrame.Message=Message));
//        textArea.setBorder(BorderFactory.createLineBorder(Color.blue));
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setLocation(10,20);
        //文本面板
        BBSPanel.setSize(550,520);
        BBSPanel.setOpaque(false);
        BBSPanel.setLayout(null);
//        BBSPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        BBSPanel.setLocation(10,50);
        BBSPanel.add(textArea);
        BBSPanel.add(hits[1]);
        BBSPanel.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                int wheel=e.getWheelRotation();
                if (wheel==1)
                {
                    if (textArea.getLocation().y<20) {
                        textArea.setLocation(textArea.getLocation().x, textArea.getLocation().y += 10);
                    }
                    else{
                        Thread thread=new Thread(() -> {
                            while (true)
                            {
                                hits[1].setVisible(textArea.getLocation().y>=20);
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }
                        //显示已经到顶
                }
                else if (wheel==-1)
                    textArea.setLocation(textArea.getLocation().x,textArea.getLocation().y-=10);
            }
        });
        //按钮
        Ok.setSize(200,45);
        Ok.doClick();
        Ok.setBorder(BorderFactory.createEtchedBorder());
        Ok.setBackground(new Color(0,green,0));
        Ok.setFocusPainted(false);
        Ok.setLocation(getSize().width/2-Ok.getWidth()/2,getSize().height-Ok.getHeight()-40);
        Ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (flag==1) {
                    Thread thread = new Thread(() -> {
                        while (true) {
                            if (green < 255)
                                Ok.setBackground(new Color(0, green++, 0));
                            else {
                                flag = 0;
                                break;
                            }
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                if (flag==0) {
                    Thread thread = new Thread(() -> {
                        while (true) {
                            if (green > 180)
                                Ok.setBackground(new Color(0, green--, 0));
                            else {
                                flag = 1;
                                break;
                            }
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    });
                    thread.start();
                }
            }
        });
        //更新面板
        updatePanel.setSize(getSize());
        updatePanel.setOpaque(false);
        updatePanel.setLayout(null);
        updatePanel.setLocation(0,0);
        add(hit);
        add(hits[0]);
        add(Ok);
        add(exeBar);
        add(BBSPanel);
        add(updatePanel);
        setVisible(Visible);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.err.println(e.getX()+"\t"+e.getY());
            }

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
//        new UpdateHitFrame(true,"111","null");
//    }
}
