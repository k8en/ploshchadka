package org.kdepo.games.ploshchadka;

public class Constants {

    public interface AnimationName {
        String STAND_RIGHT = "STAND_RIGHT";
        String STAND_LEFT = "STAND_LEFT";
        String RUN_RIGHT = "RUN_RIGHT";
        String RUN_LEFT = "RUN_LEFT";
        String KICK_RIGHT = "KICK_RIGHT";
        String KICK_LEFT = "KICK_LEFT";
        String POWER_KICK_RIGHT = "POWER_KICK_RIGHT";
        String POWER_KICK_LEFT = "POWER_KICK_LEFT";
    }

    public interface ScreenSize {
        int WIDTH = 800;
        int HEIGHT = 600;
    }

    public interface Team {
        int REFEREE = 1;
        int TEST_LEFT = 2;
        int TEST_RIGHT = 3;
    }

    private Constants() {
        throw new RuntimeException("Instantiation is not allowed");
    }
}
