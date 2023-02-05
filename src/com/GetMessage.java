package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;


public  class GetMessage {
     static String name;
     static int flag=1;
     static ResultSet resultSet=null;
     static Connection connection;
     static Statement statement=null;
     static String username="root";//用户名和你不一样也得改自己的
     static String password="*******";//我特意改的这里密码改成自己的
    static String url="jdbc:mysql://127.0.0.1:3306/game?useUnicode=true&characterEncoding=utf8";
//   static String url="jdbc:mysql://457v30h605.qicp.vip:31872/game?useUnicode=true&characterEncoding=utf8";联网才能使用的url
   static {
       //加载驱动
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("加载驱动成功！");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
           System.out.println("加载驱动失败！");
       }
       try {
           JOptionPane.showMessageDialog(null,"资源正在加载中请稍后....");
           connection= DriverManager.getConnection(url,username,password);
//           System.out.println("连接数据库成功！");
       } catch (SQLException throwables) {
           throwables.printStackTrace();
           if (flag==1)
           {
               System.out.println("连接数据库失败！");
               JOptionPane.showMessageDialog(null, "连接网络失败！", "请检查网络设置", JOptionPane.ERROR_MESSAGE);
               flag=0;
           }
       }
   }
    ///判断连是否连接
     public static   Boolean isConnection()
     {
         try {
           connection=DriverManager.getConnection(url,username,password);
             return true;
         } catch (SQLException throwables) {
             throwables.printStackTrace();
             return false;
         }

     }
    //获取连接方法
    public static  Connection getConnection()
    {

        return  connection;
    }

    //插入
    public  void chaValue(String sql)
    {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("插入成功！");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }
    //查询
    public ResultSet cha(String sql)
    {
        try {
          Statement statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
    //改变
    public void ChangeValue(String sql)
    {
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.err.println("执行成功！");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    //注册
    public void register(String sql)
    {
        try {
           Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.err.println("注册成功！");
            JOptionPane.showMessageDialog(null,"注册成功");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.err.println("4999999");
        }
    }
    //删除
    public void delete(String sql)
    {
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"删除账号成功！");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    //删除id
    public void deleteid(String sql)
    {
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.err.println("删除id成功！");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.err.println("id早已经没有！");
        }
    }
    //从数据库下载缺少的图片文件
public static void down (String sd){
    try {
        String s = "";
        File file = null;
        DownLogin.Downlabel.setText("正在下载" + sd + "文件" + "\n");//显示输出正在下载的文件
        DownLogin.autolabel.setText("已经下载文件个数为" + FileDownloadLogin.count1 + "|" + FileDownloadLogin.count);
        Statement stmt = getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("select * from image_tab where name='" + sd + "' ");
        byte[] data = null;
        String fileName = "";
        if (rs.next()) {
            data = rs.getBytes("file");
            fileName = rs.getString("name");
            s=".\\images\\"+fileName;
            file = new File(s);
            if (!file.exists()) {
                file.createNewFile(); // 如果文件不存在，则创建
            }
            FileOutputStream fos = new FileOutputStream(file);
            InputStream in = null;
            int size = 0;
            if (data.length > 0) {
                fos.write(data, 0, data.length);
            } else {
                while ((size = in.read(data)) != -1) {
                    fos.write(data, 0, size);
                }
                in.close();
            }
            fos.close();
            rs.close();
            stmt.close();
            System.out.println("从数据库下载文件成功");
            FileDownloadLogin.count1++;
    }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    //向数据库中查询
    public static ResultSet check (String sql) throws SQLException{
        Connection con= getConnection();
        ResultSet rs=null;

        try {
            Statement ps=con.createStatement();
            rs=ps.executeQuery(sql);
        } catch (SQLException e) {

            e.printStackTrace();
        }

        return  rs;
    }
}
