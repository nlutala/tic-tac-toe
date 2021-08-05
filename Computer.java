import java.util.Random;

/**
 * The Computer class will be used to create the CPU opponent the user will play
 * against for the Tic-tac-Toe game.
 *
 * @author Nathan Lutala (GitHub: nlutala)
 * @version 05/08/2021
 */
public class Computer
{
    // instance variables
    private int position;

    /**
     * Constructor for objects of class Computer
     */
    public Computer() 
    {
        this.position = position;
    }
    
    /**
     * The Computer will choose an integer to randomly place their cross.
     */
    public void setPosition()
    {
        Random random = new Random();
        position = random.nextInt(9);
    }

    /**
     * @return int the position the Computer wants to place their cross
     */
    public int getPosition()
    {
        return position;
    }
}
