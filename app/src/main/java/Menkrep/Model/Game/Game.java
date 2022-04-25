package Menkrep.Model.Game;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Player.*;

public class Game {
    public static Game gameInstance = new Game();

    private int round;
    private int playerIndex;
    private Phase phase;
    private Player[] players;

    public static Game getInstance() {
        return gameInstance;
    }

    public Game() {
        this.round = 1;
        this.playerIndex = 0;
        this.phase = Phase.Draw;
        this.players = new Player[2];
    }

    public int getManaCap(){
        return this.round<=10 ? this.round : 10;
    }

    public int getRound() {
        return this.round;
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
            this.phase = Phase.Draw;
            if(this.playerIndex==0){
                this.playerIndex=1;
            } else{
                this.playerIndex=0;
            }
        }

    }

    public Player getPlayerOne(){
        return this.players[0];
    }

    public Player getPlayerTwo(){
        return this.players[1];
    }
}
