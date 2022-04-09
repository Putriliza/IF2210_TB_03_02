package Menkrep.Model.Mana;

public class Mana {
    private int jumlah;
    private int giliran;

    public Mana() {
        this.jumlah = 1;
        this.giliran = 1;
    }

    public Mana(int jumlah, int giliran) {
        this.jumlah = jumlah;
        this.giliran = giliran;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getGiliran() {
        return giliran;
    }

    public void setGiliran(int giliran) {
        this.giliran = giliran;
        if (this.giliran > 10) {
            this.jumlah = 10;
        } else {
            this.jumlah = this.giliran;
        }
    }

    public void tambahGiliran(int giliran) {
        this.giliran += giliran;
        if (this.giliran > 10) {
            this.jumlah = 10;
        } else {
            this.jumlah = this.giliran;
        }
    }

    public int getMana() {
        if (this.giliran > 10) {
            return 10;
        } else {
            return this.jumlah;
        }
    }
}
