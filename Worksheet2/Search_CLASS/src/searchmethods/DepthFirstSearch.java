package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class DepthFirstSearch extends GraphSearch<NodeLinkedList> {

    public DepthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    //Graph Search without explored list
    @Override
    protected Solution graphSearch(Problem problem)
    {
        frontier.clear();
        frontier.add(new Node(problem.getInitialState()));

        while(!frontier.isEmpty() && !stopped)
        {
            Node n = frontier.poll();
            if(problem.isGoal(n.getState()))
            {
                return new Solution(problem, n);
            }
            List<State> sucessors = problem.executeActions(n.getState());
            addSuccessorsToFrontier(sucessors, n);
            computeStatistics(sucessors.size());
        }
        return null;
    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {
        for (State s : successors) {
            if (!frontier.containsState(s)) {
                if (!parent.isCycle(s)) {
                    frontier.addFirst(new Node(s, parent));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Depth first search";
    }
}