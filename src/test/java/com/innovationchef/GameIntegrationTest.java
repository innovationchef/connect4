package com.innovationchef;

import com.innovationchef.constant.Player;
import com.innovationchef.exception.MaxMovesReachedException;
import com.innovationchef.model.GameMoveReq;
import com.innovationchef.model.TestMoveSequence;
import com.innovationchef.service.GameExecution;
import com.innovationchef.utiltiy.TestFileReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GameIntegrationTest {

    @Autowired
    GameExecution execution;

    static List<TestMoveSequence> testMoveSequences = new LinkedList<>();

    @BeforeAll
    public static void loadTestCases() {
        testMoveSequences.addAll(TestFileReader.readMoveSeqTestFile("test-cases.txt"));
    }

    @Test
    public void gameTest1() {
        this.testMove(testMoveSequences.get(0));
    }

    @Test
    public void gameTest2() {
        this.testMove(testMoveSequences.get(1));
    }


    @Test
    public void gameTest3() {
        this.testMove(testMoveSequences.get(2));
    }


    @Test
    public void gameTest4() {
        this.testMove(testMoveSequences.get(3));
    }

    @Test
    public void gameTest5() {
        this.testMove(testMoveSequences.get(4));
    }

    @Test
    public void noPlayerWinsTest6() {
        MaxMovesReachedException ex = assertThrows(MaxMovesReachedException.class, () -> testMove(testMoveSequences.get(5)));
    }

    @Test
    public void gameTest7() {
        this.testMove(testMoveSequences.get(6));
    }

    @Test
    public void gameTest8() {
        this.testMove(testMoveSequences.get(7));
    }

    private void testMove(TestMoveSequence tCase) {
        Iterator<Integer> player1 = tCase.getP1Moves().iterator();
        Iterator<Integer> player2 = tCase.getP2Moves().iterator();
        String sessionId = execution.createSession();
        int totSteps = tCase.getP1Moves().size() + tCase.getP2Moves().size();
        int i = 1;
        while (player1.hasNext() && player2.hasNext()) {
            GameMoveReq req = new GameMoveReq();
            req.setSessionId(sessionId);
            if (i % 2 == 0) {
                req.setColumn(player2.next());
                req.setPlayer(Player.otherPlayer(tCase.getBeginner()));
            } else {
                req.setColumn(player1.next());
                req.setPlayer(tCase.getBeginner());
            }
            if (i != totSteps) assertEquals("VALID", execution.makeMove(req));
            else assertEquals("PLAYER " + tCase.getWinner().name() + " WINS", execution.makeMove(req));
            i++;
        }
        if (player1.hasNext()) {
            GameMoveReq req = new GameMoveReq();
            req.setColumn(player1.next());
            req.setPlayer(tCase.getBeginner());
            req.setSessionId(sessionId);
            if (i != totSteps) assertEquals("VALID", execution.makeMove(req));
            else assertEquals("PLAYER " + tCase.getWinner().name() + " WINS", execution.makeMove(req));
            // log.info(execution.makeMove(req));
        }
        if (player2.hasNext()) {
            GameMoveReq req = new GameMoveReq();
            req.setColumn(player2.next());
            req.setPlayer(Player.otherPlayer(tCase.getBeginner()));
            req.setSessionId(sessionId);
            if (i != totSteps) assertEquals("VALID", execution.makeMove(req));
            else assertEquals("PLAYER " + tCase.getWinner().name() + " WINS", execution.makeMove(req));
        }
    }
}
