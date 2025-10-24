package org.kdepo.games.ploshchadka.builders;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.custom.game.PlayerSkin;
import org.kdepo.games.ploshchadka.utils.FileUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.HashMap;
import java.util.Map;

public class PlayerSkinBuilder {

    private static PlayerSkinBuilder instance;

    private final Color skinColorTemplate;

    private final Color clothingColorTemplate;

    private final Color outlineColorTemplate;

    private final Map<String, BufferedImage> headTypesMap;

    private final Map<String, BufferedImage> bodyFramesMap;

    private final Map<Integer, PlayerSkin> playerSkinMap;

    public static PlayerSkinBuilder getInstance() {
        if (instance == null) {
            instance = new PlayerSkinBuilder();
        }
        return instance;
    }

    private PlayerSkinBuilder() {
        headTypesMap = new HashMap<>();
        bodyFramesMap = new HashMap<>();

        loadHeadTypes();
        loadBodyFrames();

        skinColorTemplate = new Color(239, 228, 176);
        clothingColorTemplate = new Color(195, 195, 195);
        outlineColorTemplate = new Color(0, 0, 0);

        playerSkinMap = new HashMap<>();
        playerSkinMap.put(Constants.Character.KUNIO, new PlayerSkin("01", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.YORITSUNE, new PlayerSkin("02", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.SAJI, new PlayerSkin("03", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.HORIBATA, new PlayerSkin("04", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.IWAKABE, new PlayerSkin("05", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.GENEI, new PlayerSkin("06", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.UGAJIN, new PlayerSkin("07", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.ONITAKE, new PlayerSkin("08", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.KUMON, new PlayerSkin("09", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.KAIZUKI, new PlayerSkin("10", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.TSUNEWO, new PlayerSkin("11", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));
        playerSkinMap.put(Constants.Character.CARLOS, new PlayerSkin("12", new Color(0, 0, 0), new Color(254, 129, 112), new Color(255, 255, 255)));

        playerSkinMap.put(Constants.Character.CHON_IL, new PlayerSkin("13", new Color(0, 0, 0), new Color(252, 116, 96), new Color(136, 112, 0)));
        playerSkinMap.put(Constants.Character.YONG_DOC, new PlayerSkin("14", new Color(0, 0, 0), new Color(252, 116, 96), new Color(136, 112, 0)));
        playerSkinMap.put(Constants.Character.MAN_SU, new PlayerSkin("15", new Color(0, 0, 0), new Color(252, 116, 96), new Color(136, 112, 0)));
        playerSkinMap.put(Constants.Character.MING_HO, new PlayerSkin("16", new Color(0, 0, 0), new Color(252, 116, 96), new Color(136, 112, 0)));
        playerSkinMap.put(Constants.Character.YONG_SU, new PlayerSkin("17", new Color(0, 0, 0), new Color(252, 116, 96), new Color(136, 112, 0)));
        playerSkinMap.put(Constants.Character.SUN_CHOL, new PlayerSkin("18", new Color(0, 0, 0), new Color(252, 116, 96), new Color(136, 112, 0)));
    }

    public PlayerSkin getPredefinedPlayerSkin(int playerId) {
        PlayerSkin playerSkin = playerSkinMap.get(playerId);
        if (playerSkin == null) {
            throw new IllegalArgumentException("Unknown player id " + playerId);
        }
        return playerSkin;
    }

    public void loadHeadTypes() {
        headTypesMap.clear();

        headTypesMap.put("01", FileUtils.loadImage("images/heads/head_type_01.png"));
        headTypesMap.put("01m", FileUtils.loadImage("images/heads/head_type_01m.png"));
        headTypesMap.put("01s", FileUtils.loadImage("images/heads/head_type_01s.png"));
        headTypesMap.put("01sm", FileUtils.loadImage("images/heads/head_type_01sm.png"));
        headTypesMap.put("01b", FileUtils.loadImage("images/heads/head_type_01b.png"));
        headTypesMap.put("01bm", FileUtils.loadImage("images/heads/head_type_01bm.png"));

        headTypesMap.put("02", FileUtils.loadImage("images/heads/head_type_02.png"));
        headTypesMap.put("02m", FileUtils.loadImage("images/heads/head_type_02m.png"));
        headTypesMap.put("02s", FileUtils.loadImage("images/heads/head_type_02s.png"));
        headTypesMap.put("02sm", FileUtils.loadImage("images/heads/head_type_02sm.png"));
        headTypesMap.put("02b", FileUtils.loadImage("images/heads/head_type_02b.png"));
        headTypesMap.put("02bm", FileUtils.loadImage("images/heads/head_type_02bm.png"));

        headTypesMap.put("03", FileUtils.loadImage("images/heads/head_type_03.png"));
        headTypesMap.put("03m", FileUtils.loadImage("images/heads/head_type_03m.png"));
        headTypesMap.put("03s", FileUtils.loadImage("images/heads/head_type_03s.png"));
        headTypesMap.put("03sm", FileUtils.loadImage("images/heads/head_type_03sm.png"));
        headTypesMap.put("03b", FileUtils.loadImage("images/heads/head_type_03b.png"));
        headTypesMap.put("03bm", FileUtils.loadImage("images/heads/head_type_03bm.png"));

        headTypesMap.put("04", FileUtils.loadImage("images/heads/head_type_04.png"));
        headTypesMap.put("04m", FileUtils.loadImage("images/heads/head_type_04m.png"));
        headTypesMap.put("04s", FileUtils.loadImage("images/heads/head_type_04s.png"));
        headTypesMap.put("04sm", FileUtils.loadImage("images/heads/head_type_04sm.png"));
        headTypesMap.put("04b", FileUtils.loadImage("images/heads/head_type_04b.png"));
        headTypesMap.put("04bm", FileUtils.loadImage("images/heads/head_type_04bm.png"));

        headTypesMap.put("05", FileUtils.loadImage("images/heads/head_type_05.png"));
        headTypesMap.put("05m", FileUtils.loadImage("images/heads/head_type_05m.png"));
        headTypesMap.put("05s", FileUtils.loadImage("images/heads/head_type_05s.png"));
        headTypesMap.put("05sm", FileUtils.loadImage("images/heads/head_type_05sm.png"));
        headTypesMap.put("05b", FileUtils.loadImage("images/heads/head_type_05b.png"));
        headTypesMap.put("05bm", FileUtils.loadImage("images/heads/head_type_05bm.png"));

        headTypesMap.put("06", FileUtils.loadImage("images/heads/head_type_06.png"));
        headTypesMap.put("06m", FileUtils.loadImage("images/heads/head_type_06m.png"));
        headTypesMap.put("06s", FileUtils.loadImage("images/heads/head_type_06s.png"));
        headTypesMap.put("06sm", FileUtils.loadImage("images/heads/head_type_06sm.png"));
        headTypesMap.put("06b", FileUtils.loadImage("images/heads/head_type_06b.png"));
        headTypesMap.put("06bm", FileUtils.loadImage("images/heads/head_type_06bm.png"));

        headTypesMap.put("07", FileUtils.loadImage("images/heads/head_type_07.png"));
        headTypesMap.put("07m", FileUtils.loadImage("images/heads/head_type_07m.png"));
        headTypesMap.put("07s", FileUtils.loadImage("images/heads/head_type_07s.png"));
        headTypesMap.put("07sm", FileUtils.loadImage("images/heads/head_type_07sm.png"));
        headTypesMap.put("07b", FileUtils.loadImage("images/heads/head_type_07b.png"));
        headTypesMap.put("07bm", FileUtils.loadImage("images/heads/head_type_07bm.png"));

        headTypesMap.put("08", FileUtils.loadImage("images/heads/head_type_08.png"));
        headTypesMap.put("08m", FileUtils.loadImage("images/heads/head_type_08m.png"));
        headTypesMap.put("08s", FileUtils.loadImage("images/heads/head_type_08s.png"));
        headTypesMap.put("08sm", FileUtils.loadImage("images/heads/head_type_08sm.png"));
        headTypesMap.put("08b", FileUtils.loadImage("images/heads/head_type_08b.png"));
        headTypesMap.put("08bm", FileUtils.loadImage("images/heads/head_type_08bm.png"));

        headTypesMap.put("09", FileUtils.loadImage("images/heads/head_type_09.png"));
        headTypesMap.put("09m", FileUtils.loadImage("images/heads/head_type_09m.png"));
        headTypesMap.put("09s", FileUtils.loadImage("images/heads/head_type_09s.png"));
        headTypesMap.put("09sm", FileUtils.loadImage("images/heads/head_type_09sm.png"));
        headTypesMap.put("09b", FileUtils.loadImage("images/heads/head_type_09b.png"));
        headTypesMap.put("09bm", FileUtils.loadImage("images/heads/head_type_09bm.png"));

        headTypesMap.put("10", FileUtils.loadImage("images/heads/head_type_10.png"));
        headTypesMap.put("10m", FileUtils.loadImage("images/heads/head_type_10m.png"));
        headTypesMap.put("10s", FileUtils.loadImage("images/heads/head_type_10s.png"));
        headTypesMap.put("10sm", FileUtils.loadImage("images/heads/head_type_10sm.png"));
        headTypesMap.put("10b", FileUtils.loadImage("images/heads/head_type_10b.png"));
        headTypesMap.put("10bm", FileUtils.loadImage("images/heads/head_type_10bm.png"));

        headTypesMap.put("11", FileUtils.loadImage("images/heads/head_type_11.png"));
        headTypesMap.put("11m", FileUtils.loadImage("images/heads/head_type_11m.png"));
        headTypesMap.put("11s", FileUtils.loadImage("images/heads/head_type_11s.png"));
        headTypesMap.put("11sm", FileUtils.loadImage("images/heads/head_type_11sm.png"));
        headTypesMap.put("11b", FileUtils.loadImage("images/heads/head_type_11b.png"));
        headTypesMap.put("11bm", FileUtils.loadImage("images/heads/head_type_11bm.png"));

        headTypesMap.put("12", FileUtils.loadImage("images/heads/head_type_12.png"));
        headTypesMap.put("12m", FileUtils.loadImage("images/heads/head_type_12m.png"));
        headTypesMap.put("12s", FileUtils.loadImage("images/heads/head_type_12s.png"));
        headTypesMap.put("12sm", FileUtils.loadImage("images/heads/head_type_12sm.png"));
        headTypesMap.put("12b", FileUtils.loadImage("images/heads/head_type_12b.png"));
        headTypesMap.put("12bm", FileUtils.loadImage("images/heads/head_type_12bm.png"));

        headTypesMap.put("13", FileUtils.loadImage("images/heads/head_type_13.png"));
        headTypesMap.put("13m", FileUtils.loadImage("images/heads/head_type_13m.png"));
        headTypesMap.put("13s", FileUtils.loadImage("images/heads/head_type_13s.png"));
        headTypesMap.put("13sm", FileUtils.loadImage("images/heads/head_type_13sm.png"));
        headTypesMap.put("13b", FileUtils.loadImage("images/heads/head_type_13b.png"));
        headTypesMap.put("13bm", FileUtils.loadImage("images/heads/head_type_13bm.png"));

        headTypesMap.put("14", FileUtils.loadImage("images/heads/head_type_14.png"));
        headTypesMap.put("14m", FileUtils.loadImage("images/heads/head_type_14m.png"));
        headTypesMap.put("14s", FileUtils.loadImage("images/heads/head_type_14s.png"));
        headTypesMap.put("14sm", FileUtils.loadImage("images/heads/head_type_14sm.png"));
        headTypesMap.put("14b", FileUtils.loadImage("images/heads/head_type_14b.png"));
        headTypesMap.put("14bm", FileUtils.loadImage("images/heads/head_type_14bm.png"));

        headTypesMap.put("15", FileUtils.loadImage("images/heads/head_type_15.png"));
        headTypesMap.put("15m", FileUtils.loadImage("images/heads/head_type_15m.png"));
        headTypesMap.put("15s", FileUtils.loadImage("images/heads/head_type_15s.png"));
        headTypesMap.put("15sm", FileUtils.loadImage("images/heads/head_type_15sm.png"));
        headTypesMap.put("15b", FileUtils.loadImage("images/heads/head_type_15b.png"));
        headTypesMap.put("15bm", FileUtils.loadImage("images/heads/head_type_15bm.png"));

        headTypesMap.put("16", FileUtils.loadImage("images/heads/head_type_16.png"));
        headTypesMap.put("16m", FileUtils.loadImage("images/heads/head_type_16m.png"));
        headTypesMap.put("16s", FileUtils.loadImage("images/heads/head_type_16s.png"));
        headTypesMap.put("16sm", FileUtils.loadImage("images/heads/head_type_16sm.png"));
        headTypesMap.put("16b", FileUtils.loadImage("images/heads/head_type_16b.png"));
        headTypesMap.put("16bm", FileUtils.loadImage("images/heads/head_type_16bm.png"));

        headTypesMap.put("17", FileUtils.loadImage("images/heads/head_type_17.png"));
        headTypesMap.put("17m", FileUtils.loadImage("images/heads/head_type_17m.png"));
        headTypesMap.put("17s", FileUtils.loadImage("images/heads/head_type_17s.png"));
        headTypesMap.put("17sm", FileUtils.loadImage("images/heads/head_type_17sm.png"));
        headTypesMap.put("17b", FileUtils.loadImage("images/heads/head_type_17b.png"));
        headTypesMap.put("17bm", FileUtils.loadImage("images/heads/head_type_17bm.png"));

        headTypesMap.put("18", FileUtils.loadImage("images/heads/head_type_18.png"));
        headTypesMap.put("18m", FileUtils.loadImage("images/heads/head_type_18m.png"));
        headTypesMap.put("18s", FileUtils.loadImage("images/heads/head_type_18s.png"));
        headTypesMap.put("18sm", FileUtils.loadImage("images/heads/head_type_18sm.png"));
        headTypesMap.put("18b", FileUtils.loadImage("images/heads/head_type_18b.png"));
        headTypesMap.put("18bm", FileUtils.loadImage("images/heads/head_type_18bm.png"));
    }

    public void loadBodyFrames() {
        bodyFramesMap.clear();

        bodyFramesMap.put("01", FileUtils.loadImage("animations/player/frame_01.png"));
        bodyFramesMap.put("01m", FileUtils.loadImage("animations/player/frame_01m.png"));
        bodyFramesMap.put("02", FileUtils.loadImage("animations/player/frame_02.png"));
        bodyFramesMap.put("02m", FileUtils.loadImage("animations/player/frame_02m.png"));
        bodyFramesMap.put("03", FileUtils.loadImage("animations/player/frame_03.png"));
        bodyFramesMap.put("03m", FileUtils.loadImage("animations/player/frame_03m.png"));
        bodyFramesMap.put("04", FileUtils.loadImage("animations/player/frame_04.png"));
        bodyFramesMap.put("04m", FileUtils.loadImage("animations/player/frame_04m.png"));
        bodyFramesMap.put("05", FileUtils.loadImage("animations/player/frame_05.png"));
        bodyFramesMap.put("05m", FileUtils.loadImage("animations/player/frame_05m.png"));
        bodyFramesMap.put("06", FileUtils.loadImage("animations/player/frame_06.png"));
        bodyFramesMap.put("06m", FileUtils.loadImage("animations/player/frame_06m.png"));
        bodyFramesMap.put("07", FileUtils.loadImage("animations/player/frame_07.png"));
        bodyFramesMap.put("07m", FileUtils.loadImage("animations/player/frame_07m.png"));
        bodyFramesMap.put("08", FileUtils.loadImage("animations/player/frame_08.png"));
        bodyFramesMap.put("08m", FileUtils.loadImage("animations/player/frame_08m.png"));
    }

    public Map<String, BufferedImage> buildImagesMap(PlayerSkin playerSkin) {
        Map<String, BufferedImage> imagesMap = new HashMap<>();

        // Normal (right)
        BufferedImage headTypeImage = headTypesMap.get(playerSkin.getHeadType());

        // Normal mirrored
        BufferedImage headTypeImageM = headTypesMap.get(playerSkin.getHeadType() + "m");

        // Side view (right)
        BufferedImage headTypeImageS = headTypesMap.get(playerSkin.getHeadType() + "s");

        // Side view mirrored
        BufferedImage headTypeImageSM = headTypesMap.get(playerSkin.getHeadType() + "sm");

        // Side view (right)
        BufferedImage headTypeImageB = headTypesMap.get(playerSkin.getHeadType() + "b");

        // Back view mirrored
        BufferedImage headTypeImageBM = headTypesMap.get(playerSkin.getHeadType() + "bm");

        BufferedImage frame01 = mergeHeadWithBody(headTypeImage, bodyFramesMap.get("01"), 17, 0);
        imagesMap.put("01", frame01);

        BufferedImage frame01m = mergeHeadWithBody(headTypeImageM, bodyFramesMap.get("01m"), 15, 0);
        imagesMap.put("01m", frame01m);

        BufferedImage frame02 = mergeHeadWithBody(headTypeImage, bodyFramesMap.get("02"), 17, 0);
        imagesMap.put("02", frame02);

        BufferedImage frame02m = mergeHeadWithBody(headTypeImageM, bodyFramesMap.get("02m"), 15, 0);
        imagesMap.put("02m", frame02m);

        BufferedImage frame03 = mergeHeadWithBody(headTypeImageS, bodyFramesMap.get("03"), 15, 0);
        imagesMap.put("03", frame03);

        BufferedImage frame03m = mergeHeadWithBody(headTypeImageSM, bodyFramesMap.get("03m"), 17, 0);
        imagesMap.put("03m", frame03m);

        BufferedImage frame04 = mergeHeadWithBody(headTypeImageB, bodyFramesMap.get("04"), 28, 0);
        imagesMap.put("04", frame04);

        BufferedImage frame04m = mergeHeadWithBody(headTypeImageBM, bodyFramesMap.get("04m"), 4, 0);
        imagesMap.put("04m", frame04m);

        BufferedImage frame05 = mergeHeadWithBody(headTypeImageB, bodyFramesMap.get("05"), 18, 0);
        imagesMap.put("05", frame05);

        BufferedImage frame05m = mergeHeadWithBody(headTypeImageBM, bodyFramesMap.get("05m"), 14, 0);
        imagesMap.put("05m", frame05m);

        BufferedImage frame06 = mergeHeadWithBody(headTypeImageS, bodyFramesMap.get("06"), 16, 0);
        imagesMap.put("06", frame06);

        BufferedImage frame06m = mergeHeadWithBody(headTypeImageSM, bodyFramesMap.get("06m"), 16, 0);
        imagesMap.put("06m", frame06m);

        BufferedImage frame07 = mergeHeadWithBody(headTypeImage, bodyFramesMap.get("07"), 16, 0);
        imagesMap.put("07", frame07);

        BufferedImage frame07m = mergeHeadWithBody(headTypeImageM, bodyFramesMap.get("07m"), 16, 0);
        imagesMap.put("07m", frame07m);

        BufferedImage frame08 = mergeHeadWithBody(headTypeImage, bodyFramesMap.get("08"), 15, 16);
        imagesMap.put("08", frame08);

        BufferedImage frame08m = mergeHeadWithBody(headTypeImageM, bodyFramesMap.get("08m"), 17, 16);
        imagesMap.put("08m", frame08m);

        // Change colors
        for (BufferedImage image : imagesMap.values()) {
            // Update skin color
            changeSpecificColor(image, skinColorTemplate, playerSkin.getSkinColor());

            // Update clothing color
            changeSpecificColor(image, clothingColorTemplate, playerSkin.getClothingColor());

            // Update outline color
            changeSpecificColor(image, outlineColorTemplate, playerSkin.getOutlineColor());
        }

        return imagesMap;
    }

    public BufferedImage mergeHeadWithBody(BufferedImage headImage, BufferedImage bodyImage, int headOffsetX, int headOffsetY) {
        BufferedImage result = new BufferedImage(
                bodyImage.getWidth(),
                bodyImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = result.createGraphics();

        try {
            g2d.drawImage(bodyImage, 0, 0, null);
            g2d.drawImage(headImage, headOffsetX, headOffsetY, null);

        } finally {
            g2d.dispose();
        }

        return result;
    }

    public void changeSpecificColor(BufferedImage image, Color targetColor, Color replacementColor) {
        if (image.getType() != BufferedImage.TYPE_INT_ARGB && image.getType() != BufferedImage.TYPE_INT_RGB) {
            System.err.println("Unsupported BufferedImage type for direct pixel access.");
            return;
        }

        int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        int targetRgb = targetColor.getRGB();
        int replacementRgb = replacementColor.getRGB();

        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] == targetRgb) {
                pixels[i] = replacementRgb;
            }
        }
    }

}
