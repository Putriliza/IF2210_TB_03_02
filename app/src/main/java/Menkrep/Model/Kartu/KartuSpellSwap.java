package Menkrep.Model.Kartu;

import Menkrep.Model.Mana.Mana;

public class KartuSpellSwap extends KartuSpell {
    public KartuSpellSwap() {
        super("-", "-", "SWAP", new Mana(), 1);
    }

    public KartuSpellSwap(String nama, String deskripsi, Mana mana, int durasi) {
        super(nama, deskripsi, "SWAP", mana, durasi);
    }

    public void swap(KartuKarakter karakter) {
        int health = karakter.getHealth();
        int attack = karakter.getAttack();

        karakter.setHealth(attack);
        karakter.setAttack(health);
    }
}
