package Menkrep.GUI;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import java.nio.file.Path;

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
        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        int value = Integer.parseInt(data);
        System.out.println("Hellooooooooooooooooooo " + value);
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


        System.out.println("BANYAK KARTU HAND : " + player.getHandCard().size());

        setNamaLevelGambar(player, nama_kartu_hand_1, level_kartu_hand_1, gambar_kartu_hand_1, 0, 0 < player.getHandCard().size());
        setNamaLevelGambar(player, nama_kartu_hand_2, level_kartu_hand_2, gambar_kartu_hand_2, 1, 1 < player.getHandCard().size());
        setNamaLevelGambar(player, nama_kartu_hand_3, level_kartu_hand_3, gambar_kartu_hand_3, 2, 2 < player.getHandCard().size());
        setNamaLevelGambar(player, nama_kartu_hand_4, level_kartu_hand_4, gambar_kartu_hand_4, 3, 3 < player.getHandCard().size());
        setNamaLevelGambar(player, nama_kartu_hand_5, level_kartu_hand_5, gambar_kartu_hand_5, 4, 4 < player.getHandCard().size());

    }

    public void setNamaLevelGambar(Player player, Text nama, Text level, ImageView gambar, int index, boolean isExist) {
        if (!isExist) {
            // kosong
            nama.setText("");
            level.setText("");
            gambar.setImage(null);
        } else {
            nama.setText(player.getHandCard().get(index).getNama());
            if (player.getHandCard().get(index).getTipe() == "KARAKTER") {
                level.setText("Level " + player.getHandCard().get(index).getLevel());
            } else {
                level.setText("-");
            }
            String cwd = System.getProperty("user.dir");
            gambar.setImage(new Image(cwd + "/src/main/resources/Menkrep/" + player.getHandCard().get(index).getImgPath()));
        }
    }

    public void setPlayerHealth(ActionEvent event){
        bar_health_steve.setProgress(game.getPlayerOne().getHealthPoints()/80.0);
        bar_health_alex.setProgress(game.getPlayerTwo().getHealthPoints()/80.0);
    }
}