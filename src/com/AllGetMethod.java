package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AllGetMethod {
    //获取图片的方法
    public static ImageIcon GetIcon(String filename,int Width,int Height,int hint)
    {
        Image image=Toolkit.getDefaultToolkit().createImage(filename).getScaledInstance(Width,Height,hint);
        return new ImageIcon(image);
    }
    public static JLabel GetJlabel(String path,int Wid,int Hei,int X,int Y)
    {
        JLabel jLabel=new JLabel();
        jLabel.setSize(Wid,Hei);
        jLabel.setLocation(X,Y);
        jLabel.setIcon(new ImageIcon(path));
        jLabel.add(Keepsecret.Ok);
        jLabel.add(Keepsecret.question);
        jLabel.add(Keepsecret.answer);
        jLabel.add(Keepsecret.hint1);
        jLabel.add(Keepsecret.hint2);
//        jLabel.add(hint("密保问题不能为空！",150,40,45,85,visible));
//        jLabel.add(hint("密保答案不能为空！",150,40,45,185,visible));
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX()+"\t"+e.getY());
            }
        });
        return jLabel;
    }
}
