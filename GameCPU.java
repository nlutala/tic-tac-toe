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
public class GameCPU extends Application
{
    // Instance variables
    private Pane canvas;
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
    // private Grid grid; An abstract representation of the Tic-tac-Toe grid
    private HashMap<Integer,String> occupied = new HashMap<>();
    private String startingPlayer;
    private String playerTurn;
    // private Computer computer;
    // private User player;

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
        this.canvas = new Pane();
        canvas.setPrefSize(1000,1000);

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
        b0.setOnAction(e -> placeToken(e, 0));

        Button b1 = new Button();
        b1.setPrefSize(100,100);
        b1.relocate(350,75);
        b1.setOnAction(e -> placeToken(e, 1));

        Button b2 = new Button();
        b2.setPrefSize(100,100);
        b2.relocate(515,75);
        b2.setOnAction(e -> placeToken(e, 2));

        Button b3 = new Button();
        b3.setPrefSize(100,100);
        b3.relocate(185,230);
        b3.setOnAction(e -> placeToken(e, 3));

        Button b4 = new Button();
        b4.setPrefSize(100,100);
        b4.relocate(350,230);
        b4.setOnAction(e -> placeToken(e, 4));

        Button b5 = new Button();
        b5.setPrefSize(100,100);
        b5.relocate(515,230);
        b5.setOnAction(e -> placeToken(e, 5));

        Button b6 = new Button();
        b6.setPrefSize(100,100);
        b6.relocate(185,390);
        b6.setOnAction(e -> placeToken(e, 6));

        Button b7 = new Button();
        b7.setPrefSize(100,100);
        b7.relocate(350,390);
        b7.setOnAction(e -> placeToken(e, 7));

        Button b8 = new Button();
        b8.setPrefSize(100,100);
        b8.relocate(515,390);
        b8.setOnAction(e -> placeToken(e, 8));

        canvas.getChildren().addAll(h1, h2, v1, v2, b0, b1, b2, b3, b4, b5, b6, b7, b8);
        pane.setCenter(canvas);

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
        String[] players = new String[] {"Player1", "CPU"};
        Random random = new Random();
        int number = random.nextInt(2);

        if (players[number].equals("Player1")) {
            playerTurn = "Player1";
            return playerTurn + " starts first. Press a square to place your cicle.";
        } else {
            playerTurn = "CPU";
            return playerTurn + " starts first";
        }
    }

    /**
     * Prints which square was clicked.
     * @param ActionEvent when a button is clicked.
     * @param int the position which was clicked.
     */
    private void placeToken(ActionEvent e, int position)
    {
        boolean isNotPlaced = true;

        while (isNotPlaced) {
            if (occupied.get(position) == null) {
                occupied.put(position,playerTurn);
                System.out.println(playerTurn + " places a token on position " + position);
                isNotPlaced = false;
            } else {
                // Alert box showing which player starts first
                Alert alert = new Alert(AlertType.NONE);
                alert.setAlertType(AlertType.INFORMATION);
                alert.setHeaderText("Error!");
                alert.setContentText("That place is already ooccupied. Try another place");
                alert.show();
                return;
            }
        }
        checkWinner();
        setPlayerTurn();
    }

    /**
     * Update the playerTurn.
     */
    private void setPlayerTurn()
    {
        if (playerTurn.equals("Player1")) {
            playerTurn = "CPU";
        } else {
            playerTurn = "Player1";
        }
    }

    /**
     * Shows the player that has won or an empty string if no one has won yet.
     */
    private void checkWinner()
    {
        if (occupied.get(0) == playerTurn && occupied.get(1) == playerTurn && occupied.get(2) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(3) == playerTurn && occupied.get(4) == playerTurn && occupied.get(5) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(6) == playerTurn && occupied.get(7) == playerTurn && occupied.get(8) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(0) == playerTurn && occupied.get(3) == playerTurn && occupied.get(6) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(1) == playerTurn && occupied.get(4) == playerTurn && occupied.get(7) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(2) == playerTurn && occupied.get(5) == playerTurn && occupied.get(8) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(0) == playerTurn && occupied.get(4) == playerTurn && occupied.get(8) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(6) == playerTurn && occupied.get(4) == playerTurn && occupied.get(2) == playerTurn) {
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.size() == 9) {
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Draw!");
            alert.setContentText("It's a draw! Play again.");
            alert.show();
        }
    }
}
