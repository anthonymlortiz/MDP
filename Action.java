/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdp;
import java.util.ArrayList;

/**
 *
 * @author Anthony
 */
public class Action {
    //ArrayList<Transition> transitions;
    int index;
    int actionCode;
    
    /*public Action(Transition goalState) {
        transitions = new ArrayList<Transition>();
        transitions.add(goalState);
    }
    */
    public Action(int index) {
        this.index = index;
    }
    /*
    public void addTransition(Transition goalState) {
        transitions.add(goalState);
    }
    */
    
}
