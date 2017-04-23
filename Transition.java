
public class Transition {
    
    double probability;
    StudentState nextState;
    
    public Transition(double probability, StudentState nextState) {
        this.probability = probability;
        this.nextState = nextState;
    }
}