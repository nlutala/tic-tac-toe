import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Home class what will be used to create a welcome-page GUI for the Tic-tac-Toe game.
 *
 * @author Nathan Lutala (GitHub: nlutala)
 * @version 26/07/2021
 */
public class Home extends Application
{
    private Stage stage;
    
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
        this.stage = stage;
        BorderPane pane = new BorderPane();
        
        Button playCPU = new Button("Play against the CPU");
        playCPU.setOnAction(this::playComputer);
        playCPU.setPrefSize(1000,1000);
        
        Button playPlayer = new Button("Play against another player");
        playPlayer.setOnAction(this::againstPlayer);
        playPlayer.setPrefSize(1000,1000);
        
        Button instructions = new Button("Instructions");
        instructions.setOnAction(this::showInstructions);
        instructions.setPrefSize(1000,1000);
        
        Button quit = new Button("Quit");
        quit.setOnAction(this::quitGame);
        quit.setPrefSize(1000,1000);
        
        VBox menu = new VBox(playCPU, playPlayer, instructions, quit);
        pane.setCenter(menu);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 800, 650);
        stage.setTitle("Tic-tac-Toe");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }
    
    /**
     * Play the game against the CPU.
     */
    private void playComputer(ActionEvent event)
    {
        GameCPU game = new GameCPU();
        game.start(stage);
    }
    
    /**
     * Play the game against another player.
     */
    private void againstPlayer(ActionEvent event)
    {
        GamePlayer game = new GamePlayer();
        game.start(stage);
    }
    
    /**
     * Show the instructions to the user.
     */
    private void showInstructions(ActionEvent event)
    {
        String instructions = "You and your opponent will take it in turns to place a symbol ";
        instructions += "on a 3x3 grid. To win, you need to place three circles next to each ";
        instructions += "to each other either vertically (straight down), horizontally ";
        instructions += "(straight across) or diagonally. Good luck!";
        Alert alert = new Alert(AlertType.NONE);
        alert.setAlertType(AlertType.INFORMATION);
        alert.setHeaderText("Instructions");
        alert.setContentText(instructions);
        alert.show();
    }

    /**
     * If the user presses "Quit", the program quits.
     */
    private void quitGame(ActionEvent event)
    {
        System.exit(0);
    }
}
