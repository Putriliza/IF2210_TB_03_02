package Menkrep.Model.Kartu;

import java.util.List;
import java.util.ArrayList;

public class KartuSpellLvl extends KartuSpell {

    private int levelModifier;

    public KartuSpellLvl() {
        super("-", "-", "LVL", 0, "-");
        this.levelModifier = 0;
    }

    public KartuSpellLvl(String nama, String deskripsi, int mana, int levelModifier, String imgPath) {
        super(nama, deskripsi, "LVL", mana, imgPath);
        this.levelModifier = levelModifier;
    }

    // Meningkatkan/menurunkan level dari sebuah karakter sebanyak 1
    // serta mereset exp (membuat exp menjadi 0). Batas minimal level tetap 1 dan maksimal tetap 10.
    public void lvl(KartuKarakter karakter, boolean up) {
        int level = karakter.getLevel();
        if (up) {
            if (level < 10) {
                karakter.setLevel(level + 1);
                karakter.setExp(0);
            }
        } else {
            if (level > 1) {
                karakter.setLevel(level - 1);
                karakter.setExp(0);
            }
        }
    }
}