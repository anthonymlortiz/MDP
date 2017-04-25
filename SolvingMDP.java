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
import java.util.*;
public class SolvingMDP {
    
    public static void monteCarloMethod(MarkovDecisionProcess MDP, int steps) {
        int nPossibleActions = MDP.actions.length;
        int nPossibleStates = MDP.totalStates;
        ArrayList<Integer> rT = new ArrayList<Integer>();
        int indexT = 0;
        ArrayList<Integer> rA = new ArrayList<Integer>();
        int indexA = 0;
        for(int i =0; i < nPossibleStates; i++)
            rT.add(i);
        Collections.shuffle(rT);
        for(int i =0; i < nPossibleActions; i++)
            rA.add(i);
        Collections.shuffle(rA);
        int index = 0;
        int epoch = 0;
        
        //MDP.paths.add(new Path(MDP.actionTable[0][0][1]));
        while (epoch < steps) {
            while(MDP.actionTable[index][rA.get(indexA)][rT.get(indexT)] == null) {
                if (indexT >= rT.size()-1) {
                    indexT = 0;
                    ++indexA;
              }
                else
                    ++indexT;
            }
            if (MDP.paths.size() <= epoch)
                MDP.paths.add(new Path(MDP.actionTable[index][rA.get(indexA)][rT.get(indexT)]));
            else 
                MDP.paths.get(epoch).pathTransitions.add(MDP.actionTable[index][rA.get(indexA)][rT.get(indexT)]);
            /*if (MDP.paths.get(epoch).pathTransitions.get(MDP.paths.get(epoch).pathTransitions.size()-1).nextState.terminate) {
                index = 0;
                MDP.updateValues(MDP.paths.get(epoch),epoch, .1);
                ++epoch;
            } */
            if(rT.get(indexT) == nPossibleStates - 1) {
                    //System.out.println("Index is: " + index);
                    index = 0;
                    MDP.updateValues(MDP.paths.get(epoch),epoch, .1);
                    ++epoch;
                }
            else 
                index = rT.get(indexT);
            indexT = 0;
            Collections.shuffle(rT);
            Collections.shuffle(rA);
            indexA = 0;
            //System.out.println(rA.get(indexA));   
      }
    }
    
    public static void main(String[] args) {
        MarkovDecisionProcess MDP = new MarkovDecisionProcess(11, 51, 11,3);
        MDP.initiateMDP();
        monteCarloMethod(MDP, 51);
        // MDP.printPaths();
        MDP.printTable();
        ValueIteration vi = new ValueIteration(MDP, 0.99, 0.001);
        int it = vi.solve();
        System.out.println(it);
        
    }

}