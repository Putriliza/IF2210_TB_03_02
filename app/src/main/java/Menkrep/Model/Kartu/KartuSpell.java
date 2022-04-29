package Menkrep.Model.Kartu;

public class KartuSpell extends Kartu {

    public KartuSpell(String nama, String deskripsi, String tipe, int mana, String imgPath) {
        super(nama, deskripsi, tipe, mana, imgPath);
    }

    public KartuSpell(KartuSpell other) {
        this(other.getNama(), other.getDeskripsi(), other.getTipe(), other.getMana(), other.getImgPath());
    }

    @Override
    public String getDisplayString() {
        return "SPELL";
    }
}