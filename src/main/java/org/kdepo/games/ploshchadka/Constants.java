package org.kdepo.games.ploshchadka;

public class Constants {

    public interface AnimationName {
        String STAND_RIGHT = "STAND_RIGHT";
        String STAND_LEFT = "STAND_LEFT";
        String RUN_RIGHT = "RUN_RIGHT";
        String RUN_LEFT = "RUN_LEFT";
        String KICK_RIGHT = "KICK_RIGHT";
        String KICK_LEFT = "KICK_LEFT";
    }

    public interface ScreenSize {
        int WIDTH = 1024;
        int HEIGHT = 960;
    }

    private Constants() {
        throw new RuntimeException("Instantiation is not allowed");
    }
}
