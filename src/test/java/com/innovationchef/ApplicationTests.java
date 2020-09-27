package com.innovationchef;

import com.innovationchef.constant.Direction;
import com.innovationchef.constant.Player;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.Grid;
import com.innovationchef.service.BizExecution;
import com.innovationchef.service.TrackVisit;
import com.innovationchef.utils.LogUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private BizExecution execution;

    @Test
    public void happyFlowTest() {
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
        this.execution.visit(grid, trackVisit, new Coordinate(3, 1, row, col), Direction.CENTER);
        Assert.assertFalse(trackVisit.championFound());
    }

    @Test
    public void negFlowTest() {
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
        this.execution.visit(grid, trackVisit, new Coordinate(3, 1, row, col), Direction.CENTER);
        Assert.assertTrue(trackVisit.championFound());
    }
}
