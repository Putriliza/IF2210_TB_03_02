/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Menkrep;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

//


public class App extends Application {
    public String getGreeting() {
        return "Hello world";
    }

//    public void start(Stage stage) {
//        Text text = new Text();
//        text.setText("Loading...");
//        text.setX(50);
//        text.setY(50);
//
//        Group root = new Group();
//        root.getChildren().add(text);
//
//        Scene scene = new Scene(root, 1280, 720);
//
//        stage.setTitle("Menkrep");
//        stage.setScene(scene);
//        stage.show();
//
//        text.setText("Menkrep card battle");
//    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource("scene.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("scene.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Menkrep");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
