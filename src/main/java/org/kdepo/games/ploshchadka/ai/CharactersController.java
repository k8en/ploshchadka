package org.kdepo.games.ploshchadka.ai;

import org.kdepo.games.ploshchadka.model.base.geometry.Line2D;
import org.kdepo.games.ploshchadka.model.base.geometry.VirtualRectangle;
import org.kdepo.games.ploshchadka.model.custom.Controls;
import org.kdepo.games.ploshchadka.model.custom.game.Ball;
import org.kdepo.games.ploshchadka.model.custom.game.GameFieldMarkup;
import org.kdepo.games.ploshchadka.model.custom.game.GameParticipant;
import org.kdepo.games.ploshchadka.model.custom.game.GameState;
import org.kdepo.games.ploshchadka.model.custom.game.Goalkeeper;
import org.kdepo.games.ploshchadka.model.custom.game.MatchInfo;
import org.kdepo.games.ploshchadka.model.custom.game.Player;
import org.kdepo.games.ploshchadka.model.custom.game.Team;
import org.kdepo.games.ploshchadka.utils.MathUtils;

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

    public Controls resolvePlayerControls(Controls controls, GameState gameState, GameFieldMarkup gameFieldMarkup, MatchInfo matchInfo, Ball ball, Player player, Team teammates, Team opponents) {
        // Reset controls
        controls = resetControls(controls);

        // Resolve team side and goal lines
        boolean isLeftSideTeam = (player.getTeamId() == matchInfo.getTeamOnTheLeftSide());
        Line2D ownGoalLine = isLeftSideTeam ? gameFieldMarkup.getLeftSideGoalLine() : gameFieldMarkup.getRightSideGoalLine();
        Line2D opponentGoalLine = isLeftSideTeam ? gameFieldMarkup.getRightSideGoalLine() : gameFieldMarkup.getLeftSideGoalLine();

        // Resolve controls based on game state
        if (GameState.PLAY.equals(gameState)) {
            if (player.isControllingTheBall()) {
                handleAttack(controls, player, opponentGoalLine, opponents, teammates);

            } else if (isTeamControllingTheBall(teammates)) {
                // Offence

            } else {
                // Defence

            }
        }

        return controls;
    }

    public Controls resolveGoalKeeperControls(Controls controls, GameState gameState, GameFieldMarkup gameFieldMarkup, MatchInfo matchInfo, Ball ball, Goalkeeper goalKeeper, Team teammates, Team opponents) {
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

    /**
     * Resolve controls for attack strategy
     *
     * @param controls         controls to resolve
     * @param player           player to be controlled
     * @param opponentGoalLine opponents goal line position
     * @param opponents        list of opponents
     * @param teammates        list of teammates
     */
    public void handleAttack(Controls controls, Player player, Line2D opponentGoalLine, Team opponents, Team teammates) {
        double playerX = player.getCenterX();
        double playerY = player.getCenterY();

        // Coordinates of opponents goal line center
        double goalX = (opponentGoalLine.getA().getX() + opponentGoalLine.getB().getX()) / 2;
        double goalY = (opponentGoalLine.getA().getY() + opponentGoalLine.getB().getY()) / 2;

        // Find nearest opponent
        GameParticipant nearestOpponent = findNearestPlayerToPoint(playerX, playerY, opponents);
        double distanceToOpponent = MathUtils.getDistance2D(playerX, playerY, nearestOpponent.getCenterX(), nearestOpponent.getCenterY());

        if (distanceToOpponent < 40) {
            // Соперник близко - уклоняемся или делаем пас
            Player freeTeammate = findFreeTeammate(player, teammates, opponents);
            if (freeTeammate != null && MathUtils.getDistance2D(playerX, playerY, freeTeammate.getCenterX(), freeTeammate.getCenterY()) < 200) {
                // Делаем пас свободному teammate
                moveTowardsPoint(controls, playerX, playerY, freeTeammate.getCenterX(), freeTeammate.getCenterY());
                controls.setButtonPass(true);
            } else {
                // Уклоняемся от соперника
                avoidPlayer(controls, player, nearestOpponent);
            }
        } else if (MathUtils.getDistance2D(playerX, playerY, goalX, goalY) < 150) {
            // Близко к воротам - бьем по воротам
            moveTowardsPoint(controls, playerX, playerY, goalX, goalY);
            if (player.isReadyToKick()) {
                controls.setButtonPowerKick(true);
            }
        } else {
            // Бежим к воротам противника
            moveTowardsPoint(controls, playerX, playerY, goalX, goalY);
        }
    }

    /**
     * Checks if ball is in penalty area of own team
     *
     * @param ball
     * @param field
     * @param isLeftSideTeam
     * @return
     */
    public boolean isBallInDangerZone(Ball ball, GameFieldMarkup field, boolean isLeftSideTeam) {
        double ballX = ball.getSphere().getX();
        VirtualRectangle dangerZone;

        if (isLeftSideTeam) {
            dangerZone = field.getLeftSidePenaltyArea();
        } else {
            dangerZone = field.getRightSidePenaltyArea();
        }

        return ballX >= dangerZone.getX() &&
                ballX <= dangerZone.getX() + dangerZone.getWidth() &&
                ball.getSphere().getY() >= dangerZone.getY() &&
                ball.getSphere().getY() <= dangerZone.getY() + dangerZone.getHeight();
    }

    /**
     * Finds nearest player of a team to selected point
     *
     * @param x    coordinate of selected point
     * @param y    coordinate of selected point
     * @param team player's team
     * @return nearest player
     */
    public GameParticipant findNearestPlayerToPoint(double x, double y, Team team) {
        GameParticipant nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Player teammate : team.getPlayers()) {
            double distance = MathUtils.getDistance2D(x, y, teammate.getCenterX(), teammate.getCenterY());
            if (distance < minDistance) {
                minDistance = distance;
                nearest = teammate;
            }
        }

        // Check goalkeeper
        if (team.getGoalkeeper() != null) {
            double distance = MathUtils.getDistance2D(x, y, team.getGoalkeeper().getCenterX(), team.getGoalkeeper().getCenterY());
            if (distance < minDistance) {
                nearest = team.getGoalkeeper();
            }
        }

        return nearest;
    }

    public Player findFreeTeammate(Player currentPlayer, Team teammates, Team opponents) {
        Player freestTeammate = null;
        double maxSpace = 0;

        for (Player teammate : teammates.getPlayers()) {
            if (teammate.getId() == currentPlayer.getId()) {
                continue;
            }

            // Находим ближайшего соперника к этому teammate
            GameParticipant nearestOpponent = findNearestPlayerToPoint(teammate.getCenterX(), teammate.getCenterY(), opponents);
            double space = MathUtils.getDistance2D(teammate.getCenterX(), teammate.getCenterY(), nearestOpponent.getCenterX(), nearestOpponent.getCenterY());

            if (space > maxSpace) {
                maxSpace = space;
                freestTeammate = teammate;
            }
        }

        return freestTeammate;
    }

    public void moveTowardsPoint(Controls controls, double fromX, double fromY, double toX, double toY) {
        double deltaX = toX - fromX;
        double deltaY = toY - fromY;

        // Устанавливаем направление движения
        if (Math.abs(deltaX) > 10) {
            if (deltaX > 0) {
                controls.setButtonRight(true);
            } else {
                controls.setButtonLeft(true);
            }
        }

        if (Math.abs(deltaY) > 10) {
            if (deltaY > 0) {
                controls.setButtonDown(true);
            } else {
                controls.setButtonUp(true);
            }
        }

        // TODO check if face direction is updated
    }

    public void avoidPlayer(Controls controls, Player player, GameParticipant opponent) {
        double playerX = player.getCenterX();
        double playerY = player.getCenterY();
        double opponentX = opponent.getCenterX();
        double opponentY = opponent.getCenterY();

        double avoidX = playerX + (playerX - opponentX);
        double avoidY = playerY + (playerY - opponentY);

        moveTowardsPoint(controls, playerX, playerY, avoidX, avoidY);
    }

    public boolean isTeamControllingTheBall(Team team) {
        for (Player player : team.getPlayers()) {
            if (player.isControllingTheBall()) {
                return true;
            }
        }
        return false;
    }
}
