package org.kdepo.games.ploshchadka.ai;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationPlayMode;
import org.kdepo.games.ploshchadka.model.base.geometry.Point3D;
import org.kdepo.games.ploshchadka.model.base.utils.Console;
import org.kdepo.games.ploshchadka.model.custom.Controls;
import org.kdepo.games.ploshchadka.model.custom.Impulse;
import org.kdepo.games.ploshchadka.model.custom.characters.CharacterState;
import org.kdepo.games.ploshchadka.model.custom.characters.FaceDirection;
import org.kdepo.games.ploshchadka.model.custom.game.Ball;
import org.kdepo.games.ploshchadka.model.custom.game.GameState;
import org.kdepo.games.ploshchadka.model.custom.game.GoalKeeper;
import org.kdepo.games.ploshchadka.model.custom.game.MatchInfo;
import org.kdepo.games.ploshchadka.model.custom.game.Player;
import org.kdepo.games.ploshchadka.model.custom.game.Team;
import org.kdepo.games.ploshchadka.utils.BallisticsUtils;
import org.kdepo.games.ploshchadka.utils.MathUtils;

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

    public void processPlayerState(Player player, Controls controls, Ball ball, Team teammates) {
        if (CharacterState.STAND.equals(player.getCharacterState())) {
            if (controls.isButtonKick()) {
                processPlayerStandWithKickIntent(player, ball);

            } else if (controls.isButtonPowerKick()) {
                processPlayerStandWithPowerKickIntent(player, ball);

            } else if (controls.isButtonPass()) {
                processPlayerStandWithPassIntent(player, ball, teammates);

            } else if (controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerStandWithMoveUpIntent(player, ball);

            } else if (controls.isButtonUp() && controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerStandWithMoveUpRightIntent(player, ball);

            } else if (!controls.isButtonUp() && controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerStandWithMoveRightIntent(player, ball);

            } else if (!controls.isButtonUp() && controls.isButtonRight() && controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerStandWithMoveDownRightIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerStandWithMoveDownIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && controls.isButtonDown() && controls.isButtonLeft()) {
                processPlayerStandWithMoveDownLeftIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && controls.isButtonLeft()) {
                processPlayerStandWithMoveLeftIntent(player, ball);

            } else if (controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && controls.isButtonLeft()) {
                processPlayerStandWithMoveUpLeftIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerStandWithoutControls(player, ball);
            }

        } else if (CharacterState.RUN.equals(player.getCharacterState())) {
            if (controls.isButtonKick()) {
                processPlayerRunWithKickIntent(player, ball);

            } else if (controls.isButtonPowerKick()) {
                processPlayerRunWithPowerKickIntent(player, ball);

            } else if (controls.isButtonPass()) {
                processPlayerRunWithPassIntent(player, ball, teammates);

            } else if (controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerRunWithMoveUpIntent(player, ball);

            } else if (controls.isButtonUp() && controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerRunWithMoveUpRightIntent(player, ball);

            } else if (!controls.isButtonUp() && controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerRunWithMoveRightIntent(player, ball);

            } else if (!controls.isButtonUp() && controls.isButtonRight() && controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerRunWithMoveDownRightIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerRunWithMoveDownIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && controls.isButtonDown() && controls.isButtonLeft()) {
                processPlayerRunWithMoveDownLeftIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && controls.isButtonLeft()) {
                processPlayerRunWithMoveLeftIntent(player, ball);

            } else if (controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && controls.isButtonLeft()) {
                processPlayerRunWithMoveUpLeftIntent(player, ball);

            } else if (!controls.isButtonUp() && !controls.isButtonRight() && !controls.isButtonDown() && !controls.isButtonLeft()) {
                processPlayerRunWithoutControls(player, ball);

            }

        } else if (CharacterState.KICK.equals(player.getCharacterState())) {
            // Check if animation is completed - switch to the next animation
            if (player.getAnimationsController().isPlayCompleted()) {
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);
                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setCharacterState(CharacterState.STAND);
            }

        } else if (CharacterState.POWER_KICK.equals(player.getCharacterState())) {
            // Check if animation is completed - switch to the next animation
            if (player.getAnimationsController().isPlayCompleted()) {
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);
                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setCharacterState(CharacterState.STAND);

                System.out.println("Completed");

            } else {
                // Animation is not completed
                int frameNumber = player.getAnimationsController().getCurrentFrameNumber();
                if (frameNumber == 2) {
                    // Hit the ball with the last frame
                    System.out.println("Power kick on ball");
//                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
//                        ball.getMovementVector().setX(0.7);
//                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
//                        ball.getMovementVector().setX(-0.7);
//                    } else {
//                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
//                    }

                    Point3D startPoint = new Point3D(ball.getSphere().getX(), ball.getSphere().getY(), ball.getSphere().getZ());
                    Point3D targetPoint = new Point3D(965, 0, 90);
                    Impulse impulse = BallisticsUtils.calculateImpulse(startPoint, targetPoint, 0.024d);

                    ball.getMovementVector().setX(impulse.getDirection().getX());
                    ball.getMovementVector().setY(impulse.getDirection().getY());
                    ball.getMovementVector().setZ(impulse.getDirection().getZ());

                    double ballSpeed = impulse.getSpeed() * 2;
                    if (ballSpeed < 8) {
                        ballSpeed = 8;
                    }
                    ball.setMovementSpeed(ballSpeed);

                    // Ball is not controlled anymore
                    ball.setControlledBy(null);
                    player.setControllingTheBall(false);

                    player.startKick();
                }

                System.out.println("Not completed");
            }

        }

        // Update frame ticks
        player.animate();

        // Update internal parameters
        player.update();

        Console.addMessage("Player center(" + player.getCenterX() + "," + player.getCenterY() + "," + player.getCenterZ() + ") state=" + player.getCharacterState());
    }

    public void processPlayerStandWithKickIntent(Player player, Ball ball) {
        if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
            // Player is controlling the ball
            if (player.isReadyToKick()) {
                // Player is ready to kick the ball
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_RIGHT);

                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_LEFT);

                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setCharacterState(CharacterState.KICK);
                player.startKick();

                // Hit the ball
                if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {

                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {

                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }
                    ball.getMovementVector().setX(0);
                    ball.getMovementVector().setY(0);
                    ball.getMovementVector().setZ(1);
                    ball.setMovementSpeed(2);
                }
            }

        } else {
            // Player is not controlling the ball

        }
    }

    public void processPlayerRunWithKickIntent(Player player, Ball ball) {
        if (player.isReadyToKick()) {
            if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_RIGHT);

            } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_LEFT);

            } else {
                throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
            }

            player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            player.setCharacterState(CharacterState.KICK);
            player.startKick();

            // Hit the ball
            if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    ball.getMovementVector().setX(0.7);

                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    ball.getMovementVector().setX(-0.7);

                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }
            }
        }
    }

    public void processPlayerStandWithPowerKickIntent(Player player, Ball ball) {
        if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
            // Player is controlling the ball
            if (player.isReadyToKick()) {
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_RIGHT);

                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_LEFT);

                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setCharacterState(CharacterState.POWER_KICK);
            }

        } else {
            // Player is not controlling the ball

        }
    }

    public void processPlayerRunWithPowerKickIntent(Player player, Ball ball) {
        if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
            // Player is controlling the ball
            if (player.isReadyToKick()) {
                if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_RIGHT);

                } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                    player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_LEFT);

                } else {
                    throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                }

                player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                player.getAnimationsController().setPlayCompleted(false);
                player.getAnimationsController().resetCurrentFrameNumber();
                player.getAnimationsController().setTicksPassed(0);

                player.setCharacterState(CharacterState.POWER_KICK);
            }

        } else {
            // Player is not controlling the ball

        }
    }

    public void processPlayerStandWithPassIntent(Player player, Ball ball, Team teammates) {
    }

    public void processPlayerRunWithPassIntent(Player player, Ball ball, Team teammates) {
    }

    public void processPlayerStandWithMoveUpIntent(Player player, Ball ball) {
        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

        } else {
            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
        }

        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        player.setCharacterState(CharacterState.RUN);

        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterY(nextCenterY);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveUpIntent(Player player, Ball ball) {
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterY(nextCenterY);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveUpRightIntent(Player player, Ball ball) {
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);
        player.setFaceDirection(FaceDirection.RIGHT);
        player.setCharacterState(CharacterState.RUN);

        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveUpRightIntent(Player player, Ball ball) {
        if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.setFaceDirection(FaceDirection.RIGHT);
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);
        }

        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveRightIntent(Player player, Ball ball) {
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);
        player.setFaceDirection(FaceDirection.RIGHT);
        player.setCharacterState(CharacterState.RUN);

        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        player.setCenterX(nextCenterX);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveRightIntent(Player player, Ball ball) {
        if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.setFaceDirection(FaceDirection.RIGHT);
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);
        }

        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        player.setCenterX(nextCenterX);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveDownRightIntent(Player player, Ball ball) {
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);
        player.setFaceDirection(FaceDirection.RIGHT);
        player.setCharacterState(CharacterState.RUN);

        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveDownRightIntent(Player player, Ball ball) {
        if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.setFaceDirection(FaceDirection.RIGHT);
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);
        }

        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveDownIntent(Player player, Ball ball) {
        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

        } else {
            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
        }

        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        player.setCharacterState(CharacterState.RUN);

        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterY(nextCenterY);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveDownIntent(Player player, Ball ball) {
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterY(nextCenterY);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveDownLeftIntent(Player player, Ball ball) {
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);
        player.setFaceDirection(FaceDirection.LEFT);
        player.setCharacterState(CharacterState.RUN);

        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveDownLeftIntent(Player player, Ball ball) {
        if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.setFaceDirection(FaceDirection.LEFT);
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);
        }

        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveLeftIntent(Player player, Ball ball) {
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);
        player.setFaceDirection(FaceDirection.LEFT);
        player.setCharacterState(CharacterState.RUN);

        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        player.setCenterX(nextCenterX);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveLeftIntent(Player player, Ball ball) {
        if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.setFaceDirection(FaceDirection.LEFT);
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);
        }

        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        player.setCenterX(nextCenterX);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveUpLeftIntent(Player player, Ball ball) {
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);
        player.setFaceDirection(FaceDirection.LEFT);
        player.setCharacterState(CharacterState.RUN);

        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithMoveUpLeftIntent(Player player, Ball ball) {
        if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.setFaceDirection(FaceDirection.LEFT);
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);
        }

        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithoutControls(Player player, Ball ball) {
        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerRunWithoutControls(Player player, Ball ball) {
        if (Constants.AnimationName.RUN_LEFT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);

        } else if (Constants.AnimationName.RUN_RIGHT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
        }

        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        player.setCharacterState(CharacterState.STAND);

        adjustPlayerHeightForRun(player);

        checkForBallCaptureByPlayer(player, ball);
    }

    public void adjustPlayerHeightForRun(Player player) {
        if (Constants.AnimationName.RUN_LEFT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())
                || Constants.AnimationName.RUN_RIGHT.equals(player.getAnimationsController().getCurrentAnimation().getAnimationName())) {
            int frameNumber = player.getAnimationsController().getCurrentFrameNumber();
            if (frameNumber == 0) {
                player.setCenterZ(2);
            } else {
                player.setCenterZ(0);
            }
        } else {
            player.setCenterZ(0);
        }
    }

    public void checkForBallCaptureByPlayer(Player player, Ball ball) {
        // Skip in case if controlled already
        if (ball.getControlledBy() != null) {
            return;
        }

        // Get distance between player and ball
        double distanceToBall = MathUtils.getDistance2D(player.getCenterX(), player.getCenterY(), ball.getCenterX(), ball.getCenterY());

        // If distance between player and ball is enough to acquire control
        if (distanceToBall <= player.getCharacterRadius() + ball.getSphere().getRadius()) {
            ball.setControlledBy(player);
            player.setControllingTheBall(true);
        }
    }

//    public void checkBallControl(Player player, Ball ball) {
//        // Get distance between player and ball
//        double distanceToBall = MathUtils.getDistance2D(player.getCenterX(), player.getCenterY(), ball.getCenterX(), ball.getCenterY());
//
//        if (ball.getControlledBy() == null) {
//            // If ball is not controlled by any player
//            if (distanceToBall <= player.getCharacterRadius() + ball.getSphere().getRadius()) {
//                // If distance between player and ball is enough to acquire control
//                ball.setControlledBy(player);
//            }
//        } else if (player.getId() == ball.getControlledBy().getId()) {
//            // If ball is controlled by player
//            if (distanceToBall > player.getCharacterRadius() + ball.getSphere().getRadius()) {
//                // If ball is too far from player - need to adjust distance
//                Vector2D vector2D = MathUtils.getVector2D(ball.getCenterX(), ball.getCenterY(), player.getCenterX(), player.getCenterY());
//                Vector2D vector2DNormalized = MathUtils.getVector2DNormalized(vector2D.getX(), vector2D.getY());
//                ball.getMovementVector().setX(vector2DNormalized.getX());
//                ball.getMovementVector().setY(vector2DNormalized.getY());
//                ball.getMovementVector().setZ(0);
//                ball.setMovementSpeed(3d);
//            }
//        }
//    }

}
