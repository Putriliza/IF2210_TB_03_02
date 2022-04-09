package Menkrep.Model.Player;

import java.util.ArrayList;
import java.util.Random;

import Menkrep.Model.Kartu.Kartu;
import Menkrep.Model.Mana.Mana;

public class Player {
    // Atribut
    private String nama;
    private int healthPoints;
    private Mana mana;
    private ArrayList<Kartu> deck;
    private ArrayList<Kartu> hand;

    // Konstruktor
    public Player(String nama) {
        this.nama = nama;
        this.healthPoints = 80;
        this.mana = new Mana(1, 1);

        // Inisialisasi kartu di deck
        this.deck = new ArrayList<Kartu>();
        // TO DO: Nanti kalo kartu karakter sama spell dah ada, inisiasi dengan jumlah
        // yang sama
        for (int i = 0; i < 60; i++) {
            this.deck.add(new Kartu("Kartu " + i, "Deskripsi " + i, "Tipe " + i));
        }

        // Inisiasi kartu di hand
        this.hand = new ArrayList<Kartu>();
    }

    // Getter & Setter
    public String getNama() {
        return this.nama;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0 && healthPoints <= 80) {
            this.healthPoints = healthPoints;
        }
    }

    public void setMana(int mana) {
        // this.mana.setJumlah(mana);
    }

    public Mana getMana() {
        return this.mana;
    }

    // Ambil kartu dari deck dan tambahkan ke hand
    // NOTES: Mungkin nanti si ambil kartu ini bisa di return boolean kayaknya? biar tau kapan dah ga bisa ambil kartu lagi
    public void ambilKartu() {
        // TO DO:
        // Cek apakah deck kosong
        int nDeck = this.deck.size();
        ArrayList <Kartu> randDeck = new ArrayList<Kartu>();

        if (nDeck > 0) {
            int jumlahMana = this.mana.getMana();
            if (mana.getJumlah() > 0) {
                if (nDeck > 3) {
                    Random rand = new Random();
                    for (int i = 0; i < 3; i++) {
                        int randIndex = rand.nextInt(nDeck - i);
                        randDeck.add(this.deck.remove(randIndex));
                    }
                } else {
                    randDeck.addAll(this.deck);
                    this.deck.clear();
                }
                // TO DO: 
                // Tampilin hasil kartu acakan
                // Minta input dari user mauu pilih kartu yang mana
                // Misalnya disini index ke 0 yaa
                int indexPilih = 0;
    
                // Cek apakah kartu di hand sudah 5, kalo iya buang dulu yang di hand
                int nHand = this.hand.size();
                if (nHand > 5) {
                    System.out.println("Jumlah kartu di Hand sudah 5");
                    System.out.println("Buang kartu");
                    // TO DO:
                    // Minta input dari user mau buang yang mana
                    // Misalnya disini index ke 3 yaaa
                    int indexBuang = 3;
                    this.hand.remove(indexBuang);
                }
                // Pindahkan kartu dari deck ke hand (kurangin yang di deck, tambahin di hand)
                this.hand.add(randDeck.remove(indexPilih));
                this.deck.addAll(randDeck);
                this.mana.setJumlah(jumlahMana - 1);
            } else {
                System.out.println("Mana tidak cukup");
            }

        } else {
            System.out.println("Kartu di deck kosong....");
        }
    }

    public void descKartu() {
        // TO DO:
        // Tampilin deskripsi kartu yang ada di deck atau hand
    }

    public void descPlayer() {
        // TO DO:
        // Tampilin deskripsi player
    }

    public void keluarkanKartu() {
        // TO DO:
        // Keluarkan kartu yang ada di hand
        // Masukin ke dalam board, cek apakah di board masih ada slot atau ga
    }

    public void keluarkanMana() {
        // TO DO:
        // Keluarkan mana yang dipunya
        // Pilih kartu karakter di board yang mau di pakaiin mana
        // Kartu karakter nambah exp nya
    }
}
