package eightpuzzle;

import agent.Action;
import agent.Problem;

import java.util.LinkedList;
import java.util.List;

public class EightPuzzleProblem extends Problem<EightPuzzleState>
{
    private EightPuzzleState goalState;
    private LinkedList<Action> actions;

    public EightPuzzleProblem(EightPuzzleState initialState) {
        super(initialState);
        actions = new LinkedList<>();
        actions.add(new ActionDown());
        actions.add(new ActionUp());
        actions.add(new ActionRight());
        actions.add(new ActionLeft());

        goalState =  new EightPuzzleState(EightPuzzleState.GOAL_MATRIX);
    }

    @Override
    public List<EightPuzzleState> executeActions(EightPuzzleState state) {
        LinkedList<EightPuzzleState> successors= new LinkedList<>();
        for(Action a: actions){
            if(a.isValid(state)){
                EightPuzzleState successor = (EightPuzzleState) state.clone();
                a.execute(successor);
                successors.add(successor);
            }
        }

        return successors;
    }

    @Override
    public boolean isGoal(EightPuzzleState state) {
        return state.equals(goalState);
    }

    public EightPuzzleState getGoalState() {
        return goalState;
    }
}
