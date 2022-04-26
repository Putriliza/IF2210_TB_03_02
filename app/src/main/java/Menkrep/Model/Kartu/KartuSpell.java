package Menkrep.Model.Kartu;

public class KartuSpell extends Kartu {
    
    private int mana;

    public KartuSpell(String nama, String deskripsi, String tipe, int mana, String imgPath) {
        super(nama, deskripsi, tipe);
        this.mana = mana;
        super.setImgPath(imgPath);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

}