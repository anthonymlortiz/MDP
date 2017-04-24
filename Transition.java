/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdp;

/**
 *
 * @author Anthony && Jerry Uranga
 */
import java.util.ArrayList;
public class Transition {
    private double probability;
    MDPStates nextState;
    private double reward;
    
    public Transition(double probability, MDPStates nextState, double reward) {
        this.nextState = nextState;
        this.probability = probability;
        this.reward = reward;
    }
    
    public double getProbability() {
        return probability;
    }
    
    public void setProbability(double probability){
        this.probability = probability;
        
    }
    
    public double getReward(){
        return reward;
    }
    
    public void setReward(double reward){
        this.reward = reward;
    }
    
}
