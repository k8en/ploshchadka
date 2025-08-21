package org.kdepo.games.ploshchadka;

import org.kdepo.games.ploshchadka.inputs.KeyboardInputs;
import org.kdepo.games.ploshchadka.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

    private final Application application;

    public ViewPanel(Application application) {
        this.application = application;
        MouseInputs mouseInputs = new MouseInputs();

        Ploshchadka ploshchadka = Ploshchadka.getInstance();
        Dimension size = new Dimension(ploshchadka.getScreenWidth(), ploshchadka.getScreenHeight());
        setPreferredSize(size);

        addKeyListener(new KeyboardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        application.render(g);
    }

}
