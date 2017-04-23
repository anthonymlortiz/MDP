public class StudentState {
    
    private State state;
    private int time; // 20, 22, 8, 10, 11
    //private Action action; // P, R, S
    ArrayList<Action> actions;
    private double reward;
    private double utility;
    int index;
    
    boolean [] actionTaken = new boolean[4];

    public StudentState(State state, int time, double reward) {
        this.state = state;
        this.time = time;
        this.actions = new ArrayList<Action>;
        this.reward = reward;
       // this.utility = utility;
        for(int i = 0; i<4; i++){
            actionTaken[i] = false;
        }
    }

    public State getState() {
        return state;
    }
    
    public int getTime() {
        return time;
    }
    
    public double getReward(){
        return reward;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public void setReward(double reward){
        this.reward = reward;
    }
}