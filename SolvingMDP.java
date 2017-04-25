/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package mdp;

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
    
    public void QLearning(MarkovDecisionProcess MDP, double learningRate, double gamma, double lambda) {
        int nPossibleActions = MDP.actions.size;
        int nPossibleStates = MDP.totalStates;
        int index = 0;
        int indexT = 0;
        int indexA = 0;
        ArrayList<int> rA = new ArrayList<int>();
        ArrayList<int> rT = new ArrayList<int>();
        for(int i = 0; i < nPossibleActions; i++)
            rT.add(i);
        for(int i = 0; i < nPossibleStates; i++) 
            rA.add(i);
        boolean quit = false;
        int epoch = 0;
        while (!quit) {
            ++epoch;
            Collections.shuffle(rA);
            Collections.shuffle(rT);
            while(MDP.actionTable[index][rA.get(indexA)][rT.get(indexT)] == null) {
                if (indexT >= rT.size()-1) {
                    indexT = 0;
                    ++indexA;
              }
                else
                    ++indexT;
            }
            if (rT.get(indexT) == nPossibleStates - 1) {
                index = 0;
            }
            else {
                if (MDP.QValues[index] = 0)
                    MDP.QValues[index] = 1;
                double previousValue = MDP.QValues[index];
                double nextStateQValue = MDP.QValues[indexT];
                MDP.QValues[index] = previousValue + learningRate*(DecisionTable[index][indexA][indexT].reward + gamma*(nextStateQValue) - previousValue);
                System.out.print("Prev value: " + previousValue + " ");
                System.out.print("New value: " + MDP.QValues[index] + " ");
                System.out.print("Reward: " + (previousValue - MDP.QValues[index]));
                System.out.print("NextState value: " + MDP.QValues[rT.get(indexT]);
                if (MDP.QValues[index] < 0.001) 
                    quit = true;
                index = rT.get(indexT);
                learningRate *= lambda;
            }
            indexA = 0;
        }
        MDP.printQValues();
        MDP.printQPolicy();
    }
    
    public static void main(String[] args) {
        MarkovDecisionProcess MDP = new MarkovDecisionProcess(11, 51, 11,3);
        MDP.initiateMDP();
        monteCarloMethod(MDP, 51);
        // MDP.printPaths();
        MDP.printTable();
       // ValueIteration vi = new ValueIteration(MDP, 0.99, 0.001);
        //int it = vi.solve();
        // System.out.println(it);
        
    }

}