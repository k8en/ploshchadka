package org.kdepo.games.ploshchadka;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class ApplicationWindow {

    public ApplicationWindow(ViewPanel viewPanel) {

        Ploshchadka ploshchadka = Ploshchadka.getInstance();

        JFrame jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(viewPanel);

        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(
                new WindowFocusListener() {
                    @Override
                    public void windowLostFocus(WindowEvent e) {
                        ploshchadka.windowLostFocus(e);
                    }

                    @Override
                    public void windowGainedFocus(WindowEvent e) {
                        ploshchadka.windowGainedFocus(e);
                    }
                });
    }

}
