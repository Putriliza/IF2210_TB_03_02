package Menkrep.GUI;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLController
{
    Game game = Game.getInstance();
    @FXML
    private Label label;
    @FXML
    private Button button_draw;
    @FXML
    private Button button_plan;

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
        if(game.getPhase() == Phase.Draw){
            game.nextPhase();
            button_draw.setDisable(true);
            button_plan.setDisable(false);
        }
    }
}