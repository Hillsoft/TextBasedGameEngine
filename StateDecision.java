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
     * Creates a blank decision
     */
    public StateDecision()
    {
        nextState = -1;
        prompt = "";
    }
    
    /**
     * Updates the decisions target
     * @param newNState The new state led to by this decision
     */
    public void updateNextState(int newNState)
    {
        nextState = newNState;
    }
    
    /**
     * Updates the prompt this decision displays to the user
     * @param newPrompt The new prompt
     */
    public void updatePrompt(String newPrompt)
    {
        prompt = newPrompt;
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
