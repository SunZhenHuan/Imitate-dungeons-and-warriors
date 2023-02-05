package com;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Thread1 implements Runnable{
    @Override
    public void run() {
        GetMessage g=new GetMessage();
        while(true){
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                if(GetMessage.getConnection().isClosed()) {
                    JOptionPane.showMessageDialog(null, "网络连接断开");
                    System.exit(0);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }


            String sql="select * from numberstate where id='"+LoginInterface.UUid+"'";
            ResultSet rs= g.cha(sql);
            try {
                rs.next();
                if(rs.getInt("state")==1)
                {
                    sql="update numberstate set state=2 where id='"+LoginInterface.UUid+"'";
                    new Sql().statmevalue(sql);
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        }
    }
}
