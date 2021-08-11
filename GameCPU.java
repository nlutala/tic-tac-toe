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
 * The GameCPU class will be used to create the GUI for the Tic-tac-Toe game where a player
 * plays against the Computer.
 *
 * @author Nathan Lutala (GitHub: nlutala)
 * @version 26/07/2021
 */
public class GameCPU extends Application
{
    // Instance variables
    private Stage stage;
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
    private HashMap<Integer,String> occupied = new HashMap<>();
    private String startingPlayer;
    private String playerTurn;
    private Computer cpu = new Computer();

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
        this.stage = stage;

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
        b0.setOnAction(e -> placeToken(e, 0, b0, 185, 75));

        Button b1 = new Button();
        b1.setPrefSize(100,100);
        b1.relocate(350,75);
        b1.setOnAction(e -> placeToken(e, 1, b1, 350, 75));

        Button b2 = new Button();
        b2.setPrefSize(100,100);
        b2.relocate(515,75);
        b2.setOnAction(e -> placeToken(e, 2, b2, 515, 75));

        Button b3 = new Button();
        b3.setPrefSize(100,100);
        b3.relocate(185,230);
        b3.setOnAction(e -> placeToken(e, 3, b3, 185, 230));

        Button b4 = new Button();
        b4.setPrefSize(100,100);
        b4.relocate(350,230);
        b4.setOnAction(e -> placeToken(e, 4, b4, 350, 230));

        Button b5 = new Button();
        b5.setPrefSize(100,100);
        b5.relocate(515,230);
        b5.setOnAction(e -> placeToken(e, 5, b5, 515, 230));

        Button b6 = new Button();
        b6.setPrefSize(100,100);
        b6.relocate(185,390);
        b6.setOnAction(e -> placeToken(e, 6, b6, 185, 390));

        Button b7 = new Button();
        b7.setPrefSize(100,100);
        b7.relocate(350,390);
        b7.setOnAction(e -> placeToken(e, 7, b7, 350, 390));

        Button b8 = new Button();
        b8.setPrefSize(100,100);
        b8.relocate(515,390);
        b8.setOnAction(e -> placeToken(e, 8, b8, 515, 390));

        Button h = new Button("Go Back Home");
        h.setPrefSize(120,50);
        h.relocate(100,550);
        h.setOnAction(this::goHome);

        Button rg = new Button("Restart Game");
        rg.setPrefSize(90,50);
        rg.relocate(600,550);
        rg.setOnAction(this::resetGrid);

        canvas.getChildren().addAll(h1, h2, v1, v2, b0, b1, b2, b3, b4, b5, b6, b7, b8, h, rg);
        pane.setCenter(canvas);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 800, 650);
        scene.getStylesheets().add("tttstyles.css");
        stage.setTitle("Tic-tac-Toe");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();

        // Alert box showing which player starts first
        String info = displayStartingPlayer();
        if (playerTurn.equals("CPU")) {
            cpuMove();
        }
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
            return playerTurn + " starts first. The CPU is crosses (X).";
        }
    }

    /**
     * Prints which square was clicked.
     * @param ActionEvent when a button is clicked.
     * @param int the position which was clicked.
     * @param Button the button that was clicked.
     * @param int The x coordinate for the position to mark
     * @param int The y coordinate for the position to mark
     */
    private void placeToken(ActionEvent e, int position, Button button, int x, int y)
    {
        boolean isNotPlaced = true;
        while (isNotPlaced) {
            if (occupied.get(position) == null) {
                button = new Button("O");
                button.setPrefSize(100,100);
                button.relocate(x,y);
                canvas.getChildren().add(button);
                System.out.println(playerTurn + " places a token on position " + position);
                occupied.put(position,playerTurn);
                isNotPlaced = false;
            } else {
                return;
            }
        }
        checkWinner();
        setPlayerTurn();
    }

    /**
     * Places a cross where the Computer object (cpu) has chosen.
     * @param int the position the grid.
     */
    private void placeTokenCPU(int position)
    {
        boolean isNotPlaced = true;
        while (isNotPlaced) {
            if (occupied.get(position) == null) {
                Button button = new Button("X");
                button.setPrefSize(100,100);
                if (position == 0) {
                    button.relocate(185,75);
                } else if (position == 1) {
                    button.relocate(350,75);
                } else if (position == 2) {
                    button.relocate(515,75);
                } else if (position == 3) {
                    button.relocate(185,230);
                } else if (position == 4) {
                    button.relocate(350,230);
                } else if (position == 5) {
                    button.relocate(515,230);
                } else if (position == 6) {
                    button.relocate(185,390);
                } else if (position == 7) {
                    button.relocate(350,390);
                } else if (position == 8) {
                    button.relocate(515,390);
                }
                canvas.getChildren().add(button);
                System.out.println(playerTurn + " places a token on position " + position);
                occupied.put(position,playerTurn);
                isNotPlaced = false;
            } else {
                return;
            }
        }
        checkWinner();
        setPlayerTurn();
    }

    /**
     * Defines the move the Computer object (cpu) makes.
     */
    private void cpuMove()
    {
        cpu.setPosition();
        while (occupied.get(cpu.getPosition()) != null) {
            cpu.setPosition();
        }
        placeTokenCPU(cpu.getPosition());
    }

    /**
     * Update the playerTurn.
     */
    private void setPlayerTurn()
    {
        if (playerTurn.equals("Player1")) {
            playerTurn = "CPU";
            cpuMove();
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
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(3) == playerTurn && occupied.get(4) == playerTurn && occupied.get(5) == playerTurn) {
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(6) == playerTurn && occupied.get(7) == playerTurn && occupied.get(8) == playerTurn) {
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(0) == playerTurn && occupied.get(3) == playerTurn && occupied.get(6) == playerTurn) {
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(1) == playerTurn && occupied.get(4) == playerTurn && occupied.get(7) == playerTurn) {
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(2) == playerTurn && occupied.get(5) == playerTurn && occupied.get(8) == playerTurn) {
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(0) == playerTurn && occupied.get(4) == playerTurn && occupied.get(8) == playerTurn) {
            occupied = null;
            Alert alert = new Alert(AlertType.NONE);
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Winner!");
            alert.setContentText(playerTurn + " wins!");
            alert.show();
        } else if (occupied.get(6) == playerTurn && occupied.get(4) == playerTurn && occupied.get(2) == playerTurn) {
            occupied = null;
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

    /**
     * Clears the canvas to start a new game.
     * @param ActionEvent on the action that the "Go Back Home" button was clicked.
     */
    private void goHome(ActionEvent e)
    {
        Home home = new Home();
        home.start(stage);
    }

    /**
     * Clears the canvas to start a new game.
     * @param ActionEvent on the action that the "Restart Game" button was clicked.
     */
    private void resetGrid(ActionEvent e)
    {
        GameCPU game = new GameCPU();
        game.start(stage);
    }

    /**
     * @return HashMap<Integer,String> all the places that already have either a naught 
     * (O) or cross (X) in them.
     */
    public HashMap<Integer,String> getOccupied()
    {
        return occupied;
    }
}
