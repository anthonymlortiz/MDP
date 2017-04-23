import java.util.ArrayList;

public class MDPStates {
    
    ActionEnum actionTaken;
    private int index;
    private int reward;
    private double probability;
    private double utility;
    private ArrayList<Action> action;
    
    public MDPStates(int reward, double probability, int index, double utility) {
        this.reward = reward;
        this.probability = probability;
        this.index = index;
        this.utility = utility;
        action = new ArrayList<Action>();
    }
    
    public MDPStates(int reward, double probability, int index) {
        this.reward = reward;
        this.probability = probability;
        this.index = index;
        action = new ArrayList<Action>();
    }
    
    public int getIndex() {
        return index;
    }
    
    public Action getAction(int index) {
        return action.get(index);
    }
    
    public int getReward() {
        return reward;
    }
    
    public double getProbability() {
        return probability;
    }
    
    public double getUtility() {
        return utility;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setReward(int reward) {
        this.reward = reward;
    } 
    
    public void setProbability(double probability) {
        this.probability = probability;
    }
    
    public void setUtility(double utility) {
        this.utility = utility;
    }
    
    public void addAction(MDPStates goalState) {
        action.add(new Action(goalState));
    }
    
    public void addActionToIndex(int index, MDPStates goalState) {
        Action transition = action.get(index);
        transition.addTransition(goalState);
    }
    
}