package Menkrep.GUI;

import Menkrep.Model.Enum.DrawStatus;
import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Kartu.Kartu;
import Menkrep.Model.Kartu.KartuKarakter;
import Menkrep.Model.Kartu.KartuSpell;
import Menkrep.Model.Kartu.KartuSpellLvl;
import Menkrep.Model.Kartu.KartuSpellPotion;
import Menkrep.Model.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;

public class FXMLController
{
    Game game = Game.getInstance();
    private Kartu currentHandCard;
    private KartuKarakter currentBoardCard;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
    }

    // ----------------------------------------------------------------------------------------------------
    // Fungsi untuk mengambil kartu pada draw phase.
    // Untuk sementara masuk ke draw stage dengan klik tombol draw
    @FXML private Button draw_confirm_button;
    @FXML private Pane card1;
    @FXML private ImageView card1_image;
    @FXML private Label card1_mana;
    @FXML private Label card1_atk_hp;
    @FXML private Pane card2;
    @FXML private ImageView card2_image;
    @FXML private Label card2_mana;
    @FXML private Label card2_atk_hp;
    @FXML private Pane card3;
    @FXML private ImageView card3_image;
    @FXML private Label card3_mana;
    @FXML private Label card3_atk_hp;
    @FXML private Button shuffle_button;
    private int draw_card_id;
    private ArrayList<Kartu> draw;

    public void switchToDrawPage(ActionEvent event) throws IOException {
        event.consume();

        if (!game.getHasDrawn()) {
            Stage stage;
            Parent root;
            stage = (Stage) button_draw.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("draw-page.fxml"));
            root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }

    public void updateDrawPage(ActionEvent event) {
        event.consume();

        if (game.getPlayerIndex() == 0) {
            DrawStatus status = game.getPlayerOne().generateDrawCard();
            if (status == DrawStatus.Success) {
                draw = game.getPlayerOne().getDrawCard();
            } else {
                // Deck empty
                draw = new ArrayList<>();
            }
        } else {
            DrawStatus status = game.getPlayerTwo().generateDrawCard();
            if (status == DrawStatus.Success) {
                draw = game.getPlayerTwo().getDrawCard();
            } else {
                // Deck empty
                draw = new ArrayList<>();
            }
        }
        String cwd = System.getProperty("user.dir");
        String dir = cwd + "/src/main/resources/Menkrep/";
        if (draw.size() >= 1) {
            card2_image.setImage(new Image(dir + draw.get(0).getImgPath()));
            card2_mana.setText(String.format("MANA %d", draw.get(0).getMana()));
            if (draw.get(0) instanceof KartuKarakter) {
                KartuKarakter kartu = (KartuKarakter) draw.get(0);
                card2_atk_hp.setText("ATK " + kartu.getAttack() + "/HP " + kartu.getHealth());
            } else {
                KartuSpell kartu = (KartuSpell) draw.get(0);
                card2_atk_hp.setText(kartu.getTipe());
            }
        }
        if (draw.size() >= 2) {
            card1_image.setImage(new Image(dir + draw.get(1).getImgPath()));
            card1_mana.setText("MANA " + draw.get(1).getMana());
            if (draw.get(1) instanceof KartuKarakter) {
                KartuKarakter kartu = (KartuKarakter) draw.get(1);
                card1_atk_hp.setText("ATK " + kartu.getAttack() + "/HP " + kartu.getHealth());
            } else {
                KartuSpell kartu = (KartuSpell) draw.get(1);
                card1_atk_hp.setText(kartu.getTipe());
            }
        }
        if (draw.size() == 3) {
            card3_image.setImage(new Image(dir + draw.get(2).getImgPath()));
            card3_mana.setText("MANA " + draw.get(2).getMana());
            if (draw.get(2) instanceof KartuKarakter) {
                KartuKarakter kartu = (KartuKarakter) draw.get(2);
                card3_atk_hp.setText("ATK " + kartu.getAttack() + "/HP " + kartu.getHealth());
            } else {
                KartuSpell kartu = (KartuSpell) draw.get(2);
                card3_atk_hp.setText(kartu.getTipe());
            }
        }
        shuffle_button.setDisable(true);
    }

    public void drawCardOnClick(MouseEvent event) {
        event.consume();

        if (shuffle_button.isDisable()) {
            Pane card = (Pane) event.getSource();
            setBorderColor(card1, "black");
            setBorderColor(card2, "black");
            setBorderColor(card3, "black");
            draw_card_id = Integer.parseInt(card.getId());

            if (draw_card_id == 2) {
                setBorderColor(card1, "red");
            } else if (draw_card_id == 1) {
                setBorderColor(card2, "red");
            } else if (draw_card_id == 3) {
                setBorderColor(card3, "red");
            }
            draw_confirm_button.setDisable(false);
        }
    }

    public void setBorderColor(Pane card, String color) {
        card.setStyle("-fx-border-color: " + color);
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        event.consume();
        game.setHasDrawn(true);
        if (game.getPlayerIndex() == 0) {
            game.getPlayerOne().pickDrawCard(draw_card_id-1);
        } else if (game.getPlayerIndex() == 1) {
            game.getPlayerTwo().pickDrawCard(draw_card_id-1);
        }

        stage = (Stage) draw_confirm_button.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("scene.fxml"));
        root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
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
    public void nextPhase(ActionEvent event){
        event.consume();
        game.nextPhase();
        if (game.getPhase() == Phase.Draw){
            button_draw.setDisable(false);
            button_end.setDisable(true);
            game.setHasDrawn(false);
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
    Text mana_kartu_hand_1;
    @FXML
    Text mana_kartu_hand_2;
    @FXML
    Text mana_kartu_hand_3;
    @FXML
    Text mana_kartu_hand_4;
    @FXML
    Text mana_kartu_hand_5;

    @FXML
    Text desc_kartu_hand_1;
    @FXML
    Text desc_kartu_hand_2;
    @FXML
    Text desc_kartu_hand_3;
    @FXML
    Text desc_kartu_hand_4;
    @FXML
    Text desc_kartu_hand_5;

    @FXML
    Label jumlah_deck;

    public void setHandCard(ActionEvent event){
        event.consume();

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

    public void setNamaLevelGambar(Player player, Text mana, Text desc, ImageView gambar, int index, boolean isExist) {
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
                    String atk;
                    String hp;
                    if (((KartuSpellPotion)player.getHandCard().get(index)).getAttackModifier() < 0) {
                        atk = "" + ((KartuSpellPotion)player.getHandCard().get(index)).getAttackModifier();
                    } else {
                        atk = "+" + ((KartuSpellPotion)player.getHandCard().get(index)).getAttackModifier();
                    }
                    if (((KartuSpellPotion)player.getHandCard().get(index)).getHealthModifier() < 0) {
                        hp = "" + ((KartuSpellPotion)player.getHandCard().get(index)).getHealthModifier();
                    } else {
                        hp = "+" + ((KartuSpellPotion)player.getHandCard().get(index)).getHealthModifier();
                    }

                    desc.setText("ATK" + atk + "/HP" + hp);
                } else if (player.getHandCard().get(index).getTipe() == "SWAP") {
                    desc.setText("ATK <-> HP");
                } else if (player.getHandCard().get(index).getTipe() == "LVL") {
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
            exp.setText("[" + Integer.toString(kartu.getExp()) + "/10] " + Integer.toString(kartu.getLevel()));
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

    // --------------------------------------------------------------------------------------------------------
    // Fungsi yang menangani setiap kartu di hand di click

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
                currentHandCard = player.getHandCard().get(idx);
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
            currentHandCard = player.getHandCard().get(idx);
            String cwd = System.getProperty("user.dir");
            gambar_kartu_hand_hover.setImage(new Image(cwd + "/src/main/resources/Menkrep/" + currentHandCard.getImgPath()));
            nama_kartu_hand_hover.setText(currentHandCard.getNama());
            stat_kartu_hand_hover.setText(currentHandCard.toString());
            deskripsi_kartu_hand_hover.setText("\""+ currentHandCard.getDeskripsi() + "\"");
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

        if (game.getPhase() == Phase.Plan){
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
                        this.currentHandCard = null;
                        player.setMana(player.getMana() - 1);
                        setBoardCard();
                        setHandCard(event);
                    } else {
                        System.out.println("MANA HABISSS");
                    }
                } else if (this.currentHandCard instanceof KartuSpell) {
                    if (player.getMana() > 0) {
                        player.getBoard().get(idx).addSpell((KartuSpell) this.currentHandCard);
                        player.getHandCard().remove(this.currentHandCard);
                        this.currentHandCard = null;
                        player.setMana(player.getMana() - 1);
                        setBoardCard();
                        setHandCard(event);
                    } else {
                        System.out.println("MANA HABISSS");
                    }
                }
            }
        }
    }

    // PUNYA LIZAAAAAAAAAAAAAAAAAAAAAAAAAAAA
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