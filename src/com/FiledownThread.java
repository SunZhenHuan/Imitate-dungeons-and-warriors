package com;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;

public class FiledownThread  implements Runnable{

    public void time(){
        try {
            Statement stmt = FileDownloadLogin.con.createStatement();
            ResultSet rs1 = stmt.executeQuery("select * from imagename_tab");
            while(rs1.next())
            {
                String s=  rs1.getString("name");
                File f=new File(".\\images\\"+s);
                if(!f.exists()) {
                    GetMessage.down(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
    this.time();
    }
}
