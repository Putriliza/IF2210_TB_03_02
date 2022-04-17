package Menkrep.Model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Menkrep.Model.Kartu.Kartu;
import Menkrep.Model.Kartu.KartuKarakter;
import Menkrep.Model.Kartu.KartuSpellLvl;
import Menkrep.Model.Kartu.KartuSpellMorph;
import Menkrep.Model.Kartu.KartuSpellPotion;
import Menkrep.Model.Kartu.KartuSpellSwap;
import Menkrep.Model.Reference.Reference;
import org.checkerframework.checker.units.qual.K;

public class Player {
    // Atribut
    private String name;
    private int healthPoints;
    private int mana;
    private ArrayList<Kartu> deck;
    private ArrayList<Kartu> hand;
    private ArrayList<KartuKarakter> board;

    // Konstruktor
    public Player(String name) throws IOException {
        this.name = name;
        this.healthPoints = 80;
        this.mana = 1;

        // Inisialisasi kartu di deck
        this.deck = new ArrayList<Kartu>();
    
        Reference ref = new Reference();
        for (String[] karakter : ref.getKarakter()) {
            this.deck.add(new KartuKarakter(karakter[1], karakter[2], karakter[3],
                    Integer.parseInt(karakter[4]), Integer.parseInt(karakter[5]),
                    Integer.parseInt(karakter[6]), Integer.parseInt(karakter[7]),
                    Integer.parseInt(karakter[8]), Integer.parseInt(karakter[9]),
                    karakter[10]));
        }
        for (String[] morph : ref.getMorph()) {
            // this.deck.add(new KartuSpellMorph(morph[1], morph[2], morph[3],
            //         Integer.parseInt(morph[4]), Integer.parseInt(morph[5])));
        }

        for (String[] potion : ref.getPtn()) {
            // this.deck.add(new KartuSpellPotion(potion[1], potion[2], potion[3],
            //         Integer.parseInt(potion[4]), Integer.parseInt(potion[5]),
            //        Integer.parseInt(potion[6]), Integer.parseInt(potion[7])));
        }

        for (String[] lvl : ref.getSwap()) {
            // this.deck.add(new KartuSpellLvl(lvl[1], lvl[2], lvl[3],
            //         Integer.parseInt(lvl[4]), Integer.parseInt(lvl[5])));
        }

        // Inisiasi kartu di hand
        this.hand = new ArrayList<Kartu>();
        this.board = new ArrayList<KartuKarakter>();
    }

    // Getter & Setter
    public String getName() {
        return this.name;
    }

    public int getHealthPoints(){
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0 && healthPoints <= 80) {
            this.healthPoints = healthPoints;
        }
    }

    public void setMana(int mana) {
        if (mana <= 10) {
            this.mana = mana;
        } else {
            this.mana = 10;
        }
    }

    public int getMana() {
        return this.mana;
    }

    public ArrayList<KartuKarakter> getBoard() {
        return board;
    }

    // Ambil kartu dari deck dan tambahkan ke hand
    // NOTES: Mungkin nanti si ambil kartu ini bisa di return boolean kayaknya? biar tau kapan dah ga bisa ambil kartu lagi
    public void shuffleCard() {
        // TO DO:
        // Cek apakah deck kosong
        int nDeck = this.deck.size();
        ArrayList <Kartu> randDeck = new ArrayList<Kartu>();

        if (nDeck > 0) {
            if (this.mana > 0) {
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
                // Tampilin hasil kartu acakan di randDeck
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
                this.mana--;
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

    public void removeToBoard(Reference reference, int src) {
        // TO DO:
        // Keluarkan kartu yang ada di hand
        // Masukin ke dalam board, cek apakah di board masih ada slot atau ga
        if((hand.size() - 1) > src && board.size() <= 6){
            board.add(new KartuKarakter(reference.getKarakter(),hand.get(src).getNama()));
        }
    }

    public void removeCardFromBoard(KartuKarakter card){
        this.board.remove(card);
    }

    public void attack(Player opponent) {
        // Pilih kartu di board yang mau dipake
        int idxPlayerBoard, idxOpponentBoard;
        KartuKarakter playerCard, opponentCard;

        // Semua kartu yang ada di board bakal disimpan sementara
        // di tempPlayerBoard, jadii player bisaa attack sampai
        // semua kartu yang ada di tempPlayerBoard habisss

        // Kalo ada kartu karakter yang mati, bakal langsung 
        // di remove di boardnya
        ArrayList<KartuKarakter> tempPlayerBoard = new ArrayList<KartuKarakter>();
        tempPlayerBoard.addAll(this.board);

        while (tempPlayerBoard.size() > 0) {
            // TO DO: Minta ke user mau kartu posisi yang index keberapa
            // ATAUUU nanti bisa langsung minta kartu yang mauu dipakenya ajaa, bukan indexnya
            idxPlayerBoard = 0;
            playerCard = tempPlayerBoard.remove(idxPlayerBoard);

            // Kalo lawan udah ga punya kartu yang bisa diattack
            if (opponent.getBoard().size() == 0) {
                opponent.setHealthPoints(opponent.getHealthPoints() - playerCard.getAttack());
            } else {
                // TO DO: Minta ke user mau lawan posisi yang index keberapa
                idxOpponentBoard = 0;
                opponentCard = opponent.getBoard().get(idxOpponentBoard);
                playerCard.attack(opponentCard);

                // Apabila kartu player atau opponent mati
                if (playerCard.getHealth() <= 0) {
                    this.removeCardFromBoard(playerCard);
                }
                if (opponentCard.getHealth() <= 0) {
                    opponent.removeCardFromBoard(opponentCard);
                }
            }
        }
    }
}
