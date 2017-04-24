/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdp;

/**
 *
<<<<<<< HEAD
 * @author Anthony && Jerry Uranga
=======
 * @author Anthony Ortiz && Jerry Uranga
>>>>>>> 01d14b8be6233559d3e355d8399df344f0144310
 */
import java.util.ArrayList;
public class Path {
    ArrayList<Transition> pathTransitions;
    private double reward;
    
    public void addTransition(Transition trans){
        this.pathTransitions.add(trans);
        this.reward = this.reward + trans.getReward();
    }
    
    public void setReward(double reward){
        this.reward = reward;
    }
    public double getReward(){
        return this.reward;
    }
}
