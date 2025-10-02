package org.kdepo.games.ploshchadka.model.custom;

import java.util.Objects;

public class Controls {

    private boolean isButtonUp;
    private boolean isButtonRight;
    private boolean isButtonDown;
    private boolean isButtonLeft;
    private boolean isButtonKick;
    private boolean isButtonPowerKick;

    public Controls() {
        isButtonUp = false;
        isButtonRight = false;
        isButtonDown = false;
        isButtonLeft = false;
        isButtonKick = false;
        isButtonPowerKick = false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Controls controls = (Controls) o;
        return isButtonUp == controls.isButtonUp
                && isButtonRight == controls.isButtonRight
                && isButtonDown == controls.isButtonDown
                && isButtonLeft == controls.isButtonLeft
                && isButtonKick == controls.isButtonKick
                && isButtonPowerKick == controls.isButtonPowerKick;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isButtonUp, isButtonRight, isButtonDown, isButtonLeft, isButtonKick, isButtonPowerKick);
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
                '}';
    }
}
