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
public class Action {
    //ArrayList<Transition> transitions;
    int index;
    String actionCode;
    
    /*public Action(Transition goalState) {
        transitions = new ArrayList<Transition>();
        transitions.add(goalState);
    }
    */
    public Action(int index) {
        this.index = index;
        if(index == 0)
            actionCode = "P";
        else if(index == 1)
            actionCode = "R";
        else
            actionCode = "S";
    }
    
    public Action(int index, String actionCode) {
        this.index = index;
        this.actionCode = actionCode;
    }
    /*
    public void addTransition(Transition goalState) {
        transitions.add(goalState);
    }
    */
    
}
