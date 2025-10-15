package org.kdepo.games.ploshchadka.fsm;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationPlayMode;
import org.kdepo.games.ploshchadka.model.base.geometry.Point3D;
import org.kdepo.games.ploshchadka.model.custom.Controls;
import org.kdepo.games.ploshchadka.model.custom.Impulse;
import org.kdepo.games.ploshchadka.model.custom.characters.CharacterState;
import org.kdepo.games.ploshchadka.model.custom.characters.FaceDirection;
import org.kdepo.games.ploshchadka.model.custom.game.Ball;
import org.kdepo.games.ploshchadka.model.custom.game.Player;
import org.kdepo.games.ploshchadka.model.custom.game.Team;
import org.kdepo.games.ploshchadka.utils.BallisticsUtils;
import org.kdepo.games.ploshchadka.utils.MathUtils;

public class PlayerStateMachine {

    private static PlayerStateMachine instance;

    public static PlayerStateMachine getInstance() {
        if (instance == null) {
            instance = new PlayerStateMachine();
        }
        return instance;
    }

    private PlayerStateMachine() {

    }

    public void process(Player player, Controls controls, Ball ball, Team teammates) {
        switch (player.getCharacterState()) {
            case STAND: {
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

                break;
            }
            case RUN: {
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

                break;
            }
            case KICK: {
                processPlayerKick(player);

                break;
            }
            case POWER_KICK: {
                processPlayerPowerKick(player, ball);

                break;
            }
        }
    }

    //region STAND
    public void processPlayerStandWithoutControls(Player player, Ball ball) {
        // Update animation frame ticks
        player.animate();

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);
    }

    public void processPlayerStandWithMoveUpIntent(Player player, Ball ball) {
        // Change to new animation according to face direction
        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
        } else {
            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
        }

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithMoveUpRightIntent(Player player, Ball ball) {
        // Update face direction according to planned move direction
        player.setFaceDirection(FaceDirection.RIGHT);

        // Change to new animation
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithMoveRightIntent(Player player, Ball ball) {
        // Update face direction according to planned move direction
        player.setFaceDirection(FaceDirection.RIGHT);

        // Change to new animation
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithMoveDownRightIntent(Player player, Ball ball) {
        // Update face direction according to planned move direction
        player.setFaceDirection(FaceDirection.RIGHT);

        // Change to new animation
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithMoveDownIntent(Player player, Ball ball) {
        // Change to new animation according to face direction
        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);
        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);
        } else {
            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
        }

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithMoveDownLeftIntent(Player player, Ball ball) {
        // Update face direction according to planned move direction
        player.setFaceDirection(FaceDirection.LEFT);

        // Change to new animation
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithMoveLeftIntent(Player player, Ball ball) {
        // Update face direction according to planned move direction
        player.setFaceDirection(FaceDirection.LEFT);

        // Change to new animation
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithMoveUpLeftIntent(Player player, Ball ball) {
        // Update face direction according to planned move direction
        player.setFaceDirection(FaceDirection.LEFT);

        // Change to new animation
        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.RUN);
    }

    public void processPlayerStandWithKickIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;
        if (player.isControllingTheBall()) {
            if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                if (player.isReadyToKick()) {
                    // Change to new animation according to face direction
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_RIGHT);
                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_LEFT);
                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }

                    // Setup animation play parameters
                    player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                    player.getAnimationsController().setPlayCompleted(false);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);

                    isAnimationChanged = true;

                    // Reset kick readiness
                    player.startKick();

                    // Hit the ball according to face direction
                    //TODO add ball kick
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {

                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {

                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }
                    ball.getMovementVector().setX(0);
                    ball.getMovementVector().setY(0);
                    ball.getMovementVector().setZ(1);
                    ball.setMovementSpeed(2);

                    // Switch to new state
                    player.setCharacterState(CharacterState.KICK);
                }
            } else {
                throw new RuntimeException("Error! Ball control mismatch!");
            }
        } else {
            //TODO action without ball
        }

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerStandWithPowerKickIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;
        if (player.isControllingTheBall()) {
            if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                if (player.isReadyToKick()) {
                    // Change to new animation according to face direction
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_RIGHT);
                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.POWER_KICK_LEFT);
                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }

                    // Setup animation play parameters
                    player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                    player.getAnimationsController().setPlayCompleted(false);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);

                    isAnimationChanged = true;

                    // Reset kick readiness
                    player.startKick();

                    // Ball will be hit with the last frame of power kick animation

                    // Switch to new state
                    player.setCharacterState(CharacterState.POWER_KICK);
                }
            } else {
                throw new RuntimeException("Error! Ball control mismatch!");
            }
        } else {
            //TODO action without ball
        }

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerStandWithPassIntent(Player player, Ball ball, Team teammates) {
        boolean isAnimationChanged = false;
        if (player.isControllingTheBall()) {
            if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                if (player.isReadyToKick()) {
                    // Find the nearest teammate
                    double nearestDistance = Double.MAX_VALUE;
                    Player nearestTeammate = null;
                    for (Player teammate : teammates.getPlayers()) {
                        if (player.getId() == teammate.getId()) {
                            // Skip itself
                            continue;
                        }
                        double currentDistance = MathUtils.getDistance2D(player.getCenterX(), player.getCenterY(), teammate.getCenterX(), teammate.getCenterY());
                        if (currentDistance < nearestDistance) {
                            nearestDistance = currentDistance;
                            nearestTeammate = teammate;
                        }
                    }

                    // Initiate pass
                    if (nearestTeammate != null) {


                    }
                }
            }

        } else {
            // Action without ball
            boolean isTeammateControllingTheBall = false;
            for (Player teammate : teammates.getPlayers()) {
                if (teammate.isControllingTheBall()) {
                    isTeammateControllingTheBall = true;
                    break;
                }
            }

            // Teammate is controlling the ball - to ask a pass
            if (isTeammateControllingTheBall) {

            }
        }

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }
    //endregion

    //region RUN
    public void processPlayerRunWithoutControls(Player player, Ball ball) {
        if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
        } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);
        } else {
            throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
        }

        // Setup animation play parameters
        player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
        player.getAnimationsController().setPlayCompleted(false);
        player.getAnimationsController().resetCurrentFrameNumber();
        player.getAnimationsController().setTicksPassed(0);

        // Update internal parameters
        player.update();

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Switch to new state
        player.setCharacterState(CharacterState.STAND);
    }

    public void processPlayerRunWithMoveUpIntent(Player player, Ball ball) {
        // Update player position
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterY(nextCenterY);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Update animation frame ticks
        player.animate();

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithMoveUpRightIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;

        // Check if move direction was changed from left to right or vice versa
        if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            // Update face direction according to planned move direction
            player.setFaceDirection(FaceDirection.RIGHT);

            // Change to new animation
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            isAnimationChanged = true;
        }

        // Update player position
        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithMoveRightIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;

        // Check if move direction was changed from left to right or vice versa
        if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            // Update face direction according to planned move direction
            player.setFaceDirection(FaceDirection.RIGHT);

            // Change to new animation
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            isAnimationChanged = true;
        }

        // Update player position
        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        player.setCenterX(nextCenterX);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithMoveDownRightIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;

        // Check if move direction was changed from left to right or vice versa
        if (!FaceDirection.RIGHT.equals(player.getFaceDirection())) {
            // Update face direction according to planned move direction
            player.setFaceDirection(FaceDirection.RIGHT);

            // Change to new animation
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_RIGHT);

            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            isAnimationChanged = true;
        }

        // Update player position
        double nextCenterX = player.getCenterX() + player.getRunSpeed();
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithMoveDownIntent(Player player, Ball ball) {
        // Update player position
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterY(nextCenterY);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        // Update animation frame ticks
        player.animate();

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithMoveDownLeftIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;

        // Check if move direction was changed from left to right or vice versa
        if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
            // Update face direction according to planned move direction
            player.setFaceDirection(FaceDirection.LEFT);

            // Change to new animation
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            isAnimationChanged = true;
        }

        // Update player position
        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        double nextCenterY = player.getCenterY() + player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithMoveLeftIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;

        // Check if move direction was changed from left to right or vice versa
        if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
            // Update face direction according to planned move direction
            player.setFaceDirection(FaceDirection.LEFT);

            // Change to new animation
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);

            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            isAnimationChanged = true;
        }

        // Update player position
        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        player.setCenterX(nextCenterX);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithMoveUpLeftIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;

        // Check if move direction was changed from left to right or vice versa
        if (!FaceDirection.LEFT.equals(player.getFaceDirection())) {
            // Update face direction according to planned move direction
            player.setFaceDirection(FaceDirection.LEFT);

            // Change to new animation
            player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.RUN_LEFT);


            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            isAnimationChanged = true;
        }

        // Update player position
        double nextCenterX = player.getCenterX() - player.getRunSpeed();
        double nextCenterY = player.getCenterY() - player.getRunSpeed();
        player.setCenterX(nextCenterX);
        player.setCenterY(nextCenterY);

        // Update Z coordinate for "jump" frames
        adjustPlayerHeightForRun(player);

        // Check if player can get control over the ball
        checkForBallCaptureByPlayer(player, ball);

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithKickIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;
        if (player.isControllingTheBall()) {
            if (ball.getControlledBy() != null && ball.getControlledBy().getId() == player.getId()) {
                if (player.isReadyToKick()) {
                    // Change to new animation according to face direction
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_RIGHT);
                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                        player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.KICK_LEFT);
                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }

                    // Setup animation play parameters
                    player.getAnimationsController().setPlayMode(AnimationPlayMode.SINGLE);
                    player.getAnimationsController().setPlayCompleted(false);
                    player.getAnimationsController().resetCurrentFrameNumber();
                    player.getAnimationsController().setTicksPassed(0);

                    isAnimationChanged = true;

                    // Reset kick readiness
                    player.startKick();

                    // Hit the ball according to face direction
                    //TODO add ball kick
                    if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {

                    } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {

                    } else {
                        throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
                    }
                    ball.getMovementVector().setX(0);
                    ball.getMovementVector().setY(0);
                    ball.getMovementVector().setZ(1);
                    ball.setMovementSpeed(2);

                    // Switch to new state
                    player.setCharacterState(CharacterState.KICK);
                }
            } else {
                throw new RuntimeException("Error! Ball control mismatch!");
            }
        } else {
            //TODO action without ball
        }

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithPowerKickIntent(Player player, Ball ball) {
        boolean isAnimationChanged = false;
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

                isAnimationChanged = true;

                player.setCharacterState(CharacterState.POWER_KICK);
            }

        } else {
            // Player is not controlling the ball

        }

        if (!isAnimationChanged) {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }

    public void processPlayerRunWithPassIntent(Player player, Ball ball, Team teammates) {
    }
    //endregion

    //region KICK
    public void processPlayerKick(Player player) {
        if (player.getAnimationsController().isPlayCompleted()) {
            // Change to new animation according to face direction
            if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
            } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);
            } else {
                throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
            }

            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            // Switch to new state
            player.setCharacterState(CharacterState.STAND);

        } else {
            // Update animation frame ticks
            player.animate();
        }

        // Update internal parameters
        player.update();
    }
    //endregion

    //region POWER_KICK
    public void processPlayerPowerKick(Player player, Ball ball) {
        if (player.getAnimationsController().isPlayCompleted()) {
            // Change to new animation according to face direction
            if (FaceDirection.RIGHT.equals(player.getFaceDirection())) {
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_RIGHT);
            } else if (FaceDirection.LEFT.equals(player.getFaceDirection())) {
                player.getAnimationsController().setCurrentAnimation(Constants.AnimationName.STAND_LEFT);
            } else {
                throw new RuntimeException("Unknown face direction: " + player.getFaceDirection());
            }

            // Setup animation play parameters
            player.getAnimationsController().setPlayMode(AnimationPlayMode.LOOP);
            player.getAnimationsController().setPlayCompleted(false);
            player.getAnimationsController().resetCurrentFrameNumber();
            player.getAnimationsController().setTicksPassed(0);

            // Switch to new state
            player.setCharacterState(CharacterState.STAND);

        } else {
            // Update animation frame ticks
            player.animate();

            int frameNumber = player.getAnimationsController().getCurrentFrameNumber();
            if (frameNumber == 2) {
                if (player.isControllingTheBall()) {
                    // Hit the ball with the last frame
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
            }
        }

        // Update internal parameters
        player.update();
    }
    //endregion

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
}
