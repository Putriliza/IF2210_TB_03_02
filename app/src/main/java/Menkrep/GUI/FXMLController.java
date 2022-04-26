package Menkrep.GUI;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Kartu.KartuKarakter;
import Menkrep.Model.Kartu.KartuSpell;
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
        // Node node = (Node) event.getSource() ;
        // String data = (String) node.getUserData();
        // int value = Integer.parseInt(data);
        // System.out.println("Hellooooooooooooooooooo " + value);
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
        setBoardCard();
        setPlayerHealth(event);
        setJumlahDeck(event);
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

    @FXML
    ImageView gambar_kartu_hand_hover;
    @FXML
    Text nama_kartu_hand_hover;
    @FXML
    Text spek_kartu_hand_hover;
    @FXML
    Text deskripsi_kartu_hand_hover;

    @FXML
    Label jumlah_deck;

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

    @FXML
    private Text sword_hover_11;
    @FXML
    private Text sword_hover_12;
    @FXML
    private Text sword_hover_13;
    @FXML
    private Text sword_hover_14;
    @FXML
    private Text sword_hover_15;
    @FXML
    private Text sword_hover_21;
    @FXML
    private Text sword_hover_22;
    @FXML
    private Text sword_hover_23;
    @FXML
    private Text sword_hover_24;
    @FXML
    private Text sword_hover_25;

    @FXML
    private Text heart_hover_11;
    @FXML
    private Text heart_hover_12;
    @FXML
    private Text heart_hover_13;
    @FXML
    private Text heart_hover_14;
    @FXML
    private Text heart_hover_15;
    @FXML
    private Text heart_hover_21;
    @FXML
    private Text heart_hover_22;
    @FXML
    private Text heart_hover_23;
    @FXML
    private Text heart_hover_24;
    @FXML
    private Text heart_hover_25;

    @FXML
    private Text exp_11;
    @FXML
    private Text exp_12;
    @FXML
    private Text exp_13;
    @FXML
    private Text exp_14;
    @FXML
    private Text exp_15;
    @FXML
    private Text exp_21;
    @FXML
    private Text exp_22;
    @FXML
    private Text exp_23;
    @FXML
    private Text exp_24;
    @FXML
    private Text exp_25;

    @FXML
    private ImageView gambar_kartu_board_11;
    @FXML
    private ImageView gambar_kartu_board_12;
    @FXML
    private ImageView gambar_kartu_board_13;
    @FXML
    private ImageView gambar_kartu_board_14;
    @FXML
    private ImageView gambar_kartu_board_15;
    @FXML
    private ImageView gambar_kartu_board_21;
    @FXML
    private ImageView gambar_kartu_board_22;
    @FXML
    private ImageView gambar_kartu_board_23;
    @FXML
    private ImageView gambar_kartu_board_24;
    @FXML
    private ImageView gambar_kartu_board_25;

    @FXML
    private ImageView heart_icon_11;
    @FXML
    private ImageView heart_icon_12;
    @FXML
    private ImageView heart_icon_13;
    @FXML
    private ImageView heart_icon_14;
    @FXML
    private ImageView heart_icon_15;
    @FXML
    private ImageView heart_icon_21;
    @FXML
    private ImageView heart_icon_22;
    @FXML
    private ImageView heart_icon_23;
    @FXML
    private ImageView heart_icon_24;
    @FXML
    private ImageView heart_icon_25;

    @FXML
    private ImageView sword_icon_11;
    @FXML
    private ImageView sword_icon_12;
    @FXML
    private ImageView sword_icon_13;
    @FXML
    private ImageView sword_icon_14;
    @FXML
    private ImageView sword_icon_15;
    @FXML
    private ImageView sword_icon_21;
    @FXML
    private ImageView sword_icon_22;
    @FXML
    private ImageView sword_icon_23;
    @FXML
    private ImageView sword_icon_24;
    @FXML
    private ImageView sword_icon_25;



    public void setBoardCard(){
        Player playerOne = game.getPlayerOne();
        Player playerTwo = game.getPlayerTwo();

        System.out.println("KARTUU DII BOARDDD " + playerOne.getBoard().size());
        System.out.println("KARTUU DII BOARDDD " + playerTwo.getBoard().size());
        setBoardCardGUI(heart_hover_11, sword_hover_11, exp_11, gambar_kartu_board_11, playerOne.getBoard().get(0), heart_icon_11, sword_icon_11);
        setBoardCardGUI(heart_hover_12, sword_hover_12, exp_12, gambar_kartu_board_12, playerOne.getBoard().get(1), heart_icon_12, sword_icon_12);
        setBoardCardGUI(heart_hover_13, sword_hover_13, exp_13, gambar_kartu_board_13, playerOne.getBoard().get(2), heart_icon_13, sword_icon_13);
        setBoardCardGUI(heart_hover_14, sword_hover_14, exp_14, gambar_kartu_board_14, playerOne.getBoard().get(3), heart_icon_14, sword_icon_14);
        setBoardCardGUI(heart_hover_15, sword_hover_15, exp_15, gambar_kartu_board_15, playerOne.getBoard().get(4), heart_icon_15, sword_icon_15);

        setBoardCardGUI(heart_hover_21, sword_hover_21, exp_21, gambar_kartu_board_21, playerTwo.getBoard().get(0), heart_icon_21, sword_icon_21);
        setBoardCardGUI(heart_hover_22, sword_hover_22, exp_22, gambar_kartu_board_22, playerTwo.getBoard().get(1), heart_icon_22, sword_icon_22);
        setBoardCardGUI(heart_hover_23, sword_hover_23, exp_23, gambar_kartu_board_23, playerTwo.getBoard().get(2), heart_icon_23, sword_icon_23);
        setBoardCardGUI(heart_hover_24, sword_hover_24, exp_24, gambar_kartu_board_24, playerTwo.getBoard().get(3), heart_icon_24, sword_icon_24);
        setBoardCardGUI(heart_hover_25, sword_hover_25, exp_25, gambar_kartu_board_25, playerTwo.getBoard().get(4), heart_icon_25, sword_icon_25);
    }

    public void setBoardCardGUI(Text heart, Text sword, Text exp, ImageView gambar_kartu_board, KartuKarakter kartu, ImageView heart_icon, ImageView sword_icon){
        if (kartu.getNama().equals("-")) {
            heart.setVisible(false);
            sword.setVisible(false);
            exp.setVisible(false);
            gambar_kartu_board.setVisible(false);
            heart_icon.setVisible(false);
            sword_icon.setVisible(false);
        } else {
            heart.setVisible(true);
            sword.setVisible(true);
            exp.setVisible(true);
            gambar_kartu_board.setVisible(true);
            heart_icon.setVisible(true);
            sword_icon.setVisible(true);

            heart.setText(Integer.toString(kartu.getHealth()));
            sword.setText(Integer.toString(kartu.getAttack()));
            exp.setText("[" + Integer.toString(kartu.getExp()) + "/10] " + Integer.toString(kartu.getLevel()));
            String cwd = System.getProperty("user.dir"); 
            gambar_kartu_board.setImage(new Image(cwd + "/src/main/resources/Menkrep/" + kartu.getImgPath()));
        }
    }

    public void setPlayerHealth(ActionEvent event){
        bar_health_steve.setProgress(game.getPlayerOne().getHealthPoints()/80.0);
        bar_health_alex.setProgress(game.getPlayerTwo().getHealthPoints()/80.0);
    }

    @FXML
    public void handCardOnClick(ActionEvent event){
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }

        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        int idx = Integer.parseInt(data);

        if (game.getPhase() == Phase.Plan){
            // Cek apakah kartu card valid
            if (idx < player.getHandCard().size()){
                // Apabila kartu karakter
                if (player.getHandCard().get(idx) instanceof KartuKarakter){
                    System.out.println("KARAKTERRR");
                } 
                // Apabila kartu spell
                else if (player.getHandCard().get(idx) instanceof KartuSpell){
                    System.out.println("SPELLLL");
                }
            }
        }
        
    }

    public void setJumlahDeck(ActionEvent event){
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }
        jumlah_deck.setText(player.getDeck().size() + "/40");
    }

}