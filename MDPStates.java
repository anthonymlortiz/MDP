/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdp;
import java.util.ArrayList;

/**
 *
 * @author Anthony Ortiz && Jerry Uranga
 */
public class MDPStates {
   private int index;
    private double utility;
    private Action action;
    boolean[] actionTaken = new boolean[4];
    boolean terminate;
    boolean visited;
    
    public MDPStates( int index, double utility) {
        this.index = index;
        this.utility = utility;
        this.terminate = false;
        this.visited = false;
        this.action = null;
        for (int i = 0 ; i<4; i++){
            actionTaken[i] = false;
        }
    }
    
    public MDPStates( int index) {
        this.index = index;
        this.terminate = false;
        this.visited = false;
        this.action = null;
        for (int i = 0 ; i<4; i++){
            actionTaken[i] = false;
        }
    }
    
    public void setTerminate(){
        this.terminate = true;
    }
    
    public boolean isVisited(){
        return this.visited;
    }
    
    public int getIndex() {
        return index;
    }
    
    public Action getAction(int index) {
        return this.action;
    }
    
    public double getUtility() {
        return utility;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    
    public void setUtility(double utility) {
        this.utility = utility;
    }
    /*
    public void addAction(int actionIndex) {
        action.add(new Action(goalState));
    }
    
    public void addActionToIndex(int index, MDPStates goalState) {
        Action transition = action.get(index);
        transition.addTransition(goalState);
    }
    */
    
}
