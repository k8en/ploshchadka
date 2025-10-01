package org.kdepo.games.ploshchadka;

// "mvn clean compile assembly:single" to get executable jar
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