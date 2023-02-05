package com;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;

public class DownExeFile {
    public static void downexe(String exeFile){
        try {
            String s = "";
            File file = null;
            Statement stmt = GetMessage.connection.createStatement();
            ResultSet rs=new GetMessage().cha("select *from image_tab where name='"+exeFile+"'");
            byte[]data;
            String fileName = "";
            if (rs.next()) {//查到了
                data = rs.getBytes("file");
                fileName = rs.getString("name");
                s=".\\"+fileName;
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
                JOptionPane.showMessageDialog(null,"更新完成！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
