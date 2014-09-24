package textbasedengine;

/**
 * The class that manages the game - controlling state transition, persistence, etc.
 * @author Hillsoft
 */
public class GameManager
{
    GameState[] states;
    int curState;
    
    /**
     * Creates a game
     * @param states The available game states
     */
    public GameManager(GameState[] states)
    {
        this.states = states;
        curState = 1;
    }
    
    public void playGame()
    {
        while (curState != 0)
        {
            if (curState >= states.length)
            {
                System.err.println("Internal error: state " + curState + " does not exist");
                System.exit(404);
            }
            
            curState = states[curState].play(false);
        }
        
        states[curState].play(true);
    }
}
