package org.kdepo.games.ploshchadka;

import org.kdepo.games.ploshchadka.model.base.screens.AbstractScreen;
import org.kdepo.games.ploshchadka.model.custom.screens.TrainingScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class Ploshchadka {

    private static Ploshchadka instance;

    private AbstractScreen activeScreen;

    private int screenWidth;
    private int screenHeight;

    public static Ploshchadka getInstance() {
        if (instance == null) {
            instance = new Ploshchadka();
        }
        return instance;
    }

    private Ploshchadka() {
        activeScreen = new TrainingScreen(this);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void update() {
        activeScreen.update();
    }

    public void draw(Graphics g) {
        activeScreen.draw(g);
    }

    public void mouseClicked(MouseEvent e) {
        activeScreen.mouseClicked(e);
    }

    public void mousePressed(MouseEvent e) {
        activeScreen.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        activeScreen.mouseReleased(e);
    }

    public void mouseMoved(MouseEvent e) {
        activeScreen.mouseMoved(e);
    }

    public void mouseDragged(MouseEvent e) {
        activeScreen.mouseDragged(e);
    }

    public void mouseEntered(MouseEvent e) {
        activeScreen.mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        activeScreen.mouseExited(e);
    }

    public void keyPressed(KeyEvent e) {
        activeScreen.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        activeScreen.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {
        activeScreen.keyTyped(e);
    }

    public void windowLostFocus(WindowEvent e) {
        activeScreen.windowLostFocus(e);
    }

    public void windowGainedFocus(WindowEvent e) {
        activeScreen.windowGainedFocus(e);
    }

}
