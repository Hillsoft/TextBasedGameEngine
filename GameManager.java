package textbasedengine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

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
    
    /**
     * Plays the game
     */
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
    
    static final String STATE = "state";
    static final String ID = "id";
    static final String TEXT = "text";
    static final String DECISION = "decision";
    static final String NEXT_STATE = "nState";
    public static GameManager loadFromFile(String path) throws FileNotFoundException, XMLStreamException
    {
        List<GameState> states= new ArrayList<>();
        
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        
        InputStream in = new FileInputStream(path);
        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
        
        GameState state = null;
        int curId = -1;
        List<StateDecision> decisions = null;
        
        while (eventReader.hasNext())
        {
            XMLEvent event = eventReader.nextEvent();
            
            if (event.isStartElement())
            {
                StartElement startElement = event.asStartElement();
                Iterator<Attribute> attributes = startElement.getAttributes();
                
                switch (startElement.getName().getLocalPart()) {
                    
                    case STATE:
                        state = new GameState();
                        decisions = new ArrayList<>();
                        
                        while (attributes.hasNext())
                        {
                            Attribute attr = attributes.next();
                            if (attr.getName().toString().equals(ID))
                            {
                                curId = Integer.parseInt(attr.getValue());
                            }
                        }
                        break;
                        
                    case TEXT:
                        event = eventReader.nextEvent();
                        state.setPrompt(event.asCharacters().getData());
                        break;
                        
                    case DECISION:
                        StateDecision dec = new StateDecision();
                        
                        while (attributes.hasNext())
                        {
                            Attribute attr = attributes.next();
                            if (attr.getName().toString().equals(NEXT_STATE))
                            {
                                dec.updateNextState(Integer.parseInt(attr.getValue()));
                            }
                        }
                        
                        event = eventReader.nextEvent();
                        dec.updatePrompt(event.asCharacters().getData());
                        
                        decisions.add(dec);
                        break;
                }
            }
            
            else if (event.isEndElement())
            {
                EndElement endElement = event.asEndElement();
                
                if (endElement.getName().getLocalPart().equals(STATE))
                {
                    state.setDecisions(decisions.toArray(new StateDecision[decisions.size()]));
                    
                    while (states.size() <= curId)
                    {
                        states.add(null);
                    }
                    
                    states.set(curId, state);
                }
            }
        }
        
        return new GameManager(states.toArray(new GameState[states.size()]));
    }
}
