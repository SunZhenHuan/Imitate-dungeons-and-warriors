package com;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//通用图片上传至数据库
public class ImagesFileUpload {
    static FileInputStream fis;
    static byte[] filebyte;
    public   static Connection con;
    static PreparedStatement pstmt=null;
    public static void getFileUpload()
    {
        int i=1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/game", "root", "szh271504");
           for (;i<=1;i++)
            {
                String fileNamePath = "C:\\Users\\1\\Desktop\\半成品\\images\\夜晚的冈特古村远景月亮.png";
                fis = new FileInputStream(fileNamePath);
                String fileName = fileNamePath.substring(fileNamePath.lastIndexOf("\\") + 1);
                filebyte = inputSwitchByte(fis);
                pstmt = con.prepareStatement("insert into image_tab(name,file) values(?,?)");
                pstmt.setString(1, fileName);
                pstmt.setBytes(2, filebyte);
                pstmt.executeUpdate();
                System.out.println("文件上传到数据库成功"+i);
            }
            fis.close();
            con.close();
            pstmt.close();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("文件上传失败找不到该文件");
        }
        System.gc();
    }
    /*
    文件转为字节
     */
    public static byte[] inputSwitchByte(InputStream input){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len;
        byte[] result = null;
        byte[] bt = new byte[4096];

        try {
            while( (len=input.read(bt)) != -1){
                baos.write(bt, 0, len);

            }
            result = baos.toByteArray();
            baos.close();
            input.close();
        } catch (IOException e) {
            System.out.print("IO操作失败");
        }finally{
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

//   public static void main(String[] args) {
//      getFileUpload();
//    }

    }
