package Menkrep.Model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import Menkrep.Model.Enum.DrawStatus;
import Menkrep.Model.Kartu.Kartu;
import Menkrep.Model.Kartu.KartuKarakter;
import Menkrep.Model.Kartu.KartuSpellLvl;
import Menkrep.Model.Kartu.KartuSpellMorph;
import Menkrep.Model.Kartu.KartuSpellPotion;
import Menkrep.Model.Kartu.KartuSpellSwap;
import Menkrep.Model.Reference.Reference;

public class Player {
    // Atribut
    private String name;
    private int healthPoints;
    private int mana;
    private ArrayList<Kartu> deck;
    private ArrayList<Kartu> hand;
    private ArrayList<Kartu> draw;
    private ArrayList<KartuKarakter> board;

    // Konstruktor
    public Player(String name) throws IOException {
        this.name = name;
        this.healthPoints = 80;
        this.mana = 1;

        // Inisialisasi kartu di deck
        this.deck = new ArrayList<Kartu>();
    
        Reference ref = Reference.getInstance();
        for (String[] karakter : ref.getKarakter()) {
            this.deck.add(new KartuKarakter(ref.getKarakter(), karakter[1]));
        }
        for (String[] morph : ref.getMorph()) {
            this.deck.add(new KartuSpellMorph(morph[1], morph[2], Integer.parseInt(morph[5]), morph[3]));
        }

        for (String[] potion : ref.getPtn()) {
            this.deck.add(new KartuSpellPotion(ref.getPtn(), potion[1]));
        }

        for (String[] swap: ref.getSwap()) {
            this.deck.add(new KartuSpellSwap(ref.getSwap(), swap[1]));
        }

        for (String[] lvl : ref.getLvl()) {
            this.deck.add(new KartuSpellLvl(ref.getLvl(), lvl[1]));
        }

        Collections.shuffle(this.deck);

        // Inisiasi kartu di hand
        this.hand = new ArrayList<Kartu>();
    
        for (int i = 0; i < 3; i++) {
            this.hand.add(this.deck.remove(0));
        }

        // player mengambil 3 kartu teratas
        this.board = new ArrayList<KartuKarakter>();
        for (int i = 0; i < 3; i++) {
            if (this.deck.get(i) instanceof KartuKarakter) {
                this.board.add((KartuKarakter) this.deck.remove(i));
            }
        }

        if (this.board.size() < 5){
            for (int index = this.board.size(); index < 5; index++) {
                this.board.add(new KartuKarakter("-", "-", "-", 0, 0, 0, 0, 0, 0, "-", 0));
            }
        }
    }
    
    public void removeBoardCardAtIndex(int index) {
        if (index < 5) {
            this.board.set(index, new KartuKarakter("-", "-", "-", 0, 0, 0, 0, 0, 0, "-", 0));
        }
    }

    // Getter & Setter
    public String getName() {
        return this.name;
    }

    public ArrayList<Kartu> getHandCard(){
        return this.hand;
    }

    public int getHealthPoints(){
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0 && healthPoints <= 80) {
            this.healthPoints = healthPoints;
        }
    }

    public void reduceHP(int att){
        this.healthPoints -= att;
        if(this.healthPoints<0){
            this.healthPoints=0;
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

    public ArrayList<Kartu> getDeck() {
        return deck;
    }

    // Ambil kartu dari deck dan tambahkan ke hand
    // NOTES: Mungkin nanti si ambil kartu ini bisa di return boolean kayaknya? biar tau kapan dah ga bisa ambil kartu lagi
    public DrawStatus generateDrawCard() {
        draw = new ArrayList<>();
        int deckSize = deck.size();

        if (deckSize > 0) {
            if (deckSize > 3) {
                for (int i = 0; i < 3; i++) {
                    draw.add(deck.remove(0));
                }
            } else {
                draw.addAll(this.deck);
                this.deck.clear();
            }
        } else {
            return DrawStatus.DeckEmpty;
        }
        return DrawStatus.Success;
    }

    public ArrayList<Kartu> getDrawCard() {
        return draw;
    }

    public void pickDrawCard(int index) {
        Kartu pick = draw.remove(index);

        while (draw.size() > 0) {
            // Mengembalikan kartu secara acak
            Random rand = new Random();
            deck.add(rand.nextInt(deck.size()), draw.remove(0));
            deck.add(draw.remove(0));
        }

        if (hand.size() < 5) {
            hand.add(pick);
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

    public boolean boardIsEmpty(){
        for (KartuKarakter kartu: board) {
            if(!kartu.getNama().equals("-")){
                return false;
            }
        }
        return true;
    }

    public void resetBoardAttack(){
        for (KartuKarakter kartu: board) {
            kartu.resetAttack();
        }
    }
}
