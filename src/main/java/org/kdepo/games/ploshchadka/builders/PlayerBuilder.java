package org.kdepo.games.ploshchadka.builders;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.base.animation.Animation;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationFrame;
import org.kdepo.games.ploshchadka.model.base.animation.AnimationsController;
import org.kdepo.games.ploshchadka.model.base.geometry.Point3D;
import org.kdepo.games.ploshchadka.model.custom.characters.CharacterState;
import org.kdepo.games.ploshchadka.model.custom.characters.FaceDirection;
import org.kdepo.games.ploshchadka.model.custom.game.Player;
import org.kdepo.games.ploshchadka.model.custom.game.PlayerParameters;
import org.kdepo.games.ploshchadka.model.custom.game.PlayerSkin;

import java.awt.image.BufferedImage;
import java.util.Map;

public class PlayerBuilder {

    private static PlayerBuilder instance;

    public static PlayerBuilder getInstance() {
        if (instance == null) {
            instance = new PlayerBuilder();
        }
        return instance;
    }

    private PlayerBuilder() {
    }

    public Player buildPlayer(PlayerParameters playerParameters, PlayerSkin playerSkin, int teamId, Point3D position, FaceDirection faceDirection) {
        Player player = new Player();

        // Prepare frames images
        PlayerSkinBuilder builder = PlayerSkinBuilder.getInstance();
        Map<String, BufferedImage> imagesMap = builder.buildImagesMap(playerSkin);

        // Setup animation controller
        AnimationsController animationsController = prepareAnimationController(imagesMap);
        player.setAnimationsController(animationsController);

        // Set current active animation
        String currentAnimationName;
        if (FaceDirection.RIGHT.equals(faceDirection)) {
            currentAnimationName = Constants.AnimationName.STAND_RIGHT;
        } else if (FaceDirection.LEFT.equals(faceDirection)) {
            currentAnimationName = Constants.AnimationName.STAND_LEFT;
        } else {
            throw new RuntimeException("Unknown face direction: " + faceDirection);
        }
        player.setFaceDirection(faceDirection);
        animationsController.setCurrentAnimation(currentAnimationName);

        player.setId(playerParameters.getPlayerId());
        player.setTeamId(teamId);

        // Adjust positioning
        initPositionParameters(player, position);

        // Character parameters
        player.setCharacterHeight(64);
        player.setCharacterRadius(16);
        player.setCharacterState(CharacterState.STAND);

        player.setHumanControls(false);
        player.setControllingTheBall(false);

        player.setRunSpeed(playerParameters.getRunSpeed());
        player.setDashSpeed(playerParameters.getRunSpeed() * 1.6);

        return player;
    }

    public AnimationsController prepareAnimationController(Map<String, BufferedImage> imagesMap) {
        AnimationsController animationsController = new AnimationsController();

        BufferedImage imageFrame01 = imagesMap.get("01");
        BufferedImage imageFrame01m = imagesMap.get("01m");
        BufferedImage imageFrame02 = imagesMap.get("02");
        BufferedImage imageFrame02m = imagesMap.get("02m");
        BufferedImage imageFrame03 = imagesMap.get("03");
        BufferedImage imageFrame03m = imagesMap.get("03m");
        BufferedImage imageFrame04 = imagesMap.get("04");
        BufferedImage imageFrame04m = imagesMap.get("04m");
        BufferedImage imageFrame05 = imagesMap.get("05");
        BufferedImage imageFrame05m = imagesMap.get("05m");
        BufferedImage imageFrame06 = imagesMap.get("06");
        BufferedImage imageFrame06m = imagesMap.get("06m");
        BufferedImage imageFrame07 = imagesMap.get("07");
        BufferedImage imageFrame07m = imagesMap.get("07m");
        BufferedImage imageFrame08 = imagesMap.get("08");
        BufferedImage imageFrame08m = imagesMap.get("08m");

        // Prepare animations
        AnimationFrame[] standRightFrames = new AnimationFrame[1];
        standRightFrames[0] = new AnimationFrame(0, imageFrame01, 999);
        Animation standRightAnimation = new Animation(Constants.AnimationName.STAND_RIGHT, standRightFrames, 0);
        animationsController.addAnimation(standRightAnimation);

        AnimationFrame[] runRightFrames = new AnimationFrame[2];
        runRightFrames[0] = new AnimationFrame(0, imageFrame02, 8);
        runRightFrames[1] = new AnimationFrame(1, imageFrame01, 8);
        Animation runRightAnimation = new Animation(Constants.AnimationName.RUN_RIGHT, runRightFrames, 0);
        animationsController.addAnimation(runRightAnimation);

        AnimationFrame[] standLeftFrames = new AnimationFrame[1];
        standLeftFrames[0] = new AnimationFrame(0, imageFrame01m, 999);
        Animation standLeftAnimation = new Animation(Constants.AnimationName.STAND_LEFT, standLeftFrames, 0);
        animationsController.addAnimation(standLeftAnimation);

        AnimationFrame[] runLeftFrames = new AnimationFrame[2];
        runLeftFrames[0] = new AnimationFrame(0, imageFrame02m, 8);
        runLeftFrames[1] = new AnimationFrame(1, imageFrame01m, 8);
        Animation runLeftAnimation = new Animation(Constants.AnimationName.RUN_LEFT, runLeftFrames, 0);
        animationsController.addAnimation(runLeftAnimation);

        AnimationFrame[] kickRightFrames = new AnimationFrame[1];
        kickRightFrames[0] = new AnimationFrame(0, imageFrame03, 12);
        Animation kickRightAnimation = new Animation(Constants.AnimationName.KICK_RIGHT, kickRightFrames, 0);
        animationsController.addAnimation(kickRightAnimation);

        AnimationFrame[] kickLeftFrames = new AnimationFrame[1];
        kickLeftFrames[0] = new AnimationFrame(0, imageFrame03m, 12);
        Animation kickLeftAnimation = new Animation(Constants.AnimationName.KICK_LEFT, kickLeftFrames, 0);
        animationsController.addAnimation(kickLeftAnimation);

        AnimationFrame[] powerKickRightFrames = new AnimationFrame[3];
        powerKickRightFrames[0] = new AnimationFrame(0, imageFrame04, 6);
        powerKickRightFrames[1] = new AnimationFrame(1, imageFrame05, 6);
        powerKickRightFrames[2] = new AnimationFrame(2, imageFrame06, 6);
        Animation powerKickRightAnimation = new Animation(Constants.AnimationName.POWER_KICK_RIGHT, powerKickRightFrames, 0);
        animationsController.addAnimation(powerKickRightAnimation);

        AnimationFrame[] powerKickLeftFrames = new AnimationFrame[3];
        powerKickLeftFrames[0] = new AnimationFrame(0, imageFrame04m, 6);
        powerKickLeftFrames[1] = new AnimationFrame(1, imageFrame05m, 6);
        powerKickLeftFrames[2] = new AnimationFrame(2, imageFrame06m, 6);
        Animation powerKickLeftAnimation = new Animation(Constants.AnimationName.POWER_KICK_LEFT, powerKickLeftFrames, 0);
        animationsController.addAnimation(powerKickLeftAnimation);

        return animationsController;
    }

    public void initPositionParameters(Player player, Point3D position) {
        player.setCenterX(position.getX());
        player.setCenterY(position.getY());
        player.setCenterZ(position.getZ());

        int width = player.getAnimationsController().getCurrentAnimation()
                .getAnimationFrames()[player.getAnimationsController().getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getWidth();
        player.setWidth(width);

        int height = player.getAnimationsController().getCurrentAnimation()
                .getAnimationFrames()[player.getAnimationsController().getCurrentAnimation().getCurrentFrameNumber()]
                .getFrameImage()
                .getHeight();
        player.setHeight(height);
    }
}
