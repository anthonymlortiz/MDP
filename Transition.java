
public class Transition {
    private double probability
    MDPStates nextState;
    private double reward;
    
    public Transition(double probability, MDPStates nextState, double reward) {
        this.nextState = nextState;
        this.probability = probability;
        this.reward = reward;
    }
    
    public double getProbability() {
        return probability;
    }
    
    public void setProbability(double probability){
        this.probability = probability;
        
    }
    
    public double getReward(){
        return reward
    }
    
    public void setReward(double reward){
        this.reward = reward;
    }
}