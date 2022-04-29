package Menkrep.Model.Kartu;

import java.util.List;
import java.util.ArrayList;

public class KartuSpellLvl extends KartuSpell {

    public KartuSpellLvl() {
        super("-", "-", "LVL", 0, "-");
    }

    public KartuSpellLvl(String nama, String deskripsi, int mana, String imgPath) {
        super(nama, deskripsi, "LVL", mana, imgPath);
    }

    public KartuSpellLvl(List<String[]> reference, String nama) {
        super(nama, "", "LVL", 0, "-");
        for (String[] spell : reference) {
            if (spell[1].equals(nama)) {
                this.setDeskripsi(spell[2]);
                this.setImgPath(spell[3]);
                super.setMana(0);   // mana diset ketika akan dipakai ke character
            }
        }
    }

    public KartuSpellLvl(KartuSpellLvl other) {
        super(other);
    }

    // Meningkatkan/menurunkan level dari sebuah karakter sebanyak 1
    // serta mereset exp (membuat exp menjadi 0). Batas minimal level tetap 1 dan maksimal tetap 10.
    public void lvl(KartuKarakter karakter) {
        int level = karakter.getLevel();
        if (this.getNama() == "LVLUP") {
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

    @Override
    public String toString() {
        return "Mana: " + getMana();
    }
}