package org.kdepo.games.ploshchadka.model.custom.game;

import org.kdepo.games.ploshchadka.model.base.geometry.Line2D;
import org.kdepo.games.ploshchadka.model.base.geometry.Point2D;
import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;

import java.util.Objects;

public class GameFieldMarkup {

    private Point2D kickOffPosition;

    private Point2D leftSidePenaltyPosition;

    private Point2D rightSidePenaltyPosition;

    private VirtualRectangle fieldBounds;

    private VirtualRectangle leftSidePenaltyArea;

    private VirtualRectangle rightSidePenaltyArea;

    private VirtualRectangle leftSideGoalArea;

    private VirtualRectangle rightSideGoalArea;

    private Line2D leftSideGoalLine;

    private Line2D rightSideGoalLine;

    public Point2D getKickOffPosition() {
        return kickOffPosition;
    }

    public void setKickOffPosition(Point2D kickOffPosition) {
        this.kickOffPosition = kickOffPosition;
    }

    public Point2D getLeftSidePenaltyPosition() {
        return leftSidePenaltyPosition;
    }

    public void setLeftSidePenaltyPosition(Point2D leftSidePenaltyPosition) {
        this.leftSidePenaltyPosition = leftSidePenaltyPosition;
    }

    public Point2D getRightSidePenaltyPosition() {
        return rightSidePenaltyPosition;
    }

    public void setRightSidePenaltyPosition(Point2D rightSidePenaltyPosition) {
        this.rightSidePenaltyPosition = rightSidePenaltyPosition;
    }

    public VirtualRectangle getFieldBounds() {
        return fieldBounds;
    }

    public void setFieldBounds(VirtualRectangle fieldBounds) {
        this.fieldBounds = fieldBounds;
    }

    public VirtualRectangle getLeftSidePenaltyArea() {
        return leftSidePenaltyArea;
    }

    public void setLeftSidePenaltyArea(VirtualRectangle leftSidePenaltyArea) {
        this.leftSidePenaltyArea = leftSidePenaltyArea;
    }

    public VirtualRectangle getRightSidePenaltyArea() {
        return rightSidePenaltyArea;
    }

    public void setRightSidePenaltyArea(VirtualRectangle rightSidePenaltyArea) {
        this.rightSidePenaltyArea = rightSidePenaltyArea;
    }

    public VirtualRectangle getLeftSideGoalArea() {
        return leftSideGoalArea;
    }

    public void setLeftSideGoalArea(VirtualRectangle leftSideGoalArea) {
        this.leftSideGoalArea = leftSideGoalArea;
    }

    public VirtualRectangle getRightSideGoalArea() {
        return rightSideGoalArea;
    }

    public void setRightSideGoalArea(VirtualRectangle rightSideGoalArea) {
        this.rightSideGoalArea = rightSideGoalArea;
    }

    public Line2D getLeftSideGoalLine() {
        return leftSideGoalLine;
    }

    public void setLeftSideGoalLine(Line2D leftSideGoalLine) {
        this.leftSideGoalLine = leftSideGoalLine;
    }

    public Line2D getRightSideGoalLine() {
        return rightSideGoalLine;
    }

    public void setRightSideGoalLine(Line2D rightSideGoalLine) {
        this.rightSideGoalLine = rightSideGoalLine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameFieldMarkup that = (GameFieldMarkup) o;
        return Objects.equals(kickOffPosition, that.kickOffPosition)
                && Objects.equals(leftSidePenaltyPosition, that.leftSidePenaltyPosition)
                && Objects.equals(rightSidePenaltyPosition, that.rightSidePenaltyPosition)
                && Objects.equals(fieldBounds, that.fieldBounds)
                && Objects.equals(leftSidePenaltyArea, that.leftSidePenaltyArea)
                && Objects.equals(rightSidePenaltyArea, that.rightSidePenaltyArea)
                && Objects.equals(leftSideGoalArea, that.leftSideGoalArea)
                && Objects.equals(rightSideGoalArea, that.rightSideGoalArea)
                && Objects.equals(leftSideGoalLine, that.leftSideGoalLine)
                && Objects.equals(rightSideGoalLine, that.rightSideGoalLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kickOffPosition, leftSidePenaltyPosition, rightSidePenaltyPosition, fieldBounds, leftSidePenaltyArea, rightSidePenaltyArea, leftSideGoalArea, rightSideGoalArea, leftSideGoalLine, rightSideGoalLine);
    }

    @Override
    public String toString() {
        return "GameFieldMarkup{" +
                "kickOffPosition=" + kickOffPosition +
                ", leftSidePenaltyPosition=" + leftSidePenaltyPosition +
                ", rightSidePenaltyPosition=" + rightSidePenaltyPosition +
                ", fieldBounds=" + fieldBounds +
                ", leftSidePenaltyArea=" + leftSidePenaltyArea +
                ", rightSidePenaltyArea=" + rightSidePenaltyArea +
                ", leftSideGoalArea=" + leftSideGoalArea +
                ", rightSideGoalArea=" + rightSideGoalArea +
                ", leftSideGoalLine=" + leftSideGoalLine +
                ", rightSideGoalLine=" + rightSideGoalLine +
                '}';
    }
}
