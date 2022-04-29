package Menkrep.GUI;

import Menkrep.Model.Enum.DrawStatus;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Kartu.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;

public class DrawPageController {
    Game game = Game.getInstance();

    @FXML
    private Button draw_confirm_button;
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
    private int drawCardId;
    private ArrayList<Kartu> draw;

    public void initialize() {
        drawCardId = -1;
        draw = new ArrayList<>();
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
            setCard(draw.get(0), card2_image, card2_mana, card2_atk_hp);
        }
        if (draw.size() >= 2) {
            setCard(draw.get(1), card1_image, card1_mana, card1_atk_hp);
        }
        if (draw.size() == 3) {
            setCard(draw.get(2), card3_image, card3_mana, card3_atk_hp);
        }
    }

    private void setCard(Kartu card, ImageView image, Label mana, Label atk_hp) {
        String cwd = System.getProperty("user.dir");
        String dir = cwd + "/src/main/resources/Menkrep/";

        image.setImage(new Image(dir + card.getImgPath()));
        mana.setText("MANA " + card.getMana());

        String atkHpText = "";
        if (card.getTipe() == "POTION") {
            KartuSpellPotion potion = (KartuSpellPotion) card;
            atkHpText = String.format("ATK%+d/HP%+d", potion.getAttackModifier(), potion.getHealthModifier());
        } else if (card.getTipe() == "SWAP") {
            atkHpText = "ATK <-> HP";
        } else if (card.getTipe() == "LVL") {
            atkHpText = card.getNama();
        } else if (card.getTipe() == "MORPH") {
            atkHpText = "MORPH";
        } else {
            // karakter
            KartuKarakter karakter = (KartuKarakter) card;
            atkHpText = String.format("ATK %d/HP %d", karakter.getAttack(), karakter.getHealth());
        }
        atk_hp.setText(atkHpText);
    }

    public void drawCardOnClick(MouseEvent event) {
        event.consume();

        Pane card = (Pane) event.getSource();
        setBorderColor(card1, "black");
        setBorderColor(card2, "black");
        setBorderColor(card3, "black");
        drawCardId = Integer.parseInt(card.getId());

        if (drawCardId == 2) {
            setBorderColor(card1, "red");
        } else if (drawCardId == 1) {
            setBorderColor(card2, "red");
        } else if (drawCardId == 3) {
            setBorderColor(card3, "red");
       }
        draw_confirm_button.setDisable(false);
    }

    public void setBorderColor(Pane card, String color) {
        card.setStyle("-fx-border-color: " + color);
    }

    public void switchToMainPage(ActionEvent event) throws IOException {
        event.consume();

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public int getDrawCardId() {
        return drawCardId;
    }
}
