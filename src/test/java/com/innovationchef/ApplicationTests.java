package com.innovationchef;

import com.innovationchef.constant.Player;
import com.innovationchef.exception.MaxMovesReachedException;
import com.innovationchef.gameplay.TrackVisit;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.GameMoveReq;
import com.innovationchef.model.Grid;
import com.innovationchef.model.TestMoveSequence;
import com.innovationchef.service.GameExecution;
import com.innovationchef.strategy.ArrayMatchStrategy;
import com.innovationchef.strategy.GameStrategy;
import com.innovationchef.utils.LogUtil;
import com.innovationchef.utiltiy.TestFileReader;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
@SpringBootTest
public class ApplicationTests {

    private static List<TestMoveSequence> testMoveSequences = new LinkedList<>();

    @Autowired
    private GameExecution execution;

    @BeforeAll
    public static void loadTestCases() {
        testMoveSequences.addAll(TestFileReader.readMoveSeqTestFile("test-cases.txt"));
    }

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
        GameStrategy strategy = new ArrayMatchStrategy(grid, trackVisit);
        strategy.verifyBoard(new Coordinate(3, 1, row, col));
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
        GameStrategy strategy = new ArrayMatchStrategy(grid, trackVisit);
        strategy.verifyBoard(new Coordinate(3, 1, row, col));
        Assert.assertTrue(trackVisit.championFound());
    }

    @Test
    public void execute1() {
        this.testMove(testMoveSequences.get(0));
    }

    @Test
    public void execute2() {
        this.testMove(testMoveSequences.get(1));
    }


    @Test
    public void execute3() {
        this.testMove(testMoveSequences.get(2));
    }


    @Test
    public void execute4() {
        this.testMove(testMoveSequences.get(3));
    }

    @Test
    public void execute5() {
        this.testMove(testMoveSequences.get(4));
    }

    @Test
    public void execute6() {
        MaxMovesReachedException ex = assertThrows(MaxMovesReachedException.class, () -> testMove(testMoveSequences.get(5)));
    }

    @Test
    public void execute7() {
        this.testMove(testMoveSequences.get(6));
    }

    @Test
    public void execute8() {
        this.testMove(testMoveSequences.get(7));
    }

    private void testMove(TestMoveSequence tCase) {
        Iterator<Integer> player1 = tCase.getP1Moves().iterator();
        Iterator<Integer> player2 = tCase.getP2Moves().iterator();
        String sessionId = execution.createSession();
        int i = 1;
        while (player1.hasNext() && player2.hasNext()) {
            if (i % 2 == 0) {
                GameMoveReq req = new GameMoveReq();
                req.setColumn(player2.next());
                req.setPlayer(Player.otherPlayer(tCase.getBeginner()));
                req.setSessionId(sessionId);
                log.info(execution.makeMove(req));
            } else {
                GameMoveReq req = new GameMoveReq();
                req.setColumn(player1.next());
                req.setPlayer(tCase.getBeginner());
                req.setSessionId(sessionId);
                log.info(execution.makeMove(req));
            }
            i++;
        }
        if (player1.hasNext()) {
            GameMoveReq req = new GameMoveReq();
            req.setColumn(player1.next());
            req.setPlayer(tCase.getBeginner());
            req.setSessionId(sessionId);
            log.info(execution.makeMove(req));
        }
        if (player2.hasNext()) {
            GameMoveReq req = new GameMoveReq();
            req.setColumn(player2.next());
            req.setPlayer(Player.otherPlayer(tCase.getBeginner()));
            req.setSessionId(sessionId);
            log.info(execution.makeMove(req));
        }
    }
}
