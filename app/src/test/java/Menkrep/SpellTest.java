package Menkrep;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Menkrep.Model.Kartu.KartuKarakter;
import Menkrep.Model.Kartu.KartuSpell;
import Menkrep.Model.Kartu.KartuSpellLvl;
import Menkrep.Model.Kartu.KartuSpellMorph;
import Menkrep.Model.Kartu.KartuSpellSwap;
import Menkrep.Model.Kartu.KartuSpellPotion;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;


public class SpellTest {
    @Test
    @DisplayName("Level Check")
    public void lvlTest() {
        KartuSpellLvl lvlTest = new KartuSpellLvl("LVLUP", "One level up", 1, "card/image/spell/lvl/LVLUP.png");
        KartuKarakter karakterTest = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
                             "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);
        lvlTest.lvl(karakterTest);
        assertEquals(2, karakterTest.getLevel());   // check naik level
    }

    @Test
    @DisplayName("Morph Check")
    public void morphTest() {
        KartuSpellMorph morphTest = new KartuSpellMorph("Sheepify", "Turns any being into a measly sheep, even you", 4, "card/image/spell/morph/Sheepify.png", 4);
        KartuKarakter karakterTest1 = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
                                "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);
        KartuKarakter karakterTest2 = new KartuKarakter("Sheep", "Sheep are common passive mobs that supply wool and mutton and are found in many of the grassy biomes.",
                                "OVERWORLD", 1, 1, 1, 1, 0, 0, "card/image/character/Sheep.png", 1);
        morphTest.morph(karakterTest1, karakterTest2);
        assertEquals("Sheep", karakterTest1.getNama());
        assertEquals("Sheep are common passive mobs that supply wool and mutton and are found in many of the grassy biomes.", karakterTest1.getDeskripsi());
        assertEquals(1, karakterTest1.getLevel());
        assertEquals(1, karakterTest1.getHealth());
        assertEquals(1, karakterTest1.getAttack());
        assertEquals("card/image/character/Sheep.png", karakterTest1.getImgPath());
        assertEquals(4, karakterTest1.getMana());
    }

    @Test
    @DisplayName("Swap Check")
    public void swapTest() {
        KartuSpellSwap swapTest = new KartuSpellSwap("Potion of Turtle Master", "It won't last long", 1, 1, "card/image/spell/swap/Potion of Turtle Master.png");
        KartuKarakter karakterTest = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
                             "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);
        swapTest.swap(karakterTest);        // swap health dan attack
        assertEquals(2, karakterTest.getAttack());
        assertEquals(10, karakterTest.getHealth());
    }

    @Test
    @DisplayName("Potion Check")
    public void potionTest() {
        KartuSpellPotion potionTest = new KartuSpellPotion("Deathly Magic", "The magic that is deadly", 2, 2, 2, 2, "card/image/spell/potion/Deathly Magic.png");
        KartuKarakter karakterTest = new KartuKarakter("Creeper", "A creeper is a common hostile mob that silently approaches players and explodes.",
                             "OVERWORLD", 1, 1, 2, 10, 1, 1, "card/image/character/Creeper.png", 4);
        potionTest.potion(karakterTest);
        assertEquals(4, karakterTest.getHealth());     // health = 2 + 2
    }
    
}