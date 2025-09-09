package org.kdepo.games.ploshchadka.model.base;

import org.kdepo.games.ploshchadka.model.custom.VirtualObject;

import java.awt.*;

public abstract class DrawableObject extends VirtualObject {

    public abstract void draw(Graphics g, VirtualCamera camera);

}
