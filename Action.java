import java.util.ArrayList;

public class Action {
    
    ArrayList<Transition> transitions;
    int index;
    int actionCode;
    
    public Action(Transition goalState) {
        transition = new ArrayList<Transition>();
        transition.add(goalState);
    }
    
    public void addTransition(Transition goalState) {
        transition.add(goalState);
    }
    
}