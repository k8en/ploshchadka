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

    public interface Character {
        // Team Japan
        int KUNIO = 1;
        int YORITSUNE = 2;
        int SAJI = 3;
        int HORIBATA = 4;
        int IWAKABE = 5;
        int GENEI = 6; // GK
        int UGAJIN = 7;
        int ONITAKE = 8;
        int KUMON = 9;
        int KAIZUKI = 10;
        int TSUNEWO = 11;
        int CARLOS = 12; // GK

        // Team Korea
        int CHON_IL = 13;
        int YONG_DOC = 14;
        int MAN_SU = 15;
        int MING_HO = 16;
        int YONG_SU = 17;
        int SUN_CHOL = 18;
    }

    public interface ScreenSize {
        int WIDTH = 800;
        int HEIGHT = 600;
    }

    public interface Team {
        int REFEREE = 0;
        int JAPAN = 1;
        int KOREA = 2;
    }

    private Constants() {
        throw new RuntimeException("Instantiation is not allowed");
    }
}
