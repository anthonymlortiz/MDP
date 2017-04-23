public class StudentState {
    
    private State state;
    private int time; // 20, 22, 8, 10, 11
    //private Action action; // P, R, S
    ArrayList<Action> actions;

    public StudentState(State state, int time) {
        this.state = state;
        this.time = time;
        this.actions = new ArrayList<Action>;
    }

    public State getState() {
        return state;
    }
    
    public int getTime() {
        return time;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
}