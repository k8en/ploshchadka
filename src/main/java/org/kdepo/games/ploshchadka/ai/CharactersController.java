package org.kdepo.games.ploshchadka.ai;

import org.kdepo.games.ploshchadka.model.custom.Controls;
import org.kdepo.games.ploshchadka.model.custom.game.Ball;
import org.kdepo.games.ploshchadka.model.custom.game.GameState;
import org.kdepo.games.ploshchadka.model.custom.game.GoalKeeper;
import org.kdepo.games.ploshchadka.model.custom.game.MatchInfo;
import org.kdepo.games.ploshchadka.model.custom.game.Player;
import org.kdepo.games.ploshchadka.model.custom.game.Team;

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

    public Controls resolvePlayerControls(Controls controls, GameState gameState, MatchInfo matchInfo, Ball ball, Player player, Team teammates, Team opponents) {
        // Reset controls
        controls = resetControls(controls);

        if (GameState.PLAY.equals(gameState)) {
            if (player.isControllingTheBall()) {
                controls.setButtonPowerKick(true);

            } else {

            }
        }

        return controls;
    }

    public Controls resolveGoalKeeperControls(Controls controls, GameState gameState, MatchInfo matchInfo, Ball ball, GoalKeeper goalKeeper, Team teammates, Team opponents) {
        // Reset controls
        controls = resetControls(controls);

        return controls;
    }

    public Controls resetControls(Controls controls) {
        controls.setButtonUp(false);
        controls.setButtonRight(false);
        controls.setButtonDown(false);
        controls.setButtonLeft(false);
        controls.setButtonKick(false);
        controls.setButtonPowerKick(false);
        controls.setButtonPass(false);
        controls.setButtonJump(false);

        return controls;
    }
}
