package org.kdepo.games.ploshchadka.ai;

import org.kdepo.games.ploshchadka.model.custom.Ball;
import org.kdepo.games.ploshchadka.model.custom.Controls;
import org.kdepo.games.ploshchadka.model.custom.characters.GoalKeeper;
import org.kdepo.games.ploshchadka.model.custom.characters.Player;

import java.util.List;

public class CharactersController {

    private static CharactersController instance;

    public static CharactersController getInstance() {
        if (instance == null) {
            instance = new CharactersController();
        }
        return instance;
    }

    private CharactersController() {

    }

    public Controls resolvePlayerControls(Controls controls, Ball ball, Player player, List<Player> players, List<GoalKeeper> goalKeepers) {
        // Reset controls
        controls.setButtonUp(false);
        controls.setButtonRight(false);
        controls.setButtonDown(false);
        controls.setButtonLeft(false);
        controls.setButtonKick(false);
        controls.setButtonPowerKick(false);

        return controls;
    }

    public Controls resolveGoalKeeperControls(Controls controls, Ball ball, GoalKeeper goalKeeper, List<Player> players, List<GoalKeeper> goalKeepers) {
        // Reset controls
        controls.setButtonUp(false);
        controls.setButtonRight(false);
        controls.setButtonDown(false);
        controls.setButtonLeft(false);
        controls.setButtonKick(false);
        controls.setButtonPowerKick(false);

        return controls;
    }

}
