import java.util.ArrayList;

public class Action {
    
    ArrayList<MDPStates> transition;
    
    public Action(MDPStates goalState) {
        transition = new ArrayList<MDPStates>();
        transition.add(goalState);
    }
    
    public void addTransition(MDPStates goalState) {
        transition.add(goalState);
    }
    
}