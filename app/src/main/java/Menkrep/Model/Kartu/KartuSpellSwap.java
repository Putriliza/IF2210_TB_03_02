package Menkrep.Model.Kartu;


public class KartuSpellSwap extends KartuSpell {
    private int duration;

    public KartuSpellSwap() {
        super("-", "-", "SWAP", 0);
        this.duration = 0;
    }

    public KartuSpellSwap(String nama, String deskripsi, int mana, int durasi) {
        super(nama, deskripsi, "SWAP", mana);
        this.duration = durasi;
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

        // TO DO
        // cek spell yang ada di karakter, apakah punya swap, jika iya, maka ditambahkan durasi
    }
}
