package textbasedengine;

/**
 * A decision about what state to go to that can be made by the player
 * @author Hillsoft
 */
public class StateDecision
{
    int nextState;
    String prompt;
    
    /**
     * Creates a new state decision
     * @param nState The id of the state this decision leads to
     * @param action The action that this state represents in the game
     */
    public StateDecision(int nState, String action)
    {
        nextState = nState;
        prompt = action;
    }
    
    /**
     * Displays the decision to the player, does not deal with input
     * @param key The readable name of the key the user uses to select this decision
     */
    public void pose(String key)
    {
        System.out.println("Press " + key + " to " + prompt);
    }
    
    /**
     * Gets the state this decision leads to
     * @return The state this decision leads to
     */
    public int getNextState()
    {
        return nextState;
    }
}
