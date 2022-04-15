package Menkrep.Model.Kartu;

import Menkrep.Model.Mana.Mana;

public class KartuSpellLvl extends KartuSpell {
    public KartuSpellLvl() {
        super("-", "-", "LVL", new Mana(), 0);
    }

    public KartuSpellLvl(String nama, String deskripsi, Mana mana, int durasi) {
        super(nama, deskripsi, "LVL", mana, durasi);
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