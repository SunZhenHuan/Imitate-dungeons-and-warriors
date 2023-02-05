package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter{
    int Color;
    int flag=1;
    JButton j;
    public Mouse(int Color, JButton j)
    {
        this.Color=Color;
        this.j=j;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        Thread thread=new Thread(() -> {
            while (true)
            {
                synchronized("") {
                    if (Color < 255 && flag == 1)
                        j.setBackground(new Color(Color++, 0, 0));
                    else {
                        flag = 0;
                        break;
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        Thread thread=new Thread(() -> {
            while (true)
            {
                synchronized ("") {
                    if (Color > 170 && flag == 0)
                        j.setBackground(new Color(Color--, 0, 0));
                    else {
                        flag = 1;
                        break;
                    }
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
