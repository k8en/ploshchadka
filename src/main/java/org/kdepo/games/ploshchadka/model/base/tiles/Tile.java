package org.kdepo.games.ploshchadka.model.base.tiles;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Tile {

    /**
     * Coordinate of top left corner in virtual space
     */
    private double x;

    /**
     * Coordinate of top left corner in virtual space
     */
    private double y;

    /**
     * Horizontal position on tile map
     */
    private int column;

    /**
     * Vertical position on tile map
     */
    private int row;

    private int tileId;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Double.compare(x, tile.x) == 0
                && Double.compare(y, tile.y) == 0
                && column == tile.column
                && row == tile.row
                && tileId == tile.tileId
                && Objects.equals(image, tile.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, column, row, tileId, image);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "tileId=" + tileId +
                ", x=" + x +
                ", y=" + y +
                ", column=" + column +
                ", row=" + row +
                '}';
    }
}
