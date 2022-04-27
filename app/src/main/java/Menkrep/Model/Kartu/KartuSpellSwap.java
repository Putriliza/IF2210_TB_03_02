package Menkrep.Model.Kartu;

import java.util.List;
import java.util.ArrayList;


public class KartuSpellSwap extends KartuSpell {
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

        // cek spell yang ada di karakter, apakah punya swap, jika iya, maka ditambahkan durasi
        for (KartuSpell spell : karakter.getActiveSpells()) {
            if (spell.getTipe().equals("SWAP")) {
                KartuSpellSwap swap = (KartuSpellSwap) spell;
                swap.setDuration(swap.getDuration() + this.duration);
            }
        }
    }

    @Override
    public String toString() {
        return "Mana= " + getMana() +
                "\nDuration=" + duration;
    }
}
