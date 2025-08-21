package org.kdepo.games.ploshchadka.inputs;

import org.kdepo.games.ploshchadka.Ploshchadka;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private Ploshchadka ploshchadka;

    public MouseInputs() {
        ploshchadka = Ploshchadka.getInstance();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ploshchadka.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ploshchadka.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ploshchadka.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ploshchadka.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ploshchadka.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ploshchadka.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ploshchadka.mouseExited(e);
    }

}
