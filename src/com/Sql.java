package com;

import javax.swing.*;
import java.sql.*;

public class Sql {
    //查询
    public  ResultSet cha(String sql){
        Connection con= GetMessage.getConnection();
        ResultSet rs=null;
        Statement ps= null;
        try {
            ps = con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            assert ps != null;
            rs= ps.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }


    //将state值更改
    public  void statmevalue (String sql){
        Connection con=GetMessage.getConnection();
        try {

            Statement ps=con.createStatement();
            ps.executeUpdate(sql);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}