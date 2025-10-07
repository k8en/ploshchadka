package org.kdepo.games.ploshchadka.model.custom.characters;

/**
 * Game participants are:
 * - players
 * - goalkeepers
 * - referee
 */
public abstract class GameParticipant extends AbstractCharacter {

    protected int teamId;

    protected boolean isControllingTheBall;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public boolean isControllingTheBall() {
        return isControllingTheBall;
    }

    public void setControllingTheBall(boolean isControllingTheBall) {
        this.isControllingTheBall = isControllingTheBall;
    }

}
