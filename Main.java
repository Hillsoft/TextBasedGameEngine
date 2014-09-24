package textbasedengine;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;

/**
 * The main class, launches the application
 * Also contains some general purpose static functions
 * @author Hillsoft
 */
public class Main
{
    static Scanner inReader = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.print("Enter the name of the game you want to play: ");
        
        GameManager g = null;
        try
        {
            g = GameManager.loadFromFile(Main.getInput() + ".xml");
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Could not load file");
            System.exit(400);
        }
        catch (XMLStreamException ex)
        {
            System.err.println("XML is invalid");
            System.exit(400);
        }
        
        g.playGame();
    }
    
    /**
     * Gets input from the user (I didn't want to be using Scanner's all over the place)
     * @return The text entered by the user
     */
    public static String getInput()
    {
        return inReader.nextLine();
    }
}
