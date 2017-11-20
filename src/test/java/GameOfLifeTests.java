import global.coderetreat.GameOfLife;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by patelde on 18/11/17.
 */
public class GameOfLifeTests {

    private Set<Point> initialLiveCells;

    @Before
    public void setUp() {
        initialLiveCells = new HashSet<>();
    }

    @Test
    public void nextOfEmptyGameShouldBeEmpty() {
        GameOfLife game = new GameOfLife(initialLiveCells);
        GameOfLife nextGenGam = game.nextGeneration();
        Assert.assertTrue(nextGenGam.getLiveCells().isEmpty());
    }

    @Test
    public void oneCellGameShouldGenerateEmptyGame() {
        initialLiveCells.add(new Point(3,4));
        GameOfLife game = new GameOfLife(initialLiveCells);
        GameOfLife nextGenGame = game.nextGeneration();
        Assert.assertTrue(nextGenGame.getLiveCells().isEmpty());
    }


    @Test
    public void testPentadecathlon15Period() {
        for(int x=1; x<=3; x++) {
            for(int y=1; y<=8; y++) {
                initialLiveCells.add(new Point(x,y));
            }
        }
        initialLiveCells.remove(new Point(2,2));
        initialLiveCells.remove(new Point(2,7));

        GameOfLife game = new GameOfLife(initialLiveCells);
        GameOfLife nextGame;

        for(int i=0; i<15; i++) {
            game = game.nextGeneration();
        }

        Assert.assertEquals(game.getLiveCells(), initialLiveCells);
    }

    @Test
    public void allThreeNeighbouringPointShouldAlive() {
        initialLiveCells.add(new Point(1,4));
        initialLiveCells.add(new Point(2,4));
        initialLiveCells.add(new Point(2,3));
        GameOfLife game = new GameOfLife(initialLiveCells);
        GameOfLife nextGenGame = game.nextGeneration();
        Assert.assertTrue(nextGenGame.getLiveCells().containsAll(initialLiveCells));

    }

    @Test
    public void blinkerPatternTest() {

        Set<Point> blinkState1 = new HashSet<>();
        blinkState1.add(new Point(1, 2));
        blinkState1.add(new Point(2, 2));
        blinkState1.add(new Point(3, 2));

        Set<Point> blinkState2 = new HashSet<>();
        blinkState2.add(new Point(2,1));
        blinkState2.add(new Point(2,2));
        blinkState2.add(new Point(2,3));

        GameOfLife game = new GameOfLife(blinkState1);
        GameOfLife nextGame = game.nextGeneration();
        GameOfLife nextTonextGame = nextGame.nextGeneration();

        Assert.assertEquals(nextGame.getLiveCells(), blinkState2);
        Assert.assertEquals(nextTonextGame.getLiveCells(), blinkState1);

    }

    @Test
    public void testStillPatternBlock() {
        initialLiveCells.add(new Point(2, 2));
        initialLiveCells.add(new Point(2, 3));
        initialLiveCells.add(new Point(3, 2));
        initialLiveCells.add(new Point(3, 3));
        GameOfLife game = new GameOfLife(initialLiveCells);
        Assert.assertEquals(game.nextGeneration().getLiveCells(),initialLiveCells);
    }

    @Test
    public void testStillPatternBeehive() {
        int x=0;
        int y=0;
        initialLiveCells.add(new Point(x,y));
        initialLiveCells.add(new Point(x+1, y));
        initialLiveCells.add(new Point(x, y-2));
        initialLiveCells.add(new Point(x+1, y-2));
        initialLiveCells.add(new Point(x-1, y-1));
        initialLiveCells.add(new Point(x+2,y-1));
        GameOfLife game = new GameOfLife(initialLiveCells);
        Assert.assertEquals(game.nextGeneration().getLiveCells(), initialLiveCells);
    }

    @Test
    public void testStillPatternTub() {
        int x=0;
        int y=0;
        initialLiveCells.add(new Point(x,y+1));
        initialLiveCells.add(new Point(x, y-1));
        initialLiveCells.add(new Point(x-1,y));
        initialLiveCells.add(new Point(x+1,y));
        GameOfLife game = new GameOfLife(initialLiveCells);
        Assert.assertEquals(game.nextGeneration().getLiveCells(), initialLiveCells);
    }

    @Test
    public void testStillPatternBoat() {
        int x=0;
        int y=0;
        initialLiveCells.add(new Point(x,y+1));
        initialLiveCells.add(new Point(x, y-1));
        initialLiveCells.add(new Point(x-1,y));
        initialLiveCells.add(new Point(x+1,y));
        initialLiveCells.add(new Point(x-1,y+1));
        GameOfLife game = new GameOfLife(initialLiveCells);
        Assert.assertEquals(game.nextGeneration().getLiveCells(), initialLiveCells);
    }

}
