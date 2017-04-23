
public class MarkovDecisionProcess {
    
    int rows, columns;
    Double[][] epochTable; //sumOfRewards
    ArrayList<ArrayList<StudentState>> experienceTable;
    
    public MarkovDecisionProcess() {
    }
    
    public MarkovDecisionProcess(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        epochTable = new Double[rows][columns];
        experienceTable = new ArrayList<ArrayList<StudentState>>
    }
    
    public void initiateMDP() {
        
        
        StudentState root = new StudentState(State.RU, 20,)
    }
    
    //Table 1
    // Rows = Number of States *Would be 4 for us*
    // Columns = Number of Epochs
    
    // Table2
    // ArrayList<StudentState> student = new ArrayList<StudentState>;
    
    // State Action Probability Reward GoalState
    // CState ActEnum Transition.P Action.Reward NextState
    // NextState ActEnum Transition.P Action.Reward NextState
    
    // Table 3 [correct one] 
    /*
    index = every possible state which would be numOfActions * numOfPossibleStates
    numOfActions = 4 actions every time but should be a parameter that user can change
    numOfStates = 4 states possible to reach but should be a parameter that user can change
    double[][][] table = new double[index][numOfActions][numOfPossibleStates];
    
    
    */
    
    
}