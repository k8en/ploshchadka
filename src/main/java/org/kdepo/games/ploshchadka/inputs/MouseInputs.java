package org.kdepo.games.ploshchadka.inputs;

import org.kdepo.games.ploshchadka.Simulation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private Simulation simulation;

    public MouseInputs() {
        simulation = Simulation.getInstance();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        simulation.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        simulation.mouseMoved(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        simulation.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        simulation.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        simulation.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        simulation.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        simulation.mouseExited(e);
    }

}
