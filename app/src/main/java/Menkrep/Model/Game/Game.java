package Menkrep.Model.Game;

import java.io.IOException;
import java.util.ArrayList;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Player.*;

public class Game {

    public static Game gameInstance = new Game();

    private int round;
    private int playerIndex;
    private Phase phase;
    private Player[] players;
    private boolean hasDrawn;
    // private ArrayList<Player> players;

    public static Game getInstance() {
        return gameInstance;
    }

    public Game(){
        this.round = 1;
        this.playerIndex = 0;
        this.phase = Phase.Draw;
        this.hasDrawn = false;
        this.players = new Player[2];
        try {
            Player playerOne = new Player("Steve");
            Player playerTwo = new Player("Alex");
            this.players[0] = playerOne;
            this.players[1] = playerTwo;
        } catch (IOException e) {
            //TODO: handle exception
        }
    }

    public int getManaCap(){
        return this.round<=10 ? this.round : 10;
    }

    public int getRound() {
        return this.round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getPlayerIndex(){
        return this.playerIndex;
    }

    public Phase getPhase(){
        return this.phase;
    }

    public void nextPhase() {
        if (this.phase == Phase.Draw){
            this.phase = Phase.Plan;
        } else if (this.phase == Phase.Plan){
            this.phase = Phase.Attack;
        }else if (this.phase == Phase.Attack){
            this.phase = Phase.End;
        }else if (this.phase == Phase.End){
            if(this.playerIndex==0){
                this.playerIndex=1;
            } else{
                this.playerIndex=0;
            }
            this.phase = Phase.Draw;
        }

    }

    public Player getPlayerOne(){
        return this.players[0];
    }

    public Player getPlayerTwo(){
        return this.players[1];
    }

    public boolean getHasDrawn() {
        return hasDrawn;
    }

    public void setHasDrawn(boolean hasDrawn) {
        this.hasDrawn = hasDrawn;
    }
}
