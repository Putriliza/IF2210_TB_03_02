package Menkrep.GUI;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.util.List;
import java.util.ArrayList;

public class FXMLController
{
    Game game = new Game();
    @FXML
    private Label label;
    @FXML
    private Button button_draw;
    @FXML
    private Button button_plan;
    @FXML
    private Button button_attack;
    @FXML
    private Button button_end;

    @FXML
    private ProgressBar bar_health_steve;
    @FXML
    private ProgressBar bar_health_alex;

    @FXML
    private Label turn_count;
    @FXML
    private Label turn_player;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");


//        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");
    }

    @FXML
    public void print(ActionEvent event){
        event.consume();
        System.out.println("Hellooooooooooooooooooo");
    }

    @FXML
    public void nextPhase(ActionEvent event){
        event.consume();
        game.nextPhase();
        if (game.getPhase() == Phase.Draw){
            button_draw.setDisable(false);
            button_end.setDisable(true);
            game.setRound(game.getRound() + 1);
        } else if (game.getPhase() == Phase.Plan){
            button_plan.setDisable(false);
            button_draw.setDisable(true);
        } else if (game.getPhase() == Phase.Attack){
            button_attack.setDisable(false);
            button_plan.setDisable(true);
        } else if (game.getPhase() == Phase.End){
            button_end.setDisable(false);
            button_attack.setDisable(true);
        }
        setTurn(event);
        setHandCard(event);
        setPlayerHealth(event);
    }

    public void setTurn(ActionEvent event){
        event.consume();

        turn_count.setText("Turn " + game.getRound());
        if(game.getPlayerIndex() == 0){
            turn_player.setText(game.getPlayerOne().getName());
        } else{
            turn_player.setText(game.getPlayerTwo().getName());
        }
    }

    @FXML
    ImageView gambar_kartu_hand_1;
    @FXML
    ImageView gambar_kartu_hand_2;
    @FXML
    ImageView gambar_kartu_hand_3;
    @FXML
    ImageView gambar_kartu_hand_4;
    @FXML
    ImageView gambar_kartu_hand_5;

    @FXML
    Text nama_kartu_hand_1;
    @FXML
    Text nama_kartu_hand_2;
    @FXML
    Text nama_kartu_hand_3;
    @FXML
    Text nama_kartu_hand_4;
    @FXML
    Text nama_kartu_hand_5;
    


    @FXML
    Text level_kartu_hand_1;
    @FXML
    Text level_kartu_hand_2;
    @FXML
    Text level_kartu_hand_3;
    @FXML
    Text level_kartu_hand_4;
    @FXML
    Text level_kartu_hand_5;

    // List<Text> nama_kartu_hands = new ArrayList<>(List.of(nama_kartu_hand_1, nama_kartu_hand_2, nama_kartu_hand_3, nama_kartu_hand_4, nama_kartu_hand_5));
    // List<Text> nama_kartu_hands = new ArrayList<Text>();
    // nama_kartu_hands.add(nama_kartu_hand_1);

    public void setHandCard(ActionEvent event){
        event.consume();
        System.out.println("DI HANDDD CARD");

        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }
        // System.out.println(player.getHandCard().get(0).getImgPath());
        // gambar_kartu_hand_1.setImage(new Image("@" + player.getHandCard().get(0).getImgPath()));

        int banyak_kartu_hand = player.getHandCard().size();
        System.out.println("BANYAK KARTU HAND : " + banyak_kartu_hand);


        // MAAP NGESPAMM GAISS, DIBIKIN GENERAL ARRAYLIST BLOM BISA :(
        if (banyak_kartu_hand == 5) {
            nama_kartu_hand_1.setText(player.getHandCard().get(0).getNama());
            nama_kartu_hand_2.setText(player.getHandCard().get(1).getNama());
            nama_kartu_hand_3.setText(player.getHandCard().get(2).getNama());
            nama_kartu_hand_4.setText(player.getHandCard().get(3).getNama());
            nama_kartu_hand_5.setText(player.getHandCard().get(4).getNama());
        } else if (banyak_kartu_hand == 4) {
            nama_kartu_hand_1.setText(player.getHandCard().get(0).getNama());
            nama_kartu_hand_2.setText(player.getHandCard().get(1).getNama());
            nama_kartu_hand_3.setText(player.getHandCard().get(2).getNama());
            nama_kartu_hand_4.setText(player.getHandCard().get(3).getNama());
            nama_kartu_hand_5.setText("");
        } else if (banyak_kartu_hand == 3) {
            nama_kartu_hand_1.setText(player.getHandCard().get(0).getNama());
            nama_kartu_hand_2.setText(player.getHandCard().get(1).getNama());
            nama_kartu_hand_3.setText(player.getHandCard().get(2).getNama());
            nama_kartu_hand_4.setText("");
            nama_kartu_hand_5.setText("");
        } else if (banyak_kartu_hand == 2) {
            nama_kartu_hand_1.setText(player.getHandCard().get(0).getNama());
            nama_kartu_hand_2.setText(player.getHandCard().get(1).getNama());
            nama_kartu_hand_3.setText("");
            nama_kartu_hand_4.setText("");
            nama_kartu_hand_5.setText("");
        } else if (banyak_kartu_hand == 1) {
            nama_kartu_hand_1.setText(player.getHandCard().get(0).getNama());
            nama_kartu_hand_2.setText("");
            nama_kartu_hand_3.setText("");
            nama_kartu_hand_4.setText("");
            nama_kartu_hand_5.setText("");
        } else {
            nama_kartu_hand_1.setText("");
            nama_kartu_hand_2.setText("");
            nama_kartu_hand_3.setText("");
            nama_kartu_hand_4.setText("");
            nama_kartu_hand_5.setText("");
        }

        if (player.getHandCard().get(0).getTipe() == "KARAKTER") {
            nama_kartu_hand_1.setText("Level " + player.getHandCard().get(0).getLevel());
        } else {
            nama_kartu_hand_1.setText("-");
        }

        if (player.getHandCard().get(1).getTipe() == "KARAKTER") {
            nama_kartu_hand_2.setText("Level " + player.getHandCard().get(1).getLevel());
        } else {
            nama_kartu_hand_2.setText("-");
        }

        if (player.getHandCard().get(2).getTipe() == "KARAKTER") {
            nama_kartu_hand_3.setText("Level " + player.getHandCard().get(2).getLevel());
        } else {
            nama_kartu_hand_3.setText("-");
        }

        if (player.getHandCard().get(3).getTipe() == "KARAKTER") {
            nama_kartu_hand_4.setText("Level " + player.getHandCard().get(3).getLevel());
        } else {
            nama_kartu_hand_4.setText("-");
        }

        if (player.getHandCard().get(4).getTipe() == "KARAKTER") {
            nama_kartu_hand_5.setText("Level " + player.getHandCard().get(4).getLevel());
        } else {
            nama_kartu_hand_5.setText("-");
        }
        



    }

    public void setPlayerHealth(ActionEvent event){
        bar_health_steve.setProgress(game.getPlayerOne().getHealthPoints()/80.0);
        bar_health_alex.setProgress(game.getPlayerTwo().getHealthPoints()/80.0);
    }
}