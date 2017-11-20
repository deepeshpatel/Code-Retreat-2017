package global.coderetreat;

import java.util.*;
import java.util.stream.Collectors;
import java.awt.Point;


/**
 * Created by patelde on 18/11/17.
 */
public final class GameOfLife {

    private Set<Point> liveCells;

    public GameOfLife(Set<Point> liveCells) {
        this.liveCells = clone(liveCells);
    }

    public GameOfLife nextGeneration() {
        return new GameOfLife(getListOfCellsUnderConsideration().stream()
                .filter(e-> considerForNextGen(e, getLiveNeighbourCount(e))).collect(Collectors.toSet()));
    }

    public Set<Point> getLiveCells() {
        return clone(liveCells);
    }

    private boolean considerForNextGen(Point p, long liveNeighbourCount) {
        return  surviveInNextGen(p, liveNeighbourCount) ||  deadBecomesAlive(p, liveNeighbourCount);
    }

    private boolean surviveInNextGen(Point p, long liveNeighbourCount) {
        return alive(p) && (liveNeighbourCount == 2 || liveNeighbourCount == 3);
    }

    private boolean deadBecomesAlive(Point p, long liveNeighbourCount) {
        return !alive(p) && liveNeighbourCount == 3;
    }

    private boolean alive(Point p) {
        return liveCells.contains(p);
    }

    private Set<Point> clone(Set<Point> original) {
        return original.stream().map(e-> new Point(e)).collect(Collectors.toSet());
    }

    private java.util.List<Point> getNeighbours(Point p) {
        List<Point> neighbours = new ArrayList<>(getTopNeighbours(p));
        neighbours.addAll(getBottomNeighbours(p));
        neighbours.addAll(getSideNeighbours(p));
        return neighbours;
    }

    private java.util.List<Point> getConsideredCells(Point p) {
        List<Point> consideredCells = new ArrayList<>(getNeighbours(p));
        consideredCells.add(p);
        return consideredCells;
    }

    private java.util.List<Point> getTopNeighbours(Point p) {
        return Arrays.asList(new Point(p.x-1, p.y+1), new Point(p.x, p.y+1), new Point(p.x+1, p.y+1));
    }

    private java.util.List<Point> getBottomNeighbours(Point p) {
        return Arrays.asList(new Point(p.x-1, p.y-1), new Point(p.x, p.y-1), new Point(p.x+1, p.y-1));
    }

    private java.util.List<Point> getSideNeighbours(Point p) {
        return Arrays.asList(new Point(p.x-1, p.y), new Point(p.x+1, p.y));
    }

    private long getLiveNeighbourCount(Point p) {
        return getNeighbours(p).stream().filter(e-> liveCells.contains(e)).count();
    }

    private Set<Point> getListOfCellsUnderConsideration() {
        return  liveCells.stream().map(e-> getConsideredCells(e))
                .flatMap(Collection::stream).collect(Collectors.toSet());
    }

}
