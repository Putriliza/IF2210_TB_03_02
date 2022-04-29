package Menkrep.Model.Kartu;

import java.util.List;
import java.util.ArrayList;


public class KartuSpellSwap extends KartuSpell implements KartuSpellTemp{
    private int duration;

    public KartuSpellSwap() {
        super("-", "-", "SWAP", 0, "-");
        this.duration = 0;
    }

    public KartuSpellSwap(String nama, String deskripsi, int mana, int durasi, String imgPath) {
        super(nama, deskripsi, "SWAP", mana, imgPath);
        this.duration = durasi;
    }

    public KartuSpellSwap(List<String[]> reference, String nama) {
        super(nama, "-", "SWAP", 0, "-");
        for (String[] swap: reference) {
            if (swap[1].equals(nama)) {
                this.setDeskripsi(swap[2]);
                this.setMana(Integer.parseInt(swap[5]));
                this.duration = Integer.parseInt(swap[4]);
                this.setImgPath(swap[3]);
            }
        }
    }

    public KartuSpellSwap(KartuSpellSwap other) {
        super(other);
        this.duration = other.getDuration();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void reduceDuration() {
        this.duration -= 1;
    }

    /* Menukar attack dan health. Khusus untuk swap,
    apabila digunakan pada karakter yang telah memiliki efek swap maka durasinya yang akan bertambah,
    dan bukan dilakukan swap balik.
    Perhatikan: apabila sebelum swap attack suatu karakter adalah 0, maka setelah swap, karakter tersebut mati. */

    public void swap(KartuKarakter karakter) {
        int health = karakter.getHealth();
        int attack = karakter.getAttack();

        karakter.setHealth(attack);
        karakter.setAttack(health);

        karakter.addSwapDuration(2*duration);
    }

    @Override
    public String toString() {
        return "Mana= " + getMana() +
                "\nDuration=" + duration;
    }
}
