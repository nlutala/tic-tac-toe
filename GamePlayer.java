import java.util.HashMap;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * The Home class what will be used to create a welcome-page GUI for the Tic-tac-Toe game.
 *
 * @author Nathan Lutala (GitHub: nlutala)
 * @version 26/07/2021
 */
public class GamePlayer extends Application
{
    // Instance variables
    private Pane grid;
    private Line h1;
    private Line h2;
    private Line v1;
    private Line v2;
    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b7;
    private Button b8;
    private HashMap<Integer,Boolean> occupied;

    /**
     * The start method is the main entry point for every JavaFX application. 
     * It is called after the init() method has returned and after 
     * the system is ready for the application to begin running.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage)
    {
        // Create a new BorderPane pane
        BorderPane pane = new BorderPane();

        // Creates the 3x3 grid to play in
        this.grid = new Pane();
        grid.setPrefSize(1000,1000);

        this.h1 = new Line();
        h1.setStartX(0.0f);
        h1.setEndX(500.0f);
        h1.relocate(150,200);

        this.h2 = new Line();
        h2.setStartX(0.0f);
        h2.setEndX(500.0f);
        h2.relocate(150,350);

        this.v1 = new Line();
        v1.setStartY(50.0f);
        v1.setEndY(500.0f);
        v1.relocate(320,50);

        this.v2 = new Line();
        v2.setStartY(50.0f);
        v2.setEndY(500.0f);
        v2.relocate(480,50);

        Button b0 = new Button();
        b0.setPrefSize(100,100);
        b0.relocate(185,75);
        Button b1 = new Button();
        b1.setPrefSize(100,100);
        b1.relocate(350,75);
        Button b2 = new Button();
        b2.setPrefSize(100,100);
        b2.relocate(515,75);
        Button b3 = new Button();
        b3.setPrefSize(100,100);
        b3.relocate(185,230);
        Button b4 = new Button();
        b4.setPrefSize(100,100);
        b4.relocate(350,230);
        Button b5 = new Button();
        b5.setPrefSize(100,100);
        b5.relocate(515,230);
        Button b6 = new Button();
        b6.setPrefSize(100,100);
        b6.relocate(185,390);
        Button b7 = new Button();
        b7.setPrefSize(100,100);
        b7.relocate(350,390);
        Button b8 = new Button();
        b8.setPrefSize(100,100);
        b8.relocate(515,390);

        grid.getChildren().addAll(h1, h2, v1, v2, b0, b1, b2, b3, b4, b5, b6, b7, b8);
        pane.setCenter(grid);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 800, 650);
        stage.setTitle("Tic-tac-Toe");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
        
        // Alert box showing which player starts first
        String info = displayStartingPlayer();
        Alert alert = new Alert(AlertType.NONE);
        alert.setAlertType(AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setContentText(info);
        alert.show();
    }
    
    /**
     * @return String a random starting player to start the game.
     */
    private String displayStartingPlayer()
    {
        String[] players = new String[] {"Player1", "Player2"};
        Random random = new Random();
        int number = random.nextInt(2);
        if (players[number].equals("Player1")) {
            return players[number] + " starts first. Press a square to place your circle.";
        } else {
            return players[number] + " starts first. Press a square to place your cross.";
        }
    }
}
