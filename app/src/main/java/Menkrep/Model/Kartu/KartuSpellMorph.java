package Menkrep.Model.Kartu;

import Menkrep.Model.Mana.Mana;

public class KartuSpellMorph extends KartuSpell {
    KartuSpellMorph() {
        super("-", "-", "MORPH", new Mana(), 0);
    }

    KartuSpellMorph(String nama, String deskripsi, Mana mana) {
        super(nama, deskripsi, "MORPH", mana, 0);
    }

    public void morph(KartuKarakter karakter, KartuKarakter tujuan) {
        karakter.setNama(tujuan.getNama());
        karakter.setDeskripsi(tujuan.getDeskripsi());
        karakter.setTipe(tujuan.getTipe());
        karakter.setJenis(tujuan.getJenis());
        karakter.setExp(0);
        karakter.setLevel(1);
        karakter.setHealth(tujuan.getHealth());
        karakter.setAttack(tujuan.getAttack());
        karakter.setBaseAttack(tujuan.getBaseAttack());
        karakter.setBaseHealth(tujuan.getBaseHealth());
    }
}
