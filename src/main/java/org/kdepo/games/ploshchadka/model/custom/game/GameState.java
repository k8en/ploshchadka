package org.kdepo.games.ploshchadka.model.custom.game;

public enum GameState {
    /**
     * Used to start match or restart play after a goal has been scored
     */
    KICK_OFF,

    /**
     * Used to restart play after ball crossed top or bottom field line
     */
    THROW_IN,

    /**
     * Used to restart play after ball crossed left or right field line
     */
    GOAL_KICK,

    /**
     * Used to restart play after ball has crossed left or right field line
     */
    CORNER_KICK,

    /**
     * Used to make a single shot at the goal
     */
    PENALTY_KICK,

    /**
     * Used to restart play in other cases
     */
    FREE_KICK,

    /**
     * Used to allow player to score a goal
     */
    PLAY
}
