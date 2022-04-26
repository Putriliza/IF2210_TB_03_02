package Menkrep.Model.Kartu;

public class KartuSpell extends Kartu {
    
    private int mana;
    private String imgPath;

    public KartuSpell(String nama, String deskripsi, String tipe, int mana, String imgPath) {
        super(nama, deskripsi, tipe);
        this.mana = mana;
        this.imgPath = imgPath;
        super.setImgPath(imgPath);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}