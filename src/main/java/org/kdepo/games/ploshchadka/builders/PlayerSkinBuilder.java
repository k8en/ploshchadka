package org.kdepo.games.ploshchadka.builders;

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
