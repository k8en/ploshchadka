package org.kdepo.games.ploshchadka.model.custom.game;

import org.kdepo.games.ploshchadka.model.custom.characters.CharacterState;

public class Player extends GameParticipant {

    /**
     * Is controlled by human
     */
    private boolean isHumanControls;

    private double runSpeed;
    private double dashSpeed;

    private double kickReadiness;
    private double kickReadinessRestoreSpeed;

    private int freezeTicks;

    public Player() {
        characterState = CharacterState.STAND;

        kickReadiness = 100d;
        kickReadinessRestoreSpeed = 3d;
        freezeTicks = 0;

        // Is not controlling the ball by default
        isControllingTheBall = false;
    }

    @Override
    protected void initRenderingParameters(String currentAnimationName) {
    }

    public boolean isHumanControls() {
        return isHumanControls;
    }

    public void setHumanControls(boolean humanControls) {
        isHumanControls = humanControls;
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

    public void animate() {
        animationsController.animate();
    }

    public void update() {
        if (kickReadiness < 100d) {
            kickReadiness = kickReadiness + kickReadinessRestoreSpeed;
            if (kickReadiness > 100d) {
                kickReadiness = 100d;
            }
        }

        if (freezeTicks != 0) {
            freezeTicks--;
        }
    }

    public boolean isReadyToKick() {
        return kickReadiness == 100d;
    }

    public void startKick() {
        kickReadiness = 0;
    }
}
