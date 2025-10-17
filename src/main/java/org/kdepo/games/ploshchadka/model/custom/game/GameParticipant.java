package org.kdepo.games.ploshchadka.model.custom.game;

import org.kdepo.games.ploshchadka.model.custom.characters.AbstractCharacter;

/**
 * Game participants are:
 * - players
 * - goalkeepers
 * - referee
 */
public abstract class GameParticipant extends AbstractCharacter {

    /**
     * Defines game participant role
     */
    protected GameRole gameRole;

    /**
     * Defines game participant team. In case of referee the value is 0
     */
    protected int teamId;

    /**
     * Defines if game participant controlling the ball. In case of referee the value is false
     */
    protected boolean isControllingTheBall;

    public GameRole getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRole gameRole) {
        this.gameRole = gameRole;
    }

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
