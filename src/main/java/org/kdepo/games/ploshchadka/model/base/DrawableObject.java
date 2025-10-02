package org.kdepo.games.ploshchadka.model.base;

import java.awt.*;

public abstract class DrawableObject extends VirtualObject {

    public abstract void draw(Graphics g, VirtualCamera camera);

}
