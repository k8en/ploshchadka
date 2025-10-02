package org.kdepo.games.ploshchadka.inputs;

import org.kdepo.games.ploshchadka.Ploshchadka;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private final Ploshchadka ploshchadka;

    public KeyboardInputs() {
        ploshchadka = Ploshchadka.getInstance();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ploshchadka.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        ploshchadka.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        ploshchadka.keyTyped(e);
    }
}
