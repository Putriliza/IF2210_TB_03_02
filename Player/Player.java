import java.util.ArrayList;

public class Player {
    // Atribut
    private String nama;
    private int healtPoints;
    private Mana mana;
    private ArrayList <Kartu> deck;
    private ArrayList <Kartu> hand;

    // Konstruktor
    public Player(String nama) {
        this.nama = nama;
        this.healtPoints = 80;
        this.mana = new Mana(1,1);

        // Inisialisasi kartu di deck
        this.deck = new ArrayList<Kartu>();
        // TO DO: Nanti kalo kartu karakter sama spell dah ada, inisiasi dengan jumlah yang sama
        for (int i = 0; i < 60; i++) {
            this.deck.add(new Kartu("Kartu " + i, "Deskripsi " + i, "Tipe " + i));
        }

        // Inisiasi kartu di hand
        this.hand = new ArrayList<Kartu>();
    }

    // Getter & Setter
    public String getNama(){
        return this.nama;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0 && healthPoints <= 80) {
            this.healtPoints = healthPoints;
        }
    }

    public void setMana(int mana) {
        // this.mana.setJumlah(mana);
    }

    public Mana getMana() {
        return this.mana;
    }

    // Ambil kartu dari deck dan tambahkan ke hand
    public void ambilKartu(){
        // TO DO:
        // Cek apakah deck kosong
        // Cek apakah mana cukup untuk mengambil kartu
        // Cek apakah kartu di hand sudah 5, kalo iya buang dulu yang di hand
        // Jika sudah memenuhi semua, maka ambil kartu dari deck secara random
        // Pindahkan kartu dari deck ke hand (kurangin yang di deck, tambahin di hand)
        // Kurangi mana
    }

    public void descKartu(){
        // TO DO:
        // Tampilin deskripsi kartu yang ada di deck atau hand
    }

    public void descPlaye(){
        // TO DO:
        // Tampilin deskripsi player
    }

    public void keluarkanKartu(){
        // TO DO:
        // Keluarkan kartu yang ada di hand
        // Masukin ke dalam board, cek apakah di board masih ada slot atau ga
    }

    public void keluarkanMana(){
        // TO DO:
        // Keluarkan mana yang dipunya
        // Pilih kartu karakter di board yang mau di pakaiin mana
        // Kartu karakter nambah exp nya
    }

    public static void main(String[] args) {
        Player player = new Player("Player");
        // player.setHealtPoints(10);
        // player.setMana(new Mana(1, 1));
        // player.setDeck(new ArrayList<Kartu>());
        // player.setHand(new ArrayList<Kartu>());
        // player.getMana().setGiliran(1);
        // player.getMana().setJumlah(1);
        // System.out.println(player.getMana().getMana());
        // player.getMana().tambahGiliran(1);
        // System.out.println(player.getMana().getMana());
    }
}
