package org.kdepo.games.ploshchadka.model.custom;

import java.util.Objects;

public class Controls {

    private boolean isButtonUp;
    private boolean isButtonRight;
    private boolean isButtonDown;
    private boolean isButtonLeft;
    private boolean isButtonKick;
    private boolean isButtonPowerKick;
    private boolean isButtonPass;
    private boolean isButtonJump;

    public Controls() {
        isButtonUp = false;
        isButtonRight = false;
        isButtonDown = false;
        isButtonLeft = false;
        isButtonKick = false;
        isButtonPowerKick = false;
        isButtonPass = false;
        isButtonJump = false;
    }

    public boolean isButtonUp() {
        return isButtonUp;
    }

    public void setButtonUp(boolean buttonUp) {
        isButtonUp = buttonUp;
    }

    public boolean isButtonRight() {
        return isButtonRight;
    }

    public void setButtonRight(boolean buttonRight) {
        isButtonRight = buttonRight;
    }

    public boolean isButtonDown() {
        return isButtonDown;
    }

    public void setButtonDown(boolean buttonDown) {
        isButtonDown = buttonDown;
    }

    public boolean isButtonLeft() {
        return isButtonLeft;
    }

    public void setButtonLeft(boolean buttonLeft) {
        isButtonLeft = buttonLeft;
    }

    public boolean isButtonKick() {
        return isButtonKick;
    }

    public void setButtonKick(boolean buttonKick) {
        isButtonKick = buttonKick;
    }

    public boolean isButtonPowerKick() {
        return isButtonPowerKick;
    }

    public void setButtonPowerKick(boolean buttonPowerKick) {
        isButtonPowerKick = buttonPowerKick;
    }

    public boolean isButtonPass() {
        return isButtonPass;
    }

    public void setButtonPass(boolean buttonPass) {
        isButtonPass = buttonPass;
    }

    public boolean isButtonJump() {
        return isButtonJump;
    }

    public void setButtonJump(boolean buttonJump) {
        isButtonJump = buttonJump;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Controls controls = (Controls) o;
        return isButtonUp == controls.isButtonUp
                && isButtonRight == controls.isButtonRight
                && isButtonDown == controls.isButtonDown
                && isButtonLeft == controls.isButtonLeft
                && isButtonKick == controls.isButtonKick
                && isButtonPowerKick == controls.isButtonPowerKick
                && isButtonPass == controls.isButtonPass
                && isButtonJump == controls.isButtonJump;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isButtonUp, isButtonRight, isButtonDown, isButtonLeft, isButtonKick, isButtonPowerKick, isButtonPass, isButtonJump);
    }

    @Override
    public String toString() {
        return "Controls{" +
                "isButtonUp=" + isButtonUp +
                ", isButtonRight=" + isButtonRight +
                ", isButtonDown=" + isButtonDown +
                ", isButtonLeft=" + isButtonLeft +
                ", isButtonKick=" + isButtonKick +
                ", isButtonPowerKick=" + isButtonPowerKick +
                ", isButtonPass=" + isButtonPass +
                ", isButtonJump=" + isButtonJump +
                '}';
    }
}
