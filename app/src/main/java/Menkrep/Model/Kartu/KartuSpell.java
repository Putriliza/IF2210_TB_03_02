package Menkrep.Model.Kartu;

import Menkrep.Model.Mana.Mana;

public class KartuSpell extends Kartu {
    
    private Mana mana;
    private int durasi;

    public KartuSpell(String nama, String deskripsi, String tipe, Mana mana, int durasi) {
        super(nama, deskripsi, tipe);
        this.mana = mana;
        this.durasi = durasi;
    }

    public Mana getMana() {
        return mana;
    }

    public void setMana(Mana mana) {
        this.mana = mana;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

}