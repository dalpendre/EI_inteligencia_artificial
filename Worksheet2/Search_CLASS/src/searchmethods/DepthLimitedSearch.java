package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;

public class DepthLimitedSearch extends DepthFirstSearch {

    private int limit;

    public DepthLimitedSearch() {
        this(28);
    }

    public DepthLimitedSearch(int limit) {
        this.limit = limit;
    }

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

            int total = 0;
            if(n.getDepth() < limit)
            {
                List<State> sucessors = problem.executeActions(n.getState());
                addSuccessorsToFrontier(sucessors, n);
                computeStatistics(sucessors.size());
            }
            computeStatistics(total);
        }
        return null;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Limited depth first search";
    }
}
