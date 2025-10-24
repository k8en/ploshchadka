package org.kdepo.games.ploshchadka.builders;

import org.kdepo.games.ploshchadka.Constants;
import org.kdepo.games.ploshchadka.model.custom.game.GameRole;
import org.kdepo.games.ploshchadka.model.custom.game.PlayerParameters;

import java.util.HashMap;
import java.util.Map;

public class PlayerParametersBuilder {

    private static PlayerParametersBuilder instance;

    private final Map<Integer, PlayerParameters> playerParametersMap;

    public static PlayerParametersBuilder getInstance() {
        if (instance == null) {
            instance = new PlayerParametersBuilder();
        }
        return instance;
    }

    private PlayerParametersBuilder() {
        playerParametersMap = new HashMap<>();

        playerParametersMap.put(
                Constants.Character.KUNIO,
                new PlayerParameters(
                        Constants.Character.KUNIO,
                        "Kunio",
                        GameRole.MIDFIELDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.YORITSUNE,
                new PlayerParameters(
                        Constants.Character.YORITSUNE,
                        "Yoritsune",
                        GameRole.FORWARD,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.SAJI,
                new PlayerParameters(
                        Constants.Character.SAJI,
                        "Saji",
                        GameRole.FORWARD,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.HORIBATA,
                new PlayerParameters(
                        Constants.Character.HORIBATA,
                        "Horibata",
                        GameRole.DEFENDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.IWAKABE,
                new PlayerParameters(
                        Constants.Character.IWAKABE,
                        "Iwakabe",
                        GameRole.DEFENDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.GENEI,
                new PlayerParameters(
                        Constants.Character.GENEI,
                        "Genei",
                        null,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.UGAJIN,
                new PlayerParameters(
                        Constants.Character.UGAJIN,
                        "Ugajin",
                        GameRole.DEFENDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.ONITAKE,
                new PlayerParameters(
                        Constants.Character.ONITAKE,
                        "Onitake",
                        GameRole.MIDFIELDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.KUMON,
                new PlayerParameters(
                        Constants.Character.KUMON,
                        "Kumon",
                        GameRole.DEFENDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.KAIZUKI,
                new PlayerParameters(
                        Constants.Character.KAIZUKI,
                        "Kaizuki",
                        GameRole.MIDFIELDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.TSUNEWO,
                new PlayerParameters(
                        Constants.Character.TSUNEWO,
                        "Tsunewo",
                        GameRole.FORWARD,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.CARLOS,
                new PlayerParameters(
                        Constants.Character.CARLOS,
                        "Carlos",
                        null,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.CHON_IL,
                new PlayerParameters(
                        Constants.Character.CHON_IL,
                        "Chon-Il",
                        GameRole.MIDFIELDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.YONG_DOC,
                new PlayerParameters(
                        Constants.Character.YONG_DOC,
                        "Yong-Dok",
                        GameRole.FORWARD,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.MAN_SU,
                new PlayerParameters(
                        Constants.Character.MAN_SU,
                        "Man-Su",
                        GameRole.FORWARD,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.MING_HO,
                new PlayerParameters(
                        Constants.Character.MING_HO,
                        "Ming-Ho",
                        GameRole.MIDFIELDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.YONG_SU,
                new PlayerParameters(
                        Constants.Character.YONG_SU,
                        "Yong-Su",
                        GameRole.DEFENDER,
                        1.8d,
                        2.88d
                )
        );

        playerParametersMap.put(
                Constants.Character.SUN_CHOL,
                new PlayerParameters(
                        Constants.Character.SUN_CHOL,
                        "Sun-Chol",
                        null,
                        1.8d,
                        2.88d
                )
        );
    }

    public PlayerParameters getPredefinedPlayerParameters(int playerId) {
        PlayerParameters playerParameters = playerParametersMap.get(playerId);
        if (playerParameters == null) {
            throw new IllegalArgumentException("Unknown player id " + playerId);
        }
        return playerParameters;
    }
}
