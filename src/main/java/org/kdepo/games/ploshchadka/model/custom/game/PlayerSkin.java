package org.kdepo.games.ploshchadka.model.custom.game;

import java.awt.*;
import java.util.Objects;

public class PlayerSkin {

    private String headType;

    private Color outlineColor;

    private Color skinColor;

    private Color clothingColor;

    public String getHeadType() {
        return headType;
    }

    public void setHeadType(String headType) {
        this.headType = headType;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public Color getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(Color skinColor) {
        this.skinColor = skinColor;
    }

    public Color getClothingColor() {
        return clothingColor;
    }

    public void setClothingColor(Color clothingColor) {
        this.clothingColor = clothingColor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSkin that = (PlayerSkin) o;
        return Objects.equals(headType, that.headType)
                && Objects.equals(outlineColor, that.outlineColor)
                && Objects.equals(skinColor, that.skinColor)
                && Objects.equals(clothingColor, that.clothingColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headType, outlineColor, skinColor, clothingColor);
    }

    @Override
    public String toString() {
        return "PlayerSkin{" +
                "headType='" + headType + '\'' +
                ", outlineColor=" + outlineColor +
                ", skinColor=" + skinColor +
                ", clothingColor=" + clothingColor +
                '}';
    }
}
