package textbasedengine;

/**
 * A 'room' in the text based adventure game
 * @author Hillsoft
 */
public class GameState
{
    String textPrompt;
    StateDecision[] decisions;
    
    /**
     * Initialises a new game state
     * @param textPrompt The text the describes this state to the user
     * @param decisions The options available to the user
     */
    public GameState(String textPrompt, StateDecision[] decisions)
    {
        this.textPrompt = textPrompt;
        this.decisions = decisions;
    }
    
    /**
     * Creates a blank game state
     */
    public GameState()
    {
        textPrompt = "";
        decisions = null;
    }
    
    /**
     * Updates the states prompt
     * @param newPrompt The new prompt to be displayed
     */
    public void setPrompt(String newPrompt)
    {
        textPrompt = newPrompt;
    }
    
    /**
     * Updates the states available decisions
     * @param newDecisions The new set of available decisions
     */
    public void setDecisions(StateDecision[] newDecisions)
    {
        decisions = newDecisions;
    }
    
    /**
     * Plays this particular game state for the user to respond to
     * @param endState Whether or not this is the game's win state
     * @return The id of the next state to be played
     */
    public int play(boolean endState)
    {
        System.out.println("\n" + textPrompt);
        
        int nextState = Integer.MIN_VALUE;
        
        if (!endState)
        {
            while (nextState == Integer.MIN_VALUE)
            {
                for (int i = 1; i <= decisions.length; i++)
                {
                    decisions[i - 1].pose(Integer.toString(i));
                }

                String in = Main.getInput();

                try
                {
                    nextState = decisions[Integer.parseInt(in) - 1].getNextState();
                }
                catch (NumberFormatException | ArrayIndexOutOfBoundsException ex)
                {
                    nextState = Integer.MIN_VALUE;
                    System.out.println("Invalid decision");
                }
            }
        }
        
        return nextState;
    }
}
