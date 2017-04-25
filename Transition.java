/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdp;

/**
 *
 * @author Anthony Ortiz && Jerry Uranga
 */
import java.util.ArrayList;
public class Transition {
    private int actionIndex;
    private int sourceStateIndex;
    private double probability;
    MDPStates nextState;
    private double reward;
    
    public Transition(int sourceStateIndex, int actionIndex, double probability, MDPStates nextState, double reward) {
        this.actionIndex = actionIndex;
        this.sourceStateIndex = sourceStateIndex;
        this.nextState = nextState;
        this.probability = probability;
        this.reward = reward;
    }
    
    public int getActionIndex(){
        return this.actionIndex;
    }
    
    public int getSourceStateIndex(){
        return this.sourceStateIndex;
    }
    
    public double getProbability() {
        return probability;
    }
    
    public void setProbability(double probability){
        this.probability = probability;
        
    }
    
    public double getReward(){
        return this.reward;
    }
    
    public void setReward(double reward){
        this.reward = reward;
    }
    
}
