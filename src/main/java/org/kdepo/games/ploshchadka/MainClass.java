package org.kdepo.games.ploshchadka;

public class MainClass {

    public static void main(String[] args) {
        // Initialize necessary classes and settings
        Ploshchadka ploshchadka = Ploshchadka.getInstance();
        ploshchadka.setScreenWidth(Constants.ScreenSize.WIDTH);
        ploshchadka.setScreenHeight(Constants.ScreenSize.HEIGHT);

        // Start application
        new Application();
    }
}