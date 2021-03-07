package reactiveAgent;

import java.awt.Color;

public class ReactiveAgent implements Agent {

    private Cell cell;

    public ReactiveAgent(Cell cell) {
        this.cell = cell;
        this.cell.setAgent(this);
    }

    public void act(Environment environment) {
        Perception perception = buildPerception(environment);
        Action action = decide(perception);
        execute(action, environment);
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell.setAgent(null);
        this.cell = cell;
        this.cell.setAgent(this);
    }

    public Color getColor() {
        return Color.BLACK;
    }

    private Perception buildPerception(Environment environment) {
        return new Perception(
                environment.getNorthCell(cell),
                environment.getSouthCell(cell),
                environment.getEastCell(cell),
                environment.getWestCell(cell));
    }

    //Modify according to the exercise
    private Action decide(Perception perception) {
        //return decide_a(perception);
        //return decide_b(perception);
        //return decide_c(perception);
        return decide_d(perception);
    }

    private void execute(Action action, Environment environment) {

        Cell nextCell = null;

        if (action == Action.NORTH && environment.hasNorthCell(cell)) {
            nextCell = environment.getNorthCell(cell);
        } else if (action == Action.SOUTH && environment.hasSouthCell(cell)) {
            nextCell = environment.getSouthCell(cell);
        } else if (action == Action.WEST && environment.hasWestCell(cell)) {
            nextCell = environment.getWestCell(cell);
        } else if (action == Action.EAST && environment.hasEastCell(cell)) {
            nextCell = environment.getEastCell(cell);
        }

        if (nextCell != null && !nextCell.hasWall()) {
            setCell(nextCell);
        }
    }

    private Action decide_a(Perception perception)
    {
        // TODO
        // Implement the decision process of a basic version of the reactive agent,
        // which should simply wander around the world avoiding hitting the walls.
        // This agent doesn’t care about dirt, yet (but it always cleans visited
        // cells on this and the next exercises).

        if(perception.getN() != null && !perception.getN().hasWall())
        {
            return Action.NORTH;
        }

        if(perception.getW() != null && !perception.getW().hasWall())
        {
            return Action.WEST;
        }

        if(perception.getS() != null && !perception.getS().hasWall())
        {
            return Action.SOUTH;
        }

        if(perception.getE() != null && !perception.getE().hasWall())
        {
            return Action.EAST;
        }

        return null;
    }

    private Action decide_b(Perception perception) {

        // TODO
        // The agent should now be able to perceive dirt in adjacent
        // cells and use that information in its decision process.
        // This agent prefers to visit dirty cells first.

        if(perception.getN() != null && perception.getN().isDirty())
        {
            return Action.NORTH;
        }

        if(perception.getW() != null && perception.getW().isDirty())
        {
            return Action.WEST;
        }

        if(perception.getS() != null && perception.getS().isDirty())
        {
            return Action.SOUTH;
        }

        if(perception.getE() != null && perception.getE().isDirty())
        {
            return Action.EAST;
        }

        return decide_a(perception);
    }

    private Action decide_c(Perception perception) {

        // TODO
        // Implement a memory mechanism that allows the agent to prefer
        // visiting cells that were visited the longest. Your goal is that
        // the agent visits as many cells as possible. This agent doesn’t
        // care about dirt. You may need to change the Cell class...

        Action action = null;
        int lastIteration = Integer.MAX_VALUE;

        if (perception.getN() != null && !perception.getN().hasWall()) {
            lastIteration = perception.getN().getLastIteration();
            action = Action.NORTH;
        }

        if (perception.getW() != null && !perception.getW().hasWall()) {
            if (perception.getW().getLastIteration() <= lastIteration) {
                lastIteration = perception.getW().getLastIteration();
                action = Action.WEST;
            }
        }

        if (perception.getS() != null && !perception.getS().hasWall()) {
            if (perception.getS().getLastIteration() <= lastIteration) {
                lastIteration = perception.getS().getLastIteration();
                action = Action.SOUTH;
            }
        }
        if (perception.getE() != null && !perception.getE().hasWall()){
            if (perception.getE().getLastIteration() <= lastIteration) {
                action = Action.EAST;
            }
        }

        return action;
    }

    private Action decide_d(Perception perception) {

        // TODO
        // Finally, implement a decision process in which the agent, besides
        // preferring to visit dirty cells first, it prefers to visit cells that
        // were visited the longest. It should consider the second criterium as a
        // tiebreaker, when more than one adjacent cell is dirty or if there are
        // no dirty adjacent cells.

        Action action = null;
        int lastIteration = Integer.MAX_VALUE;

        if(perception.getN() != null && perception.getN().isDirty())
        {
            return Action.NORTH;
        }

        if(perception.getW() != null && perception.getW().isDirty())
        {
            if (perception.getW().getLastIteration() <= lastIteration) {
                lastIteration = perception.getW().getLastIteration();
                action = Action.WEST;
            }
        }

        if(perception.getS() != null && perception.getS().isDirty())
        {
            if (perception.getS().getLastIteration() <= lastIteration) {
                lastIteration = perception.getS().getLastIteration();
                action = Action.SOUTH;
            }
        }

        if(perception.getE() != null && perception.getE().isDirty())
        {
            if (perception.getE().getLastIteration() <= lastIteration)
            {
                lastIteration = perception.getE().getLastIteration();
                action = Action.EAST;
            }
        }

        if(action != null)
        {
            return action;
        }

        return decide_c(perception);
    }
}