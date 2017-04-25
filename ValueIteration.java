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
public class ValueIteration {
    MarkovDecisionProcess mdp;
    double errorChange = 0.001;
    int numIterations;
    double lambda = 0.99;
    
    
    
    public ValueIteration(MarkovDecisionProcess mdp, double lambda, double maxErrorChange){
        this.mdp = mdp;
        this.lambda = lambda;
        this.errorChange = maxErrorChange;
    }
    
    public void setError(double errorChange){
        this.errorChange = errorChange;
    }
    
    public int getNumberIterations(){
        return this.numIterations;
    }
    
    public int solve(){
        mdp.setGamma(this.lambda);
        double gamma = mdp.getGamma();
        double lastError = Double.MAX_VALUE;
        boolean finished = false;
        numIterations = 0;
        while(!finished){
            double maxError = -1.;
            for (MDPStates state = mdp.getStartState(); state!= null; state = mdp.getNextState()){
                double utility = mdp.getUtility(state);
                double reward = mdp.getReward(state);
                
                double maxCurrUtility = Double.MIN_VALUE;
                Action maxAction = null;
                
                for(Action action = mdp.getStartAction(); action!=null; action=mdp.getNextAction()){
                    ArrayList<Transition> T = mdp.getTransition(state, action);
                    int s  = T.size();
                    double nextUtil =  0;
                    for(int i=0; i<s; i++){
                        Transition t = T.get(i);
                        double prob = t.getProbability();
                        MDPStates sPrime = t.nextState;
                        nextUtil += (prob * (reward + t.getReward()));
                    }
                    if(nextUtil > maxCurrUtility){
                        maxCurrUtility = nextUtil;
                        maxAction = action;
                    }
                }
                
                maxCurrUtility = reward + gamma*maxCurrUtility;
                mdp.setUtility(state, maxCurrUtility);
                mdp.setAction(state, maxAction);
                
                double currentError = Math.abs(maxCurrUtility - utility);
                if(currentError>maxError)
                    maxError = currentError;
            }
            numIterations ++;
            if(Math.abs(lastError - maxError) < this.errorChange )
                lastError = maxError;
            else
                finished = true;
        }
        
       
      return numIterations;  
    }
    
}
