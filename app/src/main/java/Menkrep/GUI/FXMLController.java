package Menkrep.GUI;

import Menkrep.Model.Enum.Phase;
import Menkrep.Model.Game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;

public class FXMLController
{
    Game game = Game.getInstance();
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
        if (game.getPhase() == Phase.Draw){
            button_draw.setDisable(true);
            button_plan.setDisable(false);
        } else if (game.getPhase() == Phase.Plan){
            button_plan.setDisable(true);
            button_attack.setDisable(false);
        } else if (game.getPhase() == Phase.Attack){
            button_attack.setDisable(true);
            button_end.setDisable(false);
        } else if (game.getPhase() == Phase.End){
            button_end.setDisable(true);
            button_draw.setDisable(false);
        }
        game.nextPhase();
        // setPlayerHealth(event);
        // setTurn(event);
    }

    public void setTurn(ActionEvent event){
        event.consume();

        // game.setRound(game.getRound()+1);
        // game.setPlayerIndex();

        turn_count.setText("Turn " + game.getRound());
        if(game.getPlayerIndex() == 0){
            turn_player.setText("Player 1");
        } else{
            turn_player.setText("Player 2");
        }
    }

    public void setPlayerHealth(ActionEvent event){
        System.out.println("TESSS");
        // bar_health_steve.setProgress(game.getPlayerOne().getHealthPoints()/100.0);
        // bar_health_alex.setProgress(game.getPlayerTwo().getHealthPoints()/100.0);
    }
}