
public class Action {
    
    ActionEnum actionTaken;
    ArrayList<Transition> transition;
    
    public Action(ActionEnum actionTaken) {
        this.actionTaken = actionTaken;
        transition = new ArrayList<Transition>;
    }
}