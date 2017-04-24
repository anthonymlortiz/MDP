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
    Double[][] epochTable; //sumOfRewards
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
    
    
    public MarkovDecisionProcess() {
    }
    
    public MarkovDecisionProcess(int states, int nbTimeSlots, int totalStates, int actionsPossible) {
        this.rows = states;
        this.columns = nbTimeSlots;
        this.totalStates = totalStates;
        epochTable = new Double[rows][columns];
        this.actionsPossible = actionsPossible;
        actionTable = new Transition[totalStates][actionsPossible][totalStates];
        actions = new Action[actionsPossible]; //4
        allStates =  new MDPStates[totalStates];
        for(int i = 0; i<actionsPossible; i++ ){
            actions[i] = new Action(i);
        }
        reachableStates = new Vector(totalStates);
        

    }
    
    public void initiateMDP() { // HardCoded MDP from HW
        // GoalState index at the end of MDPState constructor.
        // Column Actions: [0] = P, [1] = R, [2] = S 
        /* Array Indexes: 
        // 0 = RU 8pm, 1 = TU 10pm, 2 = RU 10pm, 3 = RD 10pm
        // 4 = RU 8am, 5 = RD 8am, 6 = TU 10am, 7 = RU 10am, 8 = RD 10am, 9 = TD 10am
        // 10 = 11AM 
        */

        /*allStates[0] = new MDPStates(0); //RU 8pm
        allStates[1] = new MDPStates(1);
        allStates[2] = new MDPStates(2);
        allStates[3] = new MDPStates(3);
        allStates[4] = new MDPStates(4);
        allStates[5] = new MDPStates(5);
        allStates[6] = new  MDPStates(6);
        allStates[7] = new MDPStates(7);
        allStates[8] = new MDPStates(8);
        allStates[9] = new MDPStates(9);
        allStates[10] = new MDPStates(10);
        */
        
        reachableStates.clear();
        for(int i = 0; i< allStates.length; i++){
            allStates[i] = new MDPStates(i); 
            if (i == allStates.length -1)
                allStates[i].setTerminate(); 
            reachableStates.add(allStates[i]);
        }
        nbReachableStates = allStates.length;
        
        actionTable[0][0][1] = new Transition(0,0,1/3, allStates[1], 2);
        actionTable[0][1][2] = new Transition(0,1,1/3, allStates[2], 0);
        actionTable[0][1][3] = new Transition(0,1,1/3, allStates[3], -1);
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
           System.out.print(" State: " + getStartState().getIndex() + " ");
           for(int j = 0; j< this.paths.get(i).pathTransitions.size(); j++){
              System.out.print("Action: " + paths.get(i).pathTransitions.get(j).getActionIndex() + " "); 
              System.out.print("State: " + paths.get(i).pathTransitions.get(j).nextState.getIndex()+ " ");
           }
           System.out.print("Path Reward: " + paths.get(i).getReward());
           System.out.println();
        }
        
    }
    
    public MDPStates getStartState() {
        currentStateIndex = 0;
        return (MDPStates)reachableStates.get(0);//maybe reachable states
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
    
    // 0 = RU 8pm, 1 = TU 10pm, 2 = RU 10pm, 3 = RD 10pm
    // 4 = RU 8am, 5 = RD 8am, 6 = TU 10am, 7 = RU 10am, 8 = RD 10am, 9 = TD 10am
    // 10 = w/e at 11AM
    
}
