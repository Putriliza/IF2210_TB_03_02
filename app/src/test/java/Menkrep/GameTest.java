package Menkrep;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Kartu.KartuKarakter;
import Menkrep.Model.Reference.Reference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private static Game game;
    private static Reference ref;

    @BeforeAll
    static void initAll() {
        game = Game.getInstance();
        ref = Reference.getInstance();
    }

    @Test
    @DisplayName("Initial round check")
    void initRoundCheck() {
        assertEquals(1, game.getRound());
    }

    @Test
    @DisplayName("Initial playerIndex check")
    void initPlayerIndexCheck() {
        assertEquals(0, game.getPlayerIndex());
    }

    @Test
    @DisplayName("Initial Phase Check")
    void initPhaseCheck() {
        assertEquals(Phase.Draw, game.getPhase());
    }

    @Test
    @DisplayName("Initial Player Check")
    void initPlayerCheck() {
        assertEquals(80, game.getPlayerOne().getHealthPoints());
        assertEquals(80, game.getPlayerTwo().getHealthPoints());
    }

    @Test
    @DisplayName("Get Max Mana Check")
    void initGetMaxManaCheck() {
        assertTrue(game.getManaCap() > 0);
        assertTrue(game.getManaCap() < 11);
    }

    @Test
    @DisplayName("Add Round After End Check")
    void initAddRoundCheck() {
        int initRound = game.getRound();
        game.nextPhase();
        game.nextPhase();
        game.nextPhase();
        game.nextPhase();

        // Baru pindah orang
        game.nextPhase();
        game.nextPhase();
        game.nextPhase();
        game.nextPhase();
        assertEquals(initRound + 1, game.getRound());
    }

    @Test
    @DisplayName("Attack Check")
    void initAttackNotEmptyCheck() {
        game.getPlayerOne().getBoard().set(0, new KartuKarakter(ref.getKarakter(), "Ender Dragon"));
        game.getPlayerTwo().getBoard().set(0, new KartuKarakter(ref.getKarakter(), "Ender Dragon"));
        game.attack(0, 0);
        assertEquals(80, game.getPlayerOne().getHealthPoints());
        assertEquals(80, game.getPlayerTwo().getHealthPoints());
        assertEquals(10, game.getPlayerOne().getBoard().get(0).getHealth());
        assertEquals(10, game.getPlayerTwo().getBoard().get(0).getHealth());
    }
}
