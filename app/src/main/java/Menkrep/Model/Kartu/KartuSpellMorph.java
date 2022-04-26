package Menkrep.Model.Kartu;

import java.util.List;
import java.util.ArrayList;

public class KartuSpellMorph extends KartuSpell {

    public KartuSpellMorph() {
        super("-", "-", "MORPH", 0, "-");
    }

    public KartuSpellMorph(String nama, String deskripsi, int mana, String imgPath) {
        super(nama, deskripsi, "MORPH", mana, imgPath);
    }

    public KartuSpellMorph(List<String[]> reference, String nama) {
        super(nama, "", "MORPH", 0, "-");
        for (String[] morph: reference) {
            if (morph[1].equals(nama)) {
                this.setDeskripsi(morph[2]);
                this.setMana(Integer.parseInt(morph[5]));
                this.setImgPath(morph[3]);
            }
        }
    }


    // Mengubah sebuah karakter menjadi karakter lain dengan level 1 dengan exp 0.
    // Seluruh spell yang menempel akan dibuang.
    
    public void morph(KartuKarakter karakter, KartuKarakter tujuan) {
        karakter.setNama(tujuan.getNama());
        karakter.setDeskripsi(tujuan.getDeskripsi());
        karakter.setTipe(tujuan.getTipe());
        karakter.setJenis(tujuan.getJenis());
        karakter.setExp(0);
        karakter.setLevel(1);
        karakter.setHealth(tujuan.getHealth());
        karakter.setAttack(tujuan.getAttack());
        karakter.setAttackUp(tujuan.getAttackUp());
        karakter.setHealthUp(tujuan.getHealthUp());
        karakter.setActiveSpells(new ArrayList<KartuSpell>());

        // karakter.setBaseAttack(tujuan.getBaseAttack());
        // karakter.setBaseHealth(tujuan.getBaseHealth());
        
    }
}
