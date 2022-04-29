package Menkrep.GUI;

import Menkrep.Model.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EndSceneController {
    @FXML Label playerNameLabel;
    @FXML ImageView winningPlayerImage;
    @FXML Button closeButton;

    public void initialize() { }

    public void setPlayer(Player player) {
        this.playerNameLabel.setText(player.getName());

        String cwd = System.getProperty("user.dir");
        if (player.getName() == "Alex") {
            winningPlayerImage.setImage(new Image(cwd + "/src/main/resources/Menkrep/card/image/character/alex.jpg"));
        } else {
            winningPlayerImage.setImage(new Image(cwd + "/src/main/resources/Menkrep/card/image/character/steve.jpg"));
        }
    }

    public void onCloseButtonClicked(ActionEvent event) {
        event.consume();

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
