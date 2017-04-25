/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdp;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Anthony Ortiz && Jerry Uranga
 */
public class MDP {
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
        MDP.paths.add(new Path(MDP.actionTable[0][0][0]));
        while (epoch < steps) {
            while(MDP.actionTable[index][rA.get(indexA)][rT.get(indexT)] == null) {
                if (indexT == rT.size()) {
                    indexT = 0;
                    ++indexA;
              }
                else
                    ++indexT;
            }
            MDP.paths.get(epoch).pathTransitions.add(MDP.actionTable[index][rA.get(indexA)][rT.get(indexT)]);
                if (MDP.paths.get(epoch).pathTransitions.get(MDP.paths.get(epoch).pathTransitions.size()-1).nextState.terminate)
                    index = 0;
                    MDP.updateValues(MDP.paths.get(epoch),epoch, .1);
                    ++epoch;
          }
            index = rT.get(indexT);
            Collections.shuffle(rA);
            indexA = 0;
      }
    
    public static void main(String[] args) {
        MarkovDecisionProcess MDP = new MarkovDecisionProcess(11, 50, 11,3);
        MDP.initiateMDP();
        MDP.printTable();
        monteCarloMethod(MDP, 50);
        MDP.printPaths();
    }
}
