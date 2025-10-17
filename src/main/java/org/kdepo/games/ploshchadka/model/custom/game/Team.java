package org.kdepo.games.ploshchadka.model.custom.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Team {

    private int teamId;

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
                && Objects.equals(goalkeeper, team.goalkeeper)
                && Objects.equals(players, team.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, goalkeeper, players);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", goalkeeper=" + goalkeeper +
                ", players=" + players +
                '}';
    }
}
