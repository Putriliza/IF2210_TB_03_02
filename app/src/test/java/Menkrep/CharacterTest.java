package Menkrep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Menkrep.Model.Kartu.KartuKarakter;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class CharacterTest {
    @Test
    @DisplayName("Max Exp Check")
    public void getMaxExpTest() {
        KartuKarakter karakterTest = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
        "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);

        assertEquals(1, karakterTest.getMaxExp());
    }

    @Test
    @DisplayName("Attack Check")
    public void attackTest() {
        KartuKarakter karakterTest1 = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
        "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);
        KartuKarakter karakterTest2 = new KartuKarakter("Sheep", "Sheep are common passive mobs that supply wool and mutton and are found in many of the grassy biomes.",
        "OVERWORLD", 1, 1, 1, 1, 0, 0, "card/image/character/Sheep.png", 1);

        karakterTest1.attack(karakterTest2);
        assertEquals(0, karakterTest2.getHealth());     // 1 - 10 = -9, health = 0
    }

    @Test
    @DisplayName("Naik Level Check")
    public void naikLevelTest() {
        KartuKarakter karakterTest = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
        "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);

        karakterTest.naikLevel();
        assertEquals(2, karakterTest.getLevel());
        assertEquals(0, karakterTest.getExp());
    }

    @Test
    @DisplayName("Naik Exp Check")
    public void naikExpTest() {
        KartuKarakter karakterTest = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
        "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);

        karakterTest.naikExp(1);
        assertEquals(1, karakterTest.getExp());
        assertEquals(2, karakterTest.getLevel());
    }


    
}
