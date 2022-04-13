package Menkrep.Model.Board;

import java.util.ArrayList;

import Menkrep.Model.Kartu.KartuKarakter;

public class Board {
    // Attribute
    private boolean turn; // true berarti giliran pemain 1, false giliran pemain 2
    private ArrayList<KartuKarakter> playerOne;
    private ArrayList<KartuKarakter> playerTwo;

    public Board() {
        turn = true;
        playerOne = new ArrayList<KartuKarakter>();
        playerTwo = new ArrayList<KartuKarakter>();
    }

    public boolean getTurn() {
        return turn;
    }

    public void changeTurn() {
        turn = !turn;
    }

    public ArrayList<KartuKarakter> getCard(int id) {
        if (id == 1) {
            return playerOne;
        } else {
            return playerTwo;
        }

    }

     public KartuKarakter getCardPos(int id, int position) {
         if (id == 1) {
             return playerOne.get(position);
         } else if (id == 2) {
             return playerTwo.get(position);
         } else{
             return playerOne.get(position);
         }
     }

    public void setCard(int id, KartuKarakter kartu, int position) {
        if (position > 4) {
            // TODO throw error
        }

        if (id == 1) {
            if (playerOne.get(position) != null) {
                // TODO throw error
            }
            playerOne.add(position, kartu);
        } else if (id == 2) {
            if (playerTwo.get(position) != null) {
                // TODO throw error
            }
            playerTwo.add(position, kartu);
        }
    }
}
