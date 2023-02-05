package com;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class ImagesAndExeSendFileNameMain {
    //图片和exe文件发送到名字表的主方法
   static Statement statement=null;
   static Connection connection;
   static ResultSet resultSet=null;
   static String username="root";
   static String name="",name1="";
   static int flag=0;
   static String password="szh271504";
   static String date=new Date().toLocaleString();
   static String url="jdbc:mysql://127.0.0.1:3306/game?useUnicode=true&characterEncoding=utf8";
   public static void getName(String name)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                connection= DriverManager.getConnection(url,username,password);
                statement=connection.createStatement();
                System.out.println("发送了文件" + name);
                statement.executeUpdate("insert into imagename_tab values(name)");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void sendimagename_tab()
    {
        Connection connection=GetMessage.getConnection();
        try {
            Statement statement=connection.createStatement();
            String sql="select *from image_tab";//查询这个表的所有信息
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next())
            {
                String image=resultSet.getString("name");//取出image_tab中的所有文件名字
                if (!image.equals("求最大最小.exe"))//如果没有这个文件则发送
                {
                    flag=1;
                    String s="select *from imagename_tab where name='"+image+"'";
                    ResultSet rs=new GetMessage().cha(s);
                    if (!rs.next())//没有查到
                    {
                        name+=image+"\n";
                        new GetMessage().chaValue("insert into imagename_tab values('"+image+"')");//则插入
                    }
                    else
                        flag=0;
                }
            }
            statement.close();
            connection.close();
            resultSet.close();
            if (flag==1) {
                JOptionPane.showMessageDialog(null, "补全的文件为:"+"\n"+name);
            }
            else
                JOptionPane.showMessageDialog(null,"文件都已经存在！");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//       sendimagename_tab();
//       System.gc();
//    }
}
