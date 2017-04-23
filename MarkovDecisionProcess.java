import java.util.ArrayList;

public class MarkovDecisionProcess {
    
    int rows, columns;
    Double[][] epochTable; //sumOfRewards
    MDPStates[] state;
    int totalStates;
    
    public MarkovDecisionProcess() {
    }
    
    public MarkovDecisionProcess(int states, int epochs, int totalStates) {
        this.rows = states;
        this.columns = epochs;
        this.totalStates = totalStates;
        epochTable = new Double[rows][columns];
    }
    
    public void initiateMDP() { // HardCoded MDP from HW
        // GoalState index at the end of MDPState constructor.
        // Column Actions: [0] = P, [1] = R, [2] = S 
        /* Array Indexes: 
        // 0 = RU 8pm, 1 = TU 10pm, 2 = RU 10pm, 3 = RD 10pm
        // 4 = RU 8am, 5 = RD 8am, 6 = TU 10am, 7 = RU 10am, 8 = RD 10am, 9 = TD 10am
        // 10 = 11AM 
        */
        
        state = new MDPStates[totalStates];
        state[0] = new MDPStates(0, 0, 0); //RU 8pm
        state[0].addAction(new MDPStates(2, 1.0, 1));
        state[0].addAction(new MDPStates(0, 1.0, 2));
        state[0].addAction(new MDPStates(-1, 1.0, 3));
        
        state[1] = new MDPStates(0, 0, 1);
        state[1].addAction(new MDPStates(0, 1.0, 7));
        state[1].addAction(new MDPStates(2, 1.0, 4));
        
        state[2] = new MDPStates(0, 0, 2);
        state[2].addAction(new MDPStates(2, 0.5, 4));
        state[2].addActionToIndex(0, new MDPStates(2, 0.5, 7));
        state[2].addAction(new MDPStates(0, 1.0, 4));
        state[2].addAction(new MDPStates(-1, 1.0, 5));
        
        state[3] = new MDPStates(0, 0, 3);
        state[3].addAction(new MDPStates(2, 0.5, 5));
        state[3].addActionToIndex(0, new MDPStates(2, 0.5, 8));
        state[3].addAction(new MDPStates(0, 1.0, 5));
        
        state[4] = new MDPStates(0, 0, 4);
        state[4].addAction(new MDPStates(2, 1.0, 6));
        state[4].addAction(new MDPStates(0, 10, 7));
        state[4].addAction(new MDPStates(-1, 1.0, 8));
        
        state[5] = new MDPStates(0, 0, 5);
        state[5].addAction(new MDPStates(2, 1.0, 9));
        state[5].addAction(new MDPStates(0, 1.0, 8));
        
        state[6] = new MDPStates(0, 0, 6);
        state[6].addAction(new MDPStates(-1, 1.0, 10));
        state[6].addAction(new MDPStates(-1, 1.0, 10));
        state[6].addAction(new MDPStates(-1, 1.0, 10));
        
        state[7] = new MDPStates(0, 0, 7);
        state[7].addAction(new MDPStates(0, 1.0, 10));
        state[7].addAction(new MDPStates(0, 1.0, 10));
        state[7].addAction(new MDPStates(0, 1.0, 10));
        
        state[8] = new MDPStates(0, 0, 8);
        state[8].addAction(new MDPStates(4, 1.0, 10));
        state[8].addAction(new MDPStates(4, 1.0, 10));
        state[8].addAction(new MDPStates(4, 1.0, 10));
        
        state[9] = new MDPStates(0, 0, 9);
        state[9].addAction(new MDPStates(3, 1.0, 10));
        state[9].addAction(new MDPStates(3, 1.0, 10));
        state[9].addAction(new MDPStates(3, 1.0, 10));
        
        
    }
    // 0 = RU 8pm, 1 = TU 10pm, 2 = RU 10pm, 3 = RD 10pm
    // 4 = RU 8am, 5 = RD 8am, 6 = TU 10am, 7 = RU 10am, 8 = RD 10am, 9 = TD 10am
    // 10 = w/e at 11AM

    
    
}