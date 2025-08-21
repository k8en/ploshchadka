package org.kdepo.games.ploshchadka.inputs;

import org.kdepo.games.ploshchadka.Simulation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private Simulation simulation;

    public KeyboardInputs() {
        simulation = Simulation.getInstance();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        simulation.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        simulation.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        simulation.keyTyped(e);
    }
}
