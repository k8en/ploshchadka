package org.kdepo.games.ploshchadka.model.custom.game;

import java.util.Objects;

public class MatchInfo {

    /**
     * ID of team on the left side of the football field
     */
    private int teamOnTheLeftSide;

    /**
     * ID of team on the right side of the football field
     */
    private int teamOnTheRightSide;

    private int leftTeamScore;

    private int rightTeamScore;

    public int getTeamOnTheLeftSide() {
        return teamOnTheLeftSide;
    }

    public void setTeamOnTheLeftSide(int teamOnTheLeftSide) {
        this.teamOnTheLeftSide = teamOnTheLeftSide;
    }

    public int getTeamOnTheRightSide() {
        return teamOnTheRightSide;
    }

    public void setTeamOnTheRightSide(int teamOnTheRightSide) {
        this.teamOnTheRightSide = teamOnTheRightSide;
    }

    public int getLeftTeamScore() {
        return leftTeamScore;
    }

    public void setLeftTeamScore(int leftTeamScore) {
        this.leftTeamScore = leftTeamScore;
    }

    public int getRightTeamScore() {
        return rightTeamScore;
    }

    public void setRightTeamScore(int rightTeamScore) {
        this.rightTeamScore = rightTeamScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MatchInfo matchInfo = (MatchInfo) o;
        return teamOnTheLeftSide == matchInfo.teamOnTheLeftSide
                && teamOnTheRightSide == matchInfo.teamOnTheRightSide
                && leftTeamScore == matchInfo.leftTeamScore
                && rightTeamScore == matchInfo.rightTeamScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamOnTheLeftSide, teamOnTheRightSide, leftTeamScore, rightTeamScore);
    }

    @Override
    public String toString() {
        return "MatchInfo{" +
                "teamOnTheLeftSide=" + teamOnTheLeftSide +
                ", teamOnTheRightSide=" + teamOnTheRightSide +
                ", leftTeamScore=" + leftTeamScore +
                ", rightTeamScore=" + rightTeamScore +
                '}';
    }
}
