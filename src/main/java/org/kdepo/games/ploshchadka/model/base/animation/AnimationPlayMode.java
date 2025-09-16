package org.kdepo.games.ploshchadka.model.base.animation;

public enum AnimationPlayMode {

    /**
     * Frames are controlled with custom logic
     */
    MANUAL,

    /**
     * Animation play will stop after all frames are displayed
     */
    SINGLE,

    /**
     * Animation will start from the beginning after all frames are displayed
     */
    LOOP
}
