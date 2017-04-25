/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdp;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author Anthony Ortiz && Jerry Uranga
 */
public class MarkovDecisionProcess {
   int rows, columns;
    double[][] epochTable; //sumOfRewards
    Transition[][][] actionTable;
    int totalStates;
    ArrayList<Path> paths;
    int actionsPossible;
    public MDPStates [] allStates;
    Action[] actions;
    int nbReachableStates = 0;
    Vector reachableStates;
    int currentStateIndex;
    int currentAction; 
    double gamma = 1.0;
    ArrayList<Transition> transitions;
    
    
    public MarkovDecisionProcess() {
        
    }
    
    public MarkovDecisionProcess(int states, int nbTimeSlots, int totalStates, int actionsPossible) {
        this.rows = states;
        this.columns = nbTimeSlots;
        this.totalStates = totalStates;
        epochTable = new double[rows][columns];
        this.actionsPossible = actionsPossible;
        actionTable = new Transition[totalStates][actionsPossible][totalStates];
        actions = new Action[actionsPossible]; //4
        allStates =  new MDPStates[totalStates];
        paths = new ArrayList<Path>();
        for(int i = 0; i<actionsPossible; i++ ){
            actions[i] = new Action(i);
        }
        reachableStates = new Vector(totalStates);
        transitions = new ArrayList<Transition>();
        

    }
    
    public void initiateMDP() { // HardCoded MDP from HW
        // GoalState index at the end of MDPState constructor.
        // Column Actions: [0] = P, [1] = R, [2] = S 
        /* Array Indexes: 
        // 0 = RU 8pm, 1 = TU 10pm, 2 = RU 10pm, 3 = RD 10pm
        // 4 = RU 8am, 5 = RD 8am, 6 = TU 10am, 7 = RU 10am, 8 = RD 10am, 9 = TD 10am
        // 10 = 11AM 
        */

       
        
        reachableStates.clear();
        for(int i = 0; i< allStates.length; i++){
            allStates[i] = new MDPStates(i); 
            if (i == allStates.length -1)
                allStates[i].setTerminate(); 
            reachableStates.add(allStates[i]);
        }
        nbReachableStates = allStates.length;
        
        allStates[0].setStateCode("RU 8 PM");  //RU 8pm
        allStates[1].setStateCode("TU 10 PM");
        allStates[2].setStateCode("RU 10 PM");
        allStates[3].setStateCode("RD 10 PM");
        allStates[4].setStateCode("RU 8 AM");
        allStates[5].setStateCode("RD 8 AM");
        allStates[6].setStateCode("TU 10 AM");
        allStates[7].setStateCode("RU 10 AM");
        allStates[8].setStateCode("RD 10 AM");
        allStates[9].setStateCode("TD 10 AM");
        allStates[10].setStateCode("Class 11 AM");
        
        
        actionTable[0][0][1] = new Transition(0,0,1/3, allStates[1], 2);
        actionTable[0][1][2] = new Transition(0,1,1/3, allStates[2], 0);
        actionTable[0][1][3] = new Transition(0,2,1/3, allStates[3], -1);
        actionTable[1][0][7] = new Transition(1,0,1/2, allStates[7], 2);
        actionTable[1][0][4] = new Transition(1,0,1/2, allStates[4], 0);
        actionTable[2][0][4] = new Transition(2,0,1/6, allStates[4], 2);
        actionTable[2][0][7] = new Transition(2,0,1/6, allStates[7], 2);
        actionTable[2][1][4] = new Transition(2,1,1/3, allStates[4], 0);
        actionTable[2][2][5] = new Transition(2,2,1/3, allStates[5], -1);
        actionTable[3][0][5] = new Transition(3,0,1/6, allStates[5], 2);
        actionTable[3][0][8] = new Transition(3,0,1/6, allStates[8], 2);
        actionTable[3][1][5] = new Transition(3,1,1/2, allStates[5], 0);
        actionTable[4][0][6] = new Transition(4,0,1/3, allStates[6], 2);
        actionTable[4][1][7] = new Transition(4,1,1/3, allStates[7], 0);
        actionTable[4][2][8] = new Transition(4,2,1/3, allStates[8], -1);
        actionTable[5][0][9] = new Transition(5,0,1/2, allStates[9], 2);
        actionTable[5][1][8] = new Transition(5,1,1/2, allStates[8], 0);
        actionTable[6][0][10] = new Transition(6,0,1/3, allStates[10], -1);
        actionTable[6][1][10] = new Transition(6,1,1/3, allStates[10], -1);
        actionTable[6][2][10] = new Transition(6,2,1/3, allStates[10], -1);
        actionTable[7][0][10] = new Transition(7,0,1/3, allStates[10], 0);
        actionTable[7][1][10] = new Transition(7,1,1/3, allStates[10], 0);
        actionTable[7][2][10] = new Transition(7,2,1/3, allStates[10], 0);
        actionTable[8][0][10] = new Transition(8,0,1/3, allStates[10], 4);
        actionTable[8][1][10] = new Transition(8,1,1/3, allStates[10], 4);
        actionTable[8][2][10] = new Transition(8,2,1/3, allStates[10], 4);
        actionTable[9][0][10] = new Transition(9,0,1/3, allStates[10], 3);
        actionTable[9][1][10] = new Transition(9,1,1/3, allStates[10], 3);
        actionTable[9][2][10] = new Transition(9,2,1/3, allStates[10], 3);
        
        
        
        /*state[0].addAction(new Transition(1/3, allStates[1], 2));
        state[0].addAction(new Transition(1/3, allStates[2], 0));
        state[0].addAction(new Transition(1/3, allStates[3], -1));
        
        state[1].addAction(new Transition(1/2, allStates[7], 2));
        state[1].addAction(new Transition(1/2, allStates[4], 0));
        
        state[2].addAction(new Transition(1/6, allStates[4], 2));
        state[2].addActionToIndex(0, new Transition(1/6, allStates[7], 2));
        state[2].addAction(new Transition(1/3, allStates[4], 0));
        state[2].addAction(new Transition(1/3, allStates[5], -1);
        
        state[3].addAction(new Transition(2, 0.5, 5));
        state[3].addActionToIndex(0, new Transition(2, 0.5, 8));
        state[3].addAction(new Transition(0, 1.0, 5));
        
        state[4].addAction(new Transition(2, 1.0, 6));
        state[4].addAction(new Transition(0, 10, 7));
        state[4].addAction(new Transition(-1, 1.0, 8));
        
        state[5].addAction(new Transition(2, 1.0, 9));
        state[5].addAction(new Transition(0, 1.0, 8));
        
        state[6].addAction(new Transition(-1, 1.0, 10));
        state[6].addAction(new Transition(-1, 1.0, 10));
        state[6].addAction(new Transition(-1, 1.0, 10));
        
        state[7].addAction(new Transition(0, 1.0, 10));
        state[7].addAction(new Transition(0, 1.0, 10));
        state[7].addAction(new Transition(0, 1.0, 10));
        
        state[8].addAction(new Transition(4, 1.0, 10));
        state[8].addAction(new Transition(4, 1.0, 10));
        state[8].addAction(new Transition(4, 1.0, 10));
        
        state[9].addAction(new Transition(3, 1.0, 10));
        state[9].addAction(new Transition(3, 1.0, 10));
        state[9].addAction(new Transition(3, 1.0, 10));
        */

    }
    
    public void printPaths(){        
        for(int i=0; i< this.paths.size(); i++){
           System.out.print("State: " + getStartState().getStateCode()+ " ");
           for(int j = 0; j< this.paths.get(i).pathTransitions.size(); j++){
              System.out.print("Action: " +actions[paths.get(i).pathTransitions.get(j).getActionIndex()].actionCode + " "); 
              System.out.print("State: " + paths.get(i).pathTransitions.get(j).nextState.getStateCode()+ " ");
           }
          // System.out.print("Path Reward: " + paths.get(i).getReward());
          // System.out.println();
        }
        
    }
    
 public void printTable() {
        double[] averageReward = new double[epochTable[0].length];
        
        for(int i = 0; i < epochTable.length; i++) {
            for(int j = 0; j < epochTable[0].length; j++) {
                System.out.printf("%.2f ", epochTable[i][j]);
            }
            System.out.println();
        }
        
        for(int i = 0; i < epochTable[0].length; i++) {
            for(int j = 0; j < epochTable.length; j++) {
                 averageReward[i] += epochTable[j][i];
            }
        }
        
        for(int i = 0; i < averageReward.length; i++)
            System.out.println("Average reward for path " + String.valueOf(i) + " " +(averageReward[i]/epochTable.length));
        
    }
    
    public void printPath(int index){
        System.out.print("InitialState: " + getStartState().getStateCode() + " ");        
        for(int i=0; i< this.paths.get(index).pathTransitions.size(); i++){  
            System.out.print("A: " + paths.get(index).pathTransitions.get(i).getActionIndex() + " "); 
            System.out.print("S: " + paths.get(index).pathTransitions.get(i).nextState.getStateCode() + " ");
        }
           System.out.println("Path Reward: " + paths.get(index).getReward());
    }
    
    public MDPStates getStartState() {
        currentStateIndex = 0;
        return (MDPStates)reachableStates.get(0);//maybe reachable states
    }
    
    public double getGamma(){
        return this.gamma;
    }
    
    public int getReachaleSize(){
        return this.nbReachableStates;
    }
    
   
    
    public MDPStates getState(int index) {
        return allStates[index];//maybe reachable states
    }
    
     public MDPStates getNextState(){
         currentStateIndex ++;
         if(currentStateIndex == nbReachableStates)
             return null;
         else
             return (MDPStates)reachableStates.get(currentStateIndex);
     }
     
     public Action getStartAction() {
	currentAction = 0;
	return actions[0];
     }
     
    public Action getNextAction() {
        currentAction ++;
	if(currentAction == actionsPossible)
            return null;
	else
            return actions[currentAction];
    }
     public double getUtility(MDPStates s){
         return s.getUtility();
     }
     public double getReward(MDPStates s){
         return s.getReward();
     }
    
    public void generateProperPolicy(){
       
        
    }
    
    public void addPath() {  
        //this.code += code;
    }
    
    public Action getRandomAction(){
        int a = (int) Math.round(Math.random()* actionsPossible -0.5);
        return new Action(a);
    }
    
    public void decryptCode() {
        
    }
    
    public void setGamma(double gamma){
        this.gamma = gamma;
        
    }
    // 0 = RU 8pm, 1 = TU 10pm, 2 = RU 10pm, 3 = RD 10pm
    // 4 = RU 8am, 5 = RD 8am, 6 = TU 10am, 7 = RU 10am, 8 = RD 10am, 9 = TD 10am
    // 10 = w/e at 11AM
    
    public void updateValues(Path path, int epoch, double learningRate ) { // path.size = 1 
        //int pathIndex = paths.get(epoch).size();
        
        for(int i = 0; i < path.pathTransitions.size(); i++) {
            //Transition temp = path.pathTransitions.get(i);
            //System.out.println(path.pathTransitions.get(i).getReward());
            path.setReward(path.getReward() + path.pathTransitions.get(i).getReward());
        }
        for(int i = 0; i < path.pathTransitions.size(); i++) {
            Transition temp = path.pathTransitions.get(i);
            MDPStates state = temp.nextState;
            if (temp.nextState == null)
                break;
            state.setUtility(state.getUtility() + learningRate*(path.getReward() - state.getUtility())); // MonteCarlo Update
            epochTable[state.getIndex()][epoch] = state.getUtility();
        }
        printPath(epoch);
    }
    
    public ArrayList<Transition> getTransition(MDPStates s, Action a){
        transitions.clear();
        
        if(s.terminate)
            return transitions;
        for(int i = 0; i< this.totalStates; i++){
            if(this.actionTable[s.getIndex()][a.index][i] != null){
                transitions.add(this.actionTable[s.getIndex()][a.index][i]);
            }
        }
        return transitions;
    }
    
    public void setUtility(MDPStates s, double u){
        if(s.terminate){
			//TODO the assignment is redundant
            s.setUtility(s.getReward()) ;
	}else
            s.setUtility(u);
	
    }
    
    public void setAction(MDPStates s, Action a) {
	if(s.terminate)
            return;
		
        s.setAction(a);
    }
}
