package org.kdepo.games.ploshchadka.model.custom.game;

public class PlayerParameters {

    private int playerId;

    private String name;

    private GameRole gameRole;

    private double runSpeed;

    private double dashSpeed;

    public PlayerParameters(int playerId, String name, GameRole gameRole, double runSpeed, double dashSpeed) {
        this.playerId = playerId;
        this.name = name;
        this.gameRole = gameRole;
        this.runSpeed = runSpeed;
        this.dashSpeed = dashSpeed;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameRole getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRole gameRole) {
        this.gameRole = gameRole;
    }

    public double getRunSpeed() {
        return runSpeed;
    }

    public void setRunSpeed(double runSpeed) {
        this.runSpeed = runSpeed;
    }

    public double getDashSpeed() {
        return dashSpeed;
    }

    public void setDashSpeed(double dashSpeed) {
        this.dashSpeed = dashSpeed;
    }

}
