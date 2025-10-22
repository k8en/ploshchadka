package org.kdepo.games.ploshchadka.model.custom.game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {

    private int teamId;

    private Color outlineColor;

    private Color clothingColor;

    private Goalkeeper goalkeeper;

    private List<Player> players;

    public Team() {
        players = new ArrayList<>();
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public Color getClothingColor() {
        return clothingColor;
    }

    public void setClothingColor(Color clothingColor) {
        this.clothingColor = clothingColor;
    }

    public Goalkeeper getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(Goalkeeper goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId == team.teamId
                && Objects.equals(outlineColor, team.outlineColor)
                && Objects.equals(clothingColor, team.clothingColor)
                && Objects.equals(goalkeeper, team.goalkeeper)
                && Objects.equals(players, team.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, outlineColor, clothingColor, goalkeeper, players);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", outlineColor=" + outlineColor +
                ", clothingColor=" + clothingColor +
                ", goalkeeper=" + goalkeeper +
                ", players=" + players +
                '}';
    }
}
