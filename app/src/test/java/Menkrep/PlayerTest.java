package Menkrep;

import Menkrep.Model.Enum.DrawStatus;
import Menkrep.Model.Player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    @Test
    @DisplayName("Player instantiation check")
    void testPlayerConstructor() {
        String name = "steve";
        Player player = new Player(name);
        assertEquals(player.getName(), name);
        assertTrue(player.getDeckCapacity() <= 60);
        assertTrue(player.getDeckCapacity() >= 40);
        assertEquals(player.getDeck().size(), player.getDeckCapacity() - 3);
        assertEquals(80, player.getHealthPoints());
        assertEquals(5, player.getBoard().size());
        assertEquals(0, player.getDrawCard().size());
        assertEquals(1, player.getMana());
    }

    @Test
    @DisplayName("Test player health point")
    void testPlayerHealthPoint() {
        String name = "steve";
        Player player = new Player(name);

        player.setHealthPoints(70);
        assertEquals(70, player.getHealthPoints());

        int hp = player.getHealthPoints();
        player.setHealthPoints(-5);
        assertEquals(hp, player.getHealthPoints());

        player.setHealthPoints(80);
        player.reduceHP(10);
        assertEquals(70, player.getHealthPoints());

        player.reduceHP(100);
        assertEquals(0, player.getHealthPoints());
    }

    @Test
    @DisplayName("Test player mana")
    void testPlayerMana() {
        String name = "steve";
        Player player = new Player(name);

        player.setMana(8);
        assertEquals(player.getMana(), 8);

        player.setMana(20);
        assertEquals(player.getMana(), 10);
    }

    @Test
    @DisplayName("Test player board")
    void testPlayerBoard() {
        String name = "steve";
        Player player = new Player(name);

        assertTrue(player.boardIsEmpty());
    }

    @Test
    @DisplayName("Test player draw card")
    void testPlayerDrawCard() {
        String name = "steve";
        Player player = new Player(name);

        int deckSize = player.getDeck().size();
        int handSize = player.getHandCard().size();
        assertEquals(DrawStatus.Success, player.generateDrawCard());
        assertEquals(3, player.getDrawCard().size());
        assertEquals(deckSize - 3, player.getDeck().size());

        player.pickDrawCard(0);
        assertEquals(0, player.getDrawCard().size());
        assertEquals(deckSize - 1, player.getDeck().size());
        assertEquals(handSize + 1, player.getHandCard().size());
    }
}
