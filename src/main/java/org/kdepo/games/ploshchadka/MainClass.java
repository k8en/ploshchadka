package org.kdepo.games.ploshchadka;

public class MainClass {

    public static void main(String[] args) {
        // Initialize necessary classes and settings
        Simulation simulation = Simulation.getInstance();
        simulation.setScreenWidth(1024);
        simulation.setScreenHeight(960);

        // Start application
        new Application();
    }
}