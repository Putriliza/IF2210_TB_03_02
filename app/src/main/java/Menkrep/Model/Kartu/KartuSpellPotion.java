package Menkrep.Model.Kartu;

import Menkrep.Model.Mana.Mana;

public class KartuSpellPotion extends KartuSpell {
    public KartuSpellPotion() {
        super("-", "-", "POTION", new Mana(), 0);
    }

    public KartuSpellPotion(String nama, String deskripsi, Mana mana, int durasi) {
        super(nama, deskripsi, "POTION", mana, durasi);
    }

    /* Meningkatkan atribut attack dan / atau health.
    Penambahan ini dapat bernilai 0 bahkan negatif.
    Penambahan health dilakukan secara bertumpuk, ????
    sehingga serangan musuh akan mengambil health dari efek potion terakhir terlebih dahulu
    sebelum health dari karakter atau efek potion sebelumnya.
    Pengurangan health dapat membunuh karakter.
    Pengurangan attack tidak akan membuat attack karakter kurang dari 0. */
    public void potion(KartuKarakter karakter, int attack, int health) {
        int newAttack = karakter.getAttack() + attack;
        int newHealth = karakter.getHealth() + health;
        if (newAttack < 0) {
            newAttack = 0;
        }
        karakter.setAttack(newAttack);
        karakter.setHealth(newHealth);
    }
}