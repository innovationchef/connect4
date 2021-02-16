package com.innovationchef;

import com.innovationchef.constant.Player;
import com.innovationchef.gameplay.TrackVisit;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.Grid;
import com.innovationchef.service.GameExecution;
import com.innovationchef.strategy.ArrayMatchStrategy;
import com.innovationchef.strategy.GameStrategy;
import com.innovationchef.utils.LogUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ApplicationUnitTests {

    @Autowired
    GameExecution execution;

    @Test
    public void arrayMatchStrategyTest1() {
        int row = 6, col = 7, num = 4;
        int[][] rep = new int[][]{
                {0, -1, 1, -1, -1, 0, -1},
                {0, 1, 1, 1, -1, 0, 0},
                {0, 1, 1, -1, -1, 0, 0},
                {0, 1, -1, 1, 0, 0, 0},
                {0, 0, 0, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        Grid grid = new Grid(row, col, rep);
        LogUtil.prettyPrint(grid.getRep());
        TrackVisit trackVisit = new TrackVisit(row, col, num, Player.RED);
        GameStrategy strategy = new ArrayMatchStrategy(grid, trackVisit);
        strategy.verifyBoard(new Coordinate(3, 1, row, col));
        assertFalse(trackVisit.championFound());
    }

    @Test
    public void arrayMatchStrategyTest2() {
        int row = 6, col = 7, num = 4;
        int[][] rep = new int[][]{
                {0, 1, 1, -1, 1, 0, -1},
                {0, 1, 1, 1, -1, 0, 0},
                {0, 1, 1, -1, -1, 0, 0},
                {0, 1, -1, 1, 0, 0, 0},
                {0, 0, 0, -1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0}
        };
        Grid grid = new Grid(row, col, rep);
        LogUtil.prettyPrint(grid.getRep());
        TrackVisit trackVisit = new TrackVisit(row, col, num, Player.RED);
        GameStrategy strategy = new ArrayMatchStrategy(grid, trackVisit);
        strategy.verifyBoard(new Coordinate(3, 1, row, col));
        assertTrue(trackVisit.championFound());
    }
}
