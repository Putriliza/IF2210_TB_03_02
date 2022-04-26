package Menkrep.Model.Kartu;

public class Kartu {
    private String nama;
    private String deskripsi;
    private String tipe;
    private String imgPath;

    public Kartu(String nama, String deskripsi, String tipe) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tipe = tipe;
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

    public String getImgPath(){
        return imgPath;
    }

    public void setImgPath(String imgPath){
        this.imgPath = imgPath;
    }
}
