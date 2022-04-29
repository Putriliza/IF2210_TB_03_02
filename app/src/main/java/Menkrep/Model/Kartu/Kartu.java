package Menkrep.Model.Kartu;

public class Kartu {
    private String nama;
    private String deskripsi;
    private String tipe;
    private String imgPath;
    private int mana;

    public Kartu(String nama, String deskripsi, String tipe, int mana, String imgPath) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tipe = tipe;
        this.imgPath = imgPath;
        this.mana = mana;
    }

    public Kartu(Kartu other) {
        this.nama = other.getNama();
        this.deskripsi = other.getDeskripsi();
        this.tipe = other.getTipe();
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getDisplayString() {
        return "";
    }
}
