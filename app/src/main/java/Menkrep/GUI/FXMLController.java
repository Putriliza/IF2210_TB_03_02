package Menkrep.GUI;

import Menkrep.Model.Enum.CAction;
import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Reference.Reference;
import Menkrep.Model.Kartu.*;
import Menkrep.Model.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import javafx.stage.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FXMLController
{
    Game game = Game.getInstance();
    private Kartu currentHandCard;
    private KartuKarakter currentBoardCard;
    private Parent root;
    int idxLeft = -1;
    int idxRight = -1;
    CAction currAction = CAction.Nothing;

    public void initialize()  {
        setBoardCard();
        setHandCard();
        setJumlahMana();
        setJumlahDeck();
    }

    // ----------------------------------------------------------------------------------------------------
    // Fungsi untuk mengambil kartu pada draw phase.
    // Untuk sementara masuk ke draw stage dengan klik tombol draw
    @FXML private AnchorPane mainPane;

    @FXML
    public void switchToDrawPage(ActionEvent event) throws IOException {
        event.consume();

        if (game.getHasDrawn()) return;
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }

        if (player.getHandCard().size() == 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Hand Card Full");
            alert.setHeaderText("Your card is full");
            alert.setContentText("Please remove one or more card before proceeding");
            alert.showAndWait();
            return;
        }

        if (player.getDeck().size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Card Left");
            alert.setHeaderText("No Card Left");
            alert.setContentText("You have no card left in the card deck");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("draw-page.fxml"));
        root = loader.load();
        DrawPageController drawPageController = loader.getController();

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Draw Phase");
        stage.setScene(new Scene(root));
        stage.showAndWait();

        int drawIndex = drawPageController.getDrawCardId()-1;
        if (drawIndex >= 0 && drawIndex < player.getDrawCard().size()) {
            game.setHasDrawn(true);
            player.pickDrawCard(drawIndex);
            button_draw.setStyle("-fx-text-fill: green");
            setHandCard();
            setJumlahDeck();
        }
    }

    // ----------------------------------------------------------------------------------------------------
    // Fungsi yang mengganti phase dan button phase pada GUI setiap
    // phase berubah dengan menekan button >>
    @FXML
    private Button button_draw;
    @FXML
    private Button button_plan;
    @FXML
    private Button button_attack;
    @FXML
    private Button button_end;
    @FXML
    private boolean giliranMain = false;

    @FXML
    public void cekGiliranActiveSpell() {
        if(!giliranMain) {
            giliranMain = true;
        } else if (giliranMain) {
            checkActive();
            giliranMain = false;
        }
    }
    @FXML
    public void nextPhase(ActionEvent event){
        event.consume();
        game.nextPhase();
        if (game.getPhase() == Phase.Draw){
            button_draw.setDisable(false);
            button_end.setDisable(true);
            button_delete.setDisable(false);
            game.setHasDrawn(false);
            cekGiliranActiveSpell();
            if (game.getPlayerOne().getDeck().size() == 0 && game.getPlayerIndex() == 0) {
                endGame(game.getPlayerTwo());
                return;
            }
            if (game.getPlayerTwo().getDeck().size() == 0 && game.getPlayerIndex() == 1) {
                endGame(game.getPlayerOne());
                return;
            }
        } else if (game.getPhase() == Phase.Plan){
            button_draw.setStyle("-fx-text-fill: black");
            button_plan.setDisable(false);
            button_draw.setDisable(true);
        } else if (game.getPhase() == Phase.Attack){
            resetBoardCardEffect();
            button_delete.setDisable(true);
            if (deleteMode) {
                button_delete.setText("Toggle Hand Card Deletion");
                button_delete.setStyle("-fx-text-fill: black");
                deleteMode = false;
            }
            button_attack.setDisable(false);
            button_plan.setDisable(true);
        } else if (game.getPhase() == Phase.End){
            button_end.setDisable(false);
            button_attack.setDisable(true);
        }
        setTurn(event);
        setHandCard();
        setBoardCard();
        setPlayerHealth(event);
        setJumlahDeck();
        idxLeft=-1;
        idxRight=-1;
        currentHandCard=null;
        currentBoardCard=null;
        setJumlahMana();
        game.resetBoardAttack();
    }

    // ----------------------------------------------------------------------------------------------------
    // Fungsi yang mengubah turn pada GUI setiap turn berubah
    @FXML
    private Label turn_count;
    @FXML
    private Label turn_player;

    public void setTurn(ActionEvent event){
        event.consume();

        turn_count.setText("Turn " + game.getRound());
        if(game.getPlayerIndex() == 0){
            turn_player.setText(game.getPlayerOne().getName());
        } else{
            turn_player.setText(game.getPlayerTwo().getName());
        }
    }

    // ----------------------------------------------------------------------------------------------------
    // Fungsi yang mengupdate health progress player pada GUI   
    @FXML
    private ProgressBar bar_health_steve;
    @FXML
    private ProgressBar bar_health_alex;
    
    public void setPlayerHealth(ActionEvent event){
        event.consume();
        bar_health_steve.setProgress(game.getPlayerOne().getHealthPoints()/80.0);
        bar_health_alex.setProgress(game.getPlayerTwo().getHealthPoints()/80.0);
    }

    // ----------------------------------------------------------------------------------------------------
    // Binding card to hand
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
    Label mana_kartu_hand_1;
    @FXML
    Label mana_kartu_hand_2;
    @FXML
    Label mana_kartu_hand_3;
    @FXML
    Label mana_kartu_hand_4;
    @FXML
    Label mana_kartu_hand_5;

    @FXML
    Label desc_kartu_hand_1;
    @FXML
    Label desc_kartu_hand_2;
    @FXML
    Label desc_kartu_hand_3;
    @FXML
    Label desc_kartu_hand_4;
    @FXML
    Label desc_kartu_hand_5;

    public void setHandCard(){

        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }

        setNamaLevelGambar(player, mana_kartu_hand_1, desc_kartu_hand_1, gambar_kartu_hand_1, 0, 0 < player.getHandCard().size());
        setNamaLevelGambar(player, mana_kartu_hand_2, desc_kartu_hand_2, gambar_kartu_hand_2, 1, 1 < player.getHandCard().size());
        setNamaLevelGambar(player, mana_kartu_hand_3, desc_kartu_hand_3, gambar_kartu_hand_3, 2, 2 < player.getHandCard().size());
        setNamaLevelGambar(player, mana_kartu_hand_4, desc_kartu_hand_4, gambar_kartu_hand_4, 3, 3 < player.getHandCard().size());
        setNamaLevelGambar(player, mana_kartu_hand_5, desc_kartu_hand_5, gambar_kartu_hand_5, 4, 4 < player.getHandCard().size());
    }

    public void setNamaLevelGambar(Player player, Label mana, Label desc, ImageView gambar, int index, boolean isExist) {
        if (!isExist) {
            // kosong
            mana.setText("");
            desc.setText("");
            gambar.setImage(null);
        } else {
            mana.setText("MANA " + player.getHandCard().get(index).getMana());
            if (player.getHandCard().get(index).getTipe() == "KARAKTER") {
                desc.setText("ATK " + ((KartuKarakter)player.getHandCard().get(index)).getAttack() + 
                                "/HP " + ((KartuKarakter)player.getHandCard().get(index)).getHealth());
            } else {
                if (player.getHandCard().get(index).getTipe() == "MORPH") {
                    desc.setText("MORPH");
                } else if (player.getHandCard().get(index).getTipe() == "POTION") {
                    int atk = ((KartuSpellPotion)player.getHandCard().get(index)).getAttackModifier();
                    int hp = ((KartuSpellPotion)player.getHandCard().get(index)).getHealthModifier();
                    desc.setText(String.format("ATK%+d/HP%+d", atk, hp));
                } else if (player.getHandCard().get(index).getTipe() == "SWAP") {
                    desc.setText("ATK <-> HP");
                } else if (player.getHandCard().get(index).getTipe() == "LVL") {
                    mana.setText("MANA X");
                    desc.setText(((KartuSpellLvl)player.getHandCard().get(index)).getNama());
                } else {
                    desc.setText("");
                }
            }

            String cwd = System.getProperty("user.dir");
            gambar.setImage(new Image(cwd + "/src/main/resources/Menkrep/" + player.getHandCard().get(index).getImgPath()));
        }
    }

    // ----------------------------------------------------------------------------------------------------
    // Fungsi mengupdate kartu board pada GUI
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

    // Fungsi helper untuk setBoardCard
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
            exp.setText((kartu.getExp()==0 ? "0" : Integer.toString(kartu.getExp())) +"/"+ kartu.getMaxExp() + " [" + kartu.getLevel()+ "]") ;
            String cwd = System.getProperty("user.dir"); 
            gambar_kartu_board.setImage(new Image(cwd + "/src/main/resources/Menkrep/" + kartu.getImgPath()));
        }
    }

    // --------------------------------------------------------------------------------------------------------
    // Fungsi untuk memberikan outline berwarna beda setiap ingin memindahkan kartu dari hand ke board
    @FXML
    private Button kartu_board_11;
    @FXML
    private Button kartu_board_12;
    @FXML
    private Button kartu_board_13;
    @FXML
    private Button kartu_board_14;
    @FXML
    private Button kartu_board_15;
    @FXML
    private Button kartu_board_21;
    @FXML
    private Button kartu_board_22;
    @FXML
    private Button kartu_board_23;
    @FXML
    private Button kartu_board_24;
    @FXML
    private Button kartu_board_25;
    public void setBoardCardEffect(boolean isPlaced, Button kartu_board){
        if (isPlaced) {
            kartu_board.setStyle("-fx-border-color: blue;");
        } else {
            kartu_board.setStyle("-fx-border-color: black;");
        }
    }

    public void resetBoardCardEffect(){
        if (game.getPlayerIndex() == 0) {
            System.out.println("HAHAAAAAAAAAAAAAAAAAAAAAAA PLAYER KIRI");
            kartu_board_11.setStyle("-fx-border-color: black;");
            kartu_board_12.setStyle("-fx-border-color: black;");
            kartu_board_13.setStyle("-fx-border-color: black;");
            kartu_board_14.setStyle("-fx-border-color: black;");
            kartu_board_15.setStyle("-fx-border-color: black;");
        } else {
            System.out.println("HAHAAAAAAAAAAAAAAAAAAAAAAA PLAYER KANANNN");
            kartu_board_21.setStyle("-fx-border-color: black;");
            kartu_board_22.setStyle("-fx-border-color: black;");
            kartu_board_23.setStyle("-fx-border-color: black;");
            kartu_board_24.setStyle("-fx-border-color: black;");
            kartu_board_25.setStyle("-fx-border-color: black;");
        }
    }

    // --------------------------------------------------------------------------------------------------------
    // Fungsi untuk menangani penghapusan kartu
    @FXML Button button_delete;
    public boolean deleteMode = false;

    public void deleteButtonOnClick(ActionEvent event) {
        event.consume();

        for (int i = 0; i < game.getPlayerOne().getDeck().size(); i++) {
            System.out.println(i + " " + game.getPlayerOne().getDeck().get(i).getNama());
        }
        if (deleteMode) {
            button_delete.setText("Toggle Hand Card Deletion");
            button_delete.setStyle("-fx-text-fill: black");
            deleteMode = false;
        } else {
            button_delete.setText("DELETION ENABLED");
            button_delete.setStyle("-fx-text-fill: red");
            deleteMode = true;
        }
    }

    // --------------------------------------------------------------------------------------------------------
    // Fungsi yang menangani setiap kartu di hand di-hover dan di-click

    @FXML
    ImageView gambar_kartu_hand_hover;
    @FXML
    Text nama_kartu_hand_hover;
    @FXML
    Text stat_kartu_hand_hover;
    @FXML
    Text deskripsi_kartu_hand_hover;

    @FXML
    Button kartu_hand_1;
    @FXML
    Button kartu_hand_2;
    @FXML
    Button kartu_hand_3;
    @FXML
    Button kartu_hand_4;
    @FXML
    Button kartu_hand_5;

    @FXML
    public void handCardOnHover(MouseEvent event) {
        event.consume();

        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }

        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        int idx = Integer.parseInt(data);
        // Cek apakah kartu card valid
        // Tampilkan detail handover card
        if (idx < player.getHandCard().size()){
            Kartu currentSelectd = player.getHandCard().get(idx);
            String cwd = System.getProperty("user.dir");
            gambar_kartu_hand_hover.setImage(new Image(cwd + "/src/main/resources/Menkrep/" + currentSelectd.getImgPath()));
            nama_kartu_hand_hover.setText(currentSelectd.getNama());
            stat_kartu_hand_hover.setText(currentSelectd.toString());
            deskripsi_kartu_hand_hover.setText("\""+ currentSelectd.getDeskripsi() + "\"");
            setHandCardEffect(idx);
        }
        else {
            gambar_kartu_hand_hover.setImage(null);
            nama_kartu_hand_hover.setText("");
            stat_kartu_hand_hover.setText("");
            deskripsi_kartu_hand_hover.setText("");
        }
    }

    @FXML
    public void handCardOnClick(ActionEvent event){
        event.consume();

        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }

        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        int idx = Integer.parseInt(data);

        // Penghapusan kartu
        if (deleteMode) {
            if (idx < player.getHandCard().size()) {
                player.getHandCard().remove(idx);
                setHandCard();
            }
            return;
        }

        if (game.getPhase() == Phase.Plan){
            // Cek apakah kartu card valid
            if (idx < player.getHandCard().size() && player.getMana() >= player.getHandCard().get(idx).getMana()){
                currentHandCard = player.getHandCard().get(idx);
                // Apabila kartu karakter
                if (currentHandCard instanceof KartuKarakter){
                    if (game.getPlayerIndex() == 0) {
                        setBoardCardEffect(player.getBoard().get(0).getNama().equals("-"), kartu_board_11);
                        setBoardCardEffect(player.getBoard().get(1).getNama().equals("-"), kartu_board_12);
                        setBoardCardEffect(player.getBoard().get(2).getNama().equals("-"), kartu_board_13);
                        setBoardCardEffect(player.getBoard().get(3).getNama().equals("-"), kartu_board_14);
                        setBoardCardEffect(player.getBoard().get(4).getNama().equals("-"), kartu_board_15);
                    } else{
                        setBoardCardEffect(player.getBoard().get(0).getNama().equals("-"), kartu_board_21);
                        setBoardCardEffect(player.getBoard().get(1).getNama().equals("-"), kartu_board_22);
                        setBoardCardEffect(player.getBoard().get(2).getNama().equals("-"), kartu_board_23);
                        setBoardCardEffect(player.getBoard().get(3).getNama().equals("-"), kartu_board_24);
                        setBoardCardEffect(player.getBoard().get(4).getNama().equals("-"), kartu_board_25);
                    }
                } 
                // Apabila kartu spell
                else if (currentHandCard instanceof KartuSpell){
                    if (game.getPlayerIndex() == 0) {
                        setBoardCardEffect(!player.getBoard().get(0).getNama().equals("-"), kartu_board_11);
                        setBoardCardEffect(!player.getBoard().get(1).getNama().equals("-"), kartu_board_12);
                        setBoardCardEffect(!player.getBoard().get(2).getNama().equals("-"), kartu_board_13);
                        setBoardCardEffect(!player.getBoard().get(3).getNama().equals("-"), kartu_board_14);
                        setBoardCardEffect(!player.getBoard().get(4).getNama().equals("-"), kartu_board_15);
                    } else{
                        setBoardCardEffect(!player.getBoard().get(0).getNama().equals("-"), kartu_board_21);
                        setBoardCardEffect(!player.getBoard().get(1).getNama().equals("-"), kartu_board_22);
                        setBoardCardEffect(!player.getBoard().get(2).getNama().equals("-"), kartu_board_23);
                        setBoardCardEffect(!player.getBoard().get(3).getNama().equals("-"), kartu_board_24);
                        setBoardCardEffect(!player.getBoard().get(4).getNama().equals("-"), kartu_board_25);
                    }
                }
            }
        }

        // Tampilkan detail handover card
        if (idx < player.getHandCard().size()){
            Kartu currentSelected = player.getHandCard().get(idx);
            String cwd = System.getProperty("user.dir");
            gambar_kartu_hand_hover.setImage(new Image(cwd + "/src/main/resources/Menkrep/" + currentSelected.getImgPath()));
            nama_kartu_hand_hover.setText(currentSelected.getNama());
            stat_kartu_hand_hover.setText(currentSelected.toString());
            deskripsi_kartu_hand_hover.setText("\""+ currentSelected.getDeskripsi() + "\"");
            setHandCardEffect(idx);
        }
        else {
            gambar_kartu_hand_hover.setImage(null);
            nama_kartu_hand_hover.setText("");
            stat_kartu_hand_hover.setText("");
            deskripsi_kartu_hand_hover.setText("");
        }
    }

    public void setHandCardEffect(int index){
        kartu_hand_1.setStyle("-fx-border-color: black;");
        kartu_hand_2.setStyle("-fx-border-color: black;");
        kartu_hand_3.setStyle("-fx-border-color: black;");
        kartu_hand_4.setStyle("-fx-border-color: black;");
        kartu_hand_5.setStyle("-fx-border-color: black;");
        
        if (index == 0) {
            kartu_hand_1.setStyle("-fx-border-color: yellow;");
        } else if (index == 1) {
            kartu_hand_2.setStyle("-fx-border-color: yellow;");
        } else if (index == 2) {
            kartu_hand_3.setStyle("-fx-border-color: yellow;");
        } else if (index == 3) {
            kartu_hand_4.setStyle("-fx-border-color: yellow;");
        } else if (index == 4) {
            kartu_hand_5.setStyle("-fx-border-color: yellow;");
        }
    }

    // --------------------------------------------------------------------------------------------------------
    // Fungsi yang menangani setiap kartu di board di click
    @FXML
    public void boardCardOnClick(ActionEvent event){
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }

        Node node = (Node) event.getSource() ;
        String data = (String) node.getUserData();
        int idx = Integer.parseInt(data);

        List<String> left = Arrays.asList("kartu_board_11", "kartu_board_12", "kartu_board_13", "kartu_board_14", "kartu_board_15");
        List<String> right = Arrays.asList("kartu_board_21", "kartu_board_22", "kartu_board_23", "kartu_board_24", "kartu_board_25");

        if (game.getPhase() == Phase.Plan){
            if(currAction == CAction.UpMana){
                if(game.getPlayerIndex()==0){
                    game.getPlayerOne().upMana(idx);
                }else{
                    game.getPlayerTwo().upMana(idx);
                }
                currAction = CAction.Nothing;
            } else{
                if (this.currentHandCard == null) {
                    System.out.println("BELUM MENCET KARTU APAPUN WOI");
                } else if (this.currentHandCard instanceof KartuKarakter && !player.getBoard().get(idx).getNama().equals("-")){
                    System.out.println("KARTU KARAKTER SUDAH TERISI");
                } else if (this.currentHandCard instanceof KartuSpell && player.getBoard().get(idx).getNama().equals("-")){
                    System.out.println("KARTU SPELL HANYA BISA DITAROH DI SPELL YANG TERISI");
                } else {
                    if (this.currentHandCard instanceof KartuKarakter) {
                        if (player.getMana() > 0) {
                            player.getBoard().set(idx, (KartuKarakter) this.currentHandCard);
                            player.getHandCard().remove(this.currentHandCard);
                            player.setMana(player.getMana() - this.currentHandCard.getMana());
                        } else {
                            System.out.println("MANA HABISSS");
                        }
                    } else if (this.currentHandCard instanceof KartuSpell) {
                        if (player.getMana() > 0) {
                            player.getBoard().get(idx).addSpell((KartuSpell) this.currentHandCard);
                            applySpell(idx, this.currentHandCard);
                            player.getHandCard().remove(this.currentHandCard);
                            player.setMana(player.getMana() - this.currentHandCard.getMana());
                        } else {
                            System.out.println("MANA HABISSS");
                        }
                    }
                }
            }
        } else if(game.getPhase() == Phase.Attack){
            Player playerOne = game.getPlayerOne();
            Player playerTwo = game.getPlayerTwo();
            if(idxLeft==-1 && idxRight==-1){
                if(game.getPlayerIndex()==0 && left.contains(node.getId()) && !playerOne.getBoard().get(idx).getNama().equals("-") && !playerOne.getBoard().get(idx).getDoneAttack()){
                    idxLeft = idx;
                } else if(game.getPlayerIndex()==1 && right.contains(node.getId()) && !playerTwo.getBoard().get(idx).getNama().equals("-") && !playerTwo.getBoard().get(idx).getDoneAttack()){
                    idxRight = idx;
                } else{
                    System.out.println("Error kartu");
                }
            } else{
                if(game.getPlayerIndex()==0 && playerTwo.boardIsEmpty()){
                    playerTwo.reduceHP(playerOne.getBoard().get(idxLeft).getAttack());
                    bar_health_steve.setProgress(game.getPlayerOne().getHealthPoints()/80.0);
                    bar_health_alex.setProgress(game.getPlayerTwo().getHealthPoints()/80.0);
                    if (playerTwo.getHealthPoints() <= 0) {
                        // HP lawan nol
                        endGame(playerOne);
                    }
                } else if(game.getPlayerIndex()==1 && playerOne.boardIsEmpty()){
                    playerOne.reduceHP(playerTwo.getBoard().get(idxRight).getAttack());

                    bar_health_steve.setProgress(game.getPlayerOne().getHealthPoints()/80.0);
                    bar_health_alex.setProgress(game.getPlayerTwo().getHealthPoints()/80.0);
                    if (playerOne.getHealthPoints() <= 0) {
                        // HP lawan nol
                        endGame(playerTwo);
                    }
                } else{
                    if(game.getPlayerIndex()==1 && left.contains(node.getId()) && !playerOne.getBoard().get(idx).getNama().equals("-")){
                        idxLeft = idx;
                        playerTwo.getBoard().get(idxRight).alrAttack();
                        game.attack(idxLeft, idxRight);
                    } else if(game.getPlayerIndex()==0 && right.contains(node.getId()) && !playerTwo.getBoard().get(idx).getNama().equals("-")){
                        idxRight = idx;
                        playerOne.getBoard().get(idxLeft).alrAttack();
                        game.attack(idxLeft, idxRight);
                    } else{
                        System.out.println("Salah kartu");
                    }
                }

                idxLeft=-1;
                idxRight=-1;
            }
        }
        this.currentHandCard = null;
        setJumlahMana();
        setBoardCard();
        setHandCard();
    }

    
    // -------------------------------------------------------------------------------------
    // Fungsi untuk bind jumlah deck

    @FXML Label jumlah_deck;

    public void setJumlahDeck(){
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }
        jumlah_deck.setText(player.getDeck().size() + "/" + player.getDeckCapacity());
    }

    // -------------------------------------------------------------------------------------
    // Fungsi untuk bind jumlah mana

    @FXML
    Label jumlah_mana;

    public void setJumlahMana(){
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }
        jumlah_mana.setText(player.getMana() + "/" + game.getManaCap());
    }

    public void upExp(ActionEvent event){
        event.consume();
        if((game.getPlayerIndex()==0 && game.getPlayerOne().getMana()>0) || (game.getPlayerIndex()==1 && game.getPlayerTwo().getMana()>0)){
            currAction = CAction.UpMana;
        }
    }

    // -------------------------------------------------------------------------------------

    // Fungsi untuk aplikasi spell

    public void checkActive() {
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }
        System.out.println("Utama");
        for(int i = 0; i < 5; i++) {
            for(KartuSpell kartu: player.getBoard().get(i).getActiveSpells()) {
                if(kartu instanceof KartuSpellPotion) {
                    ((KartuSpellPotion) kartu).setDuration(((KartuSpellPotion) kartu).getDuration()-1);
                    if(((KartuSpellPotion) kartu).getDuration() == 0) {
                        player.getBoard().get(i).setHealthTemp(0);
                        player.getBoard().get(i).setAttackTemp(0);
                    }
                }
                System.out.println("Masukkk");
                if (kartu instanceof KartuSpellSwap) {
                    ((KartuSpellSwap) kartu).setDuration(((KartuSpellSwap) kartu).getDuration() - 1);
                    System.out.println(((KartuSpellSwap) kartu).getDuration());
                    if(((KartuSpellSwap) kartu).getDuration() == 0) {
                        player.getBoard().get(i).getActiveSpells().remove(kartu);
                        int health_temp = player.getBoard().get(i).getHealth();
                        int attack_temp = player.getBoard().get(i).getAttack();

                        player.getBoard().get(i).setHealth(attack_temp);
                        player.getBoard().get(i).setAttack(health_temp);
                    }
                }
            }
        }
    }

    public void applySpell(int idx, Kartu kartu) {
        Player player;
        if (game.getPlayerIndex() == 0) {
            player = game.getPlayerOne();
        } else {
            player = game.getPlayerTwo();
        }
        if (this.currentHandCard instanceof KartuSpellLvl && player.getMana()>=(int)Math.ceil(player.getBoard().get(idx).getLevel()/2.0)) {
            ((KartuSpellLvl) this.currentHandCard).lvl(player.getBoard().get(idx));
        } else if (kartu instanceof KartuSpellPotion) {
            if (((KartuSpellPotion) this.currentHandCard).getDuration() == 0) {
                player.getBoard().get(idx).setHealth(player.getBoard().get(idx).getHealth() + ((KartuSpellPotion) this.currentHandCard).getHealthModifier());
                player.getBoard().get(idx).setAttack(player.getBoard().get(idx).getAttack() + ((KartuSpellPotion) this.currentHandCard).getAttackModifier());
            } else {
                player.getBoard().get(idx).setAttackTemp(player.getBoard().get(idx).getAttackTemp() + ((KartuSpellPotion) this.currentHandCard).getAttackModifier());
                player.getBoard().get(idx).setHealthTemp(player.getBoard().get(idx).getHealthTemp() + ((KartuSpellPotion) this.currentHandCard).getHealthModifier());
                System.out.println(((KartuSpellPotion) this.currentHandCard).getAttackModifier());
                System.out.println(((KartuSpellPotion) this.currentHandCard).getHealthModifier());
            }
        } else if (kartu instanceof KartuSpellSwap) {
            ((KartuSpellSwap) this.currentHandCard).swap(player.getBoard().get(idx));
            if (player.getBoard().get(idx).getHealth() == 0) {
                player.removeBoardCardAtIndex(idx);
            }
        } else if (kartu instanceof KartuSpellMorph) {
            int id = ((KartuSpellMorph) this.currentHandCard).getTargetId();
            Reference ref = Reference.getInstance();
            String target_name = ref.getKarakter().get(id-1)[1];
            KartuKarakter kartu_karakter = new KartuKarakter(ref.getKarakter(), target_name);
            ((KartuSpellMorph) this.currentHandCard).morph(player.getBoard().get(idx), kartu_karakter);
        }
    }
    // Kondisi akhir game
    public void endGame(Player player) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("end-scene.fxml"));
            root = loader.load();
            EndSceneController endSceneController = loader.getController();

            endSceneController.setPlayer(player);


            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainPane.getScene().getWindow());
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Game Finished");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            Window mainStage = mainPane.getScene().getWindow();
            mainStage.hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}