package org.kdepo.games.ploshchadka.model.base.screens;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public abstract class AbstractScreen {

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public abstract void mouseDragged(MouseEvent e);

    public abstract void mouseEntered(MouseEvent e);

    public abstract void mouseExited(MouseEvent e);

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);

    public abstract void keyTyped(KeyEvent e);

    public abstract void windowLostFocus(WindowEvent e);

    public abstract void windowGainedFocus(WindowEvent e);

}
