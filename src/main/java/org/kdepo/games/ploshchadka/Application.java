package org.kdepo.games.ploshchadka;

import java.awt.*;

public class Application implements Runnable {

    private final Ploshchadka ploshchadka;

    private final ViewPanel viewPanel;
    private final int FPS_SET = 60;
    private final int UPS_SET = 60;

    private final boolean SHOW_FPS_UPS = false;

    public Application() {
        ploshchadka = Ploshchadka.getInstance();

        viewPanel = new ViewPanel(this);
        new ApplicationWindow(viewPanel);
        viewPanel.requestFocusInWindow();

        Thread applicationThread = new Thread(this);
        applicationThread.start();
    }

    public void update() {
        ploshchadka.update();
    }

    public void render(Graphics g) {
        ploshchadka.draw(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {

                update();
                updates++;
                deltaU--;

            }

            if (deltaF >= 1) {

                viewPanel.repaint();
                frames++;
                deltaF--;

            }

            if (SHOW_FPS_UPS)
                if (System.currentTimeMillis() - lastCheck >= 1000) {

                    lastCheck = System.currentTimeMillis();
                    System.out.println("FPS: " + frames + " | UPS: " + updates);
                    frames = 0;
                    updates = 0;

                }
        }
    }
}
