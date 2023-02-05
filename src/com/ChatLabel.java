package com;

import com.sun.java.swing.plaf.motif.MotifTextUI;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;
import com.sun.java.swing.plaf.windows.WindowsTextPaneUI;

import javax.swing.*;
import javax.swing.plaf.synth.SynthSeparatorUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ChatLabel extends JLabel {
    JSeparator spit=new JSeparator(SwingConstants.VERTICAL);
    static JButton after=new JButton();
    static ImageIcon[]images={
            new ImageIcon("images//詹姆斯图像.png"),
    };
    static JTextArea show=new JTextArea();
    static int flag=-1;
    static JLabel rolename=new JLabel();
    static JLabel iconlb=new JLabel();
    MouseAdapter mouseAdapter=new MouseAdapter() {
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            super.mouseWheelMoved(e);
            int max=5;
            int wheel=e.getWheelRotation();
            if (wheel==1)//向下滚动此时show的y坐标为加
            {
                if (show.getLocation().y<max)//如果此时的y坐标小于边界就可以向下滑动加
                {
                    show.setLocation(210, show.getLocation().y += 15);
                    after.setLocation(1500,after.getLocation().y+=15);
                }
                else;//否则不能
            }
            else//向上滚动
            {
                show.setLocation(210, show.getLocation().y -= 15);
                after.setLocation(1500, after.getLocation().y -= 15);
            }
        }
    };
    public ChatLabel(ImageIcon icon) {
        setIcon(icon);
        setSize(1920,330);
        setLocation(0,750);
        //任务按钮
        after.setBackground(Color.orange);
        after.setSize(150,50);
        after.setLocation(1500,250);
        after.setText("确认");
        after.setBorder(BorderFactory.createEtchedBorder());
        after.setVisible(false);
        //人物名称
        rolename.setSize(150,50);
        rolename.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        rolename.setForeground(Color.PINK);
        rolename.setHorizontalAlignment(CENTER);
        rolename.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,25));
        rolename.setLocation(20,160);
        //显示消息
        show.setLocation(210,5);
        show.setFocusable(false);
        show.setEditable(false);
        show.setSize(1700,10000);
        show.setLineWrap(true);
        show.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        show.setOpaque(false);
        //想要键盘可以使用就必须把文本域设为禁用，因为一开始你的所以输入法都是在文本域输入的，就算设置为不可编辑键盘还是不能将人物移动。或者设置没有焦点以及不可编辑也行
//        show.setEnabled(false);
        show.setForeground(Color.CYAN);
        show.setFont(new Font("字酷堂匡山楷书 简",Font.PLAIN,35));
        show.setCaret(new MotifTextUI.MotifCaret());
        show.setCaretColor(Color.MAGENTA);
        addMouseWheelListener(mouseAdapter);
        show.addMouseWheelListener(mouseAdapter);
        //分割线
        spit.setForeground(Color.RED);
        spit.setOpaque(false);
        spit.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        spit.setSize(4,330);
        spit.setLocation(200,0);
        //人物面板
        iconlb.setLocation(20,10);
        iconlb.setSize(160,130);
        iconlb.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        Thread thread=new Thread(()->{
            while (true)
            {
                String name=rolename.getText();
                switch (name) {
                    case "詹姆斯":
                        iconlb.setIcon(images[0]);
                        flag = 0;//如果点击了这个人物则把标志改为0

                        after.setVisible(true);
                        break;
                    case "卢本伟":
                        iconlb.setIcon(images[0]);
                        iconlb.setIcon(null);
                        flag = 1;
                        after.setVisible(true);
                        break;
                    case "wdwadw":
                        iconlb.setIcon(images[0]);
                        iconlb.setIcon(null);
                        flag = 2;
                        after.setVisible(true);
                        break;
                }
                }
        });
        thread.start();
        add(spit);
        add(after);
        add(show);
        add(iconlb);
    }

}
