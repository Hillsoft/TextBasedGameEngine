package textbasedengine;

import java.util.Scanner;

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
        GameState[] states = new GameState[5];
        
        states[0] = new GameState("You have reached the end state.", null);
        states[1] = new GameState("This is the starting state.", new StateDecision[] { new StateDecision(2, "go to state 2"), new StateDecision(3, "go to state 3") });
        states[2] = new GameState("This is state 2.", new StateDecision[] { new StateDecision(2, "go to state 2."), new StateDecision(3, "go to state 3.") });
        states[3] = new GameState("This is state 3.", new StateDecision[] { new StateDecision(3, "go to state 3."), new StateDecision(4, "go to state 4.") });
        states[4] = new GameState("This is state 4.", new StateDecision[] { new StateDecision(2, "go to state 2."), new StateDecision(0, "win.") });
        
        GameManager g = new GameManager(states);
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
