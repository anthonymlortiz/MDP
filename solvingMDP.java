

public class SolvingMDP {
    
    public void monteCarloMethod(MarkovDecisionProcess MDP, int steps) {
        int nPossibleActions = MDP.actions.length();
        int nPossibleStates = MDP.totalStates;
        ArrayList<int> rT = new ArrayList<int>();
        int indexT = 0;
        ArrayList<int> rA = new ArrayList<int>();
        int indexA = 0;
        for(int i =0; i < nPossibleStates; i++)
            rT.add(i);
        Collections.shuffle(rT)
        for(int i =0; i < nPossibleActions; i++)
            rA.add(i);
        Collections.shuffle(rA);
        int index = 0;
        path.add[0][0][0];
        while (MDP.epoch < steps) {
            while(MDP.actionTable[index][rA.get(indexA)][rT.get(indexT)] == null) {
                if (indexT == rT.size) {
                    indexT = 0;
                    ++indexA;
              }
                else
                    ++indexT;
            }
            MDP.path.add(MDP.actionTable[rT.get(indexT)]);
                if (MDP.path.get(MDP.path.size).nextState.terminate) {
                    index = 0;
                    //updateValues(MDP.path); Gets Initiated Needs to be Added
                    ++MDP.epoch;
                }
                Collections.shuffle(rA);
                rA = 0;
                index = rt.get(indexT);
          }

      }
    
    public static void main(String[] args) {
        MarkovDecisionProcess MDP = new MarkovDecisionProcess();
        MDP.initiateMDP();
        monteCarloMethod(MDP);
    }

}