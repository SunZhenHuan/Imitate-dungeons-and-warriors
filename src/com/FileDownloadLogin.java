package com;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Date;

public class FileDownloadLogin {
    public static Connection con=GetMessage.connection;
    static String s;
    static GetMessage getMessage=new GetMessage();
    static int BBSFlag=1;
    public static float count = 0, count1 = 0f;//count是缺少的文件个数；count1是已下载文件的个数
    static String hostname="";
    public FileDownloadLogin() {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = getMessage.cha("select *from bbs_tab where Hostname='" + hostname + "'");//向表查这个主机名,返回一条结果
        try {
            if (!resultSet.next())//如果没有查到
            {
                String insert_hostname_sql = "insert into bbs_tab values('','" + hostname + "','1.0')";//插入空的值
                String insert_hostname_sql1 = "insert into rulebbs_tab values('" + hostname + "','1.0')";//插入空的值
                getMessage.chaValue(insert_hostname_sql1);
                getMessage.chaValue(insert_hostname_sql);//插入
                BBSFlag=0;
            } else//查到了
            {
                String Message = resultSet.getString("BBS");//取出信息
                if (!Message.trim().equals(""))//不等于空的话
                    new UpdateHitFrame(true,hostname,Message);//显示更新窗体
                else
                    BBSFlag=0;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if (BBSFlag == 0) {
            GetMessage.getConnection();
            File fimages = new File(".\\images");//普通图片文件
            if (!fimages.isDirectory()) {
                fimages.mkdirs();
            }
            try {
                Statement stmt = con.createStatement();
                ResultSet rs1 = stmt.executeQuery("select * from imagename_tab ");
                while (rs1.next()) {
                    s = rs1.getString("name");
                    File f = new File(".\\images\\" + s);
                    if (!f.exists())
                        count++;//统计个数
                }
            } catch (
                    Exception e) {
                e.printStackTrace();
            }
            System.out.println(count);
            if (count != 0) //count不为0，代表需要下载资源
            {
                DownLogin dlg = new DownLogin();
                String s, sql = "select *from imagename_tab";
                try {
                    ResultSet rs = GetMessage.check(sql);
                    while (rs.next()) {
                        s = rs.getString("name");
                        File f = new File(".\\images\\" + s);
                        if (!f.exists()) {
                            GetMessage.down(s);
                        }
                    }
                    FiledownThread thread = new FiledownThread();
                    Thread t = new Thread(thread);
                    t.start();
                    System.gc();

                    while (true) {
                        if (ProgressBar.tag != 1) {
                            dlg.setVisible(false);
                            break;
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (GetMessage.isConnection()) {
                JOptionPane.showMessageDialog(null, "资源已经加载完成！", "资源完整", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.setRootFrame(new Frame());
            } else
                JOptionPane.showMessageDialog(null, "连接网络失败！", "请检查网络设置", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FileDownloadLogin();
    }
}

