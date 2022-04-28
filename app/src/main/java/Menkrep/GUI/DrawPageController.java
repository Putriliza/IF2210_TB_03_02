package Menkrep.GUI;

import Menkrep.Model.Enum.DrawStatus;
import Menkrep.Model.Game.Game;
import Menkrep.Model.Kartu.Kartu;
import Menkrep.Model.Kartu.KartuKarakter;
import Menkrep.Model.Kartu.KartuSpell;
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
