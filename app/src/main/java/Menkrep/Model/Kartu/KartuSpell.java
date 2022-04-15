package Menkrep.Model.Kartu;

public class KartuSpell extends Kartu {
    
    private int mana;

    public KartuSpell(String nama, String deskripsi, String tipe, int mana) {
        super(nama, deskripsi, tipe);
        this.mana = mana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

}