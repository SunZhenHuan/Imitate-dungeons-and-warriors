package com;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface Event extends MouseListener,Runnable {
    @Override
    void mouseEntered(MouseEvent e);

    @Override
    void mouseExited(MouseEvent e);

    @Override
    void mouseClicked(MouseEvent e);

    @Override
    void mousePressed(MouseEvent e);

    @Override
    void mouseReleased(MouseEvent e);

    @Override
    void run();
}
