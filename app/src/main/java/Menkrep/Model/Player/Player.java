package Menkrep.Model.Player;

import Menkrep.Model.Enum.DrawStatus;
import Menkrep.Model.Kartu.*;
import Menkrep.Model.Reference.Reference;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    // Atribut
    private final String name;
    private int healthPoints;
    private int mana;
    private final int deckCapacity;
    private final ArrayList<Kartu> deck;
    private final ArrayList<Kartu> hand;
    private ArrayList<Kartu> draw;
    private final ArrayList<KartuKarakter> board;

    // Konstruktor
    public Player(String name) {
        this.name = name;
        this.healthPoints = 80;
        this.mana = 1;

        // kapasitas deck random antara 40 dan 60
        Random rand = new Random();
        this.deckCapacity = 40 + rand.nextInt(21);

        // Inisialisasi kartu
        this.deck = new ArrayList<>();
        this.draw = new ArrayList<>();
        this.board = new ArrayList<>();

        Reference ref = Reference.getInstance();
        ArrayList<Kartu> referenceDeck = ref.getReferenceDeck();
        for (int i = 0; i < this.deckCapacity; i++) {
            int idx = rand.nextInt(referenceDeck.size());

            if (referenceDeck.get(idx) instanceof KartuKarakter) {
                this.deck.add(new KartuKarakter((KartuKarakter) referenceDeck.get(idx)));
            } else if (referenceDeck.get(idx) instanceof KartuSpellSwap) {
                this.deck.add(new KartuSpellSwap((KartuSpellSwap) referenceDeck.get(idx)));
            } else if (referenceDeck.get(idx) instanceof KartuSpellPotion) {
                this.deck.add(new KartuSpellPotion((KartuSpellPotion) referenceDeck.get(idx)));
            } else if (referenceDeck.get(idx) instanceof KartuSpellMorph) {
                this.deck.add(new KartuSpellMorph((KartuSpellMorph) referenceDeck.get(idx)));
            } else if (referenceDeck.get(idx) instanceof KartuSpellLvl) {
                this.deck.add(new KartuSpellLvl((KartuSpellLvl) referenceDeck.get(idx)));
            } else {
                this.deck.add(new Kartu(referenceDeck.get(idx)));
            }
        }

        // Inisiasi kartu di hand
        this.hand = new ArrayList<Kartu>();

        // player mengambil 3 kartu teratas
        for (int i = 0; i < 3; i++) {
            this.hand.add(this.deck.remove(0));
        }

        for (int index = 0; index < 5; index++) {
            this.board.add(new KartuKarakter("-", "-", "-", 0, 0, 0, 0, 0, 0, "-", 0));
        }
    }

    public void removeBoardCardAtIndex(int index) {
        if (index >= 0 && index < 5) {
            this.board.set(index, new KartuKarakter("-", "-", "-", 0, 0, 0, 0, 0, 0, "-", 0));
        }
    }

    // Getter & Setter
    public String getName() {
        return this.name;
    }

    public ArrayList<Kartu> getHandCard() {
        return this.hand;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0 && healthPoints <= 80) {
            this.healthPoints = healthPoints;
        }
    }

    public void reduceHP(int att) {
        this.healthPoints -= att;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    public void setMana(int mana) {
        if (mana <= 10) {
            this.mana = mana;
        } else {
            this.mana = 10;
        }
    }

    public void reduceAllSwap() {
        for (int i = 0; i < 5; i++) {
            boolean needSwap = board.get(i).reduceSwapDuration();
            if(needSwap){
                int health = board.get(i).getHealth();
                board.get(i).setHealth(board.get(i).getAttack());
                board.get(i).setAttack(health);
            }
            if (board.get(i).getHealth() <= 0) {
                removeBoardCardAtIndex(i);
            }
        }
    }

    public void reduceAllDuration(){
        for (int i = 0; i < 5; i++) {
            boolean needDelete = board.get(i).reduceDuration();
            if (needDelete) {
                removeBoardCardAtIndex(i);
            }
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

    public int getDeckCapacity() {
        return deckCapacity;
    }

    // Ambil kartu dari deck dan tambahkan ke hand
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
            if (deck.size() > 0) {
                deck.add(rand.nextInt(deck.size()), draw.remove(0));
            } else {
                deck.add(draw.remove(0));
            }
        }

        if (hand.size() < 5) {
            hand.add(pick);
        }
    }

    public void upMana(int idx) {
        if (!board.get(idx).getNama().equals("-")) {
            board.get(idx).naikExp(1);
            setMana(mana - 1);
        }
    }

    public void removeToBoard(Reference reference, int src) {
        // TO DO:
        // Keluarkan kartu yang ada di hand
        // Masukin ke dalam board, cek apakah di board masih ada slot atau ga
        if ((hand.size() - 1) > src && board.size() <= 6) {
            board.add(new KartuKarakter(reference.getKarakter(), hand.get(src).getNama()));
        }
    }

    public boolean boardIsEmpty() {
        for (KartuKarakter kartu : board) {
            if (!kartu.getNama().equals("-")) {
                return false;
            }
        }
        return true;
    }

    public void resetBoardAttack() {
        for (KartuKarakter kartu : board) {
            kartu.resetAttack();
        }
    }
}
