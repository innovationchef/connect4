package com.innovationchef.service;

import com.innovationchef.constant.Direction;
import com.innovationchef.constant.GameMessage;
import com.innovationchef.constant.Player;
import com.innovationchef.entity.Connect4GameData;
import com.innovationchef.entity.Connect4MatchData;
import com.innovationchef.exception.IllegalMoveException;
import com.innovationchef.exception.InvalidSessionIdException;
import com.innovationchef.exception.MaxMovesReachedException;
import com.innovationchef.model.*;
import com.innovationchef.repository.DatabaseAccessObject;
import com.innovationchef.utils.LogUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Log4j2
public class BizExecution {

    @Value("${connect4.grid.row}")
    private int row;

    @Value("${connect4.grid.col}")
    private int col;

    @Value("${connect4.grid.num}")
    private int num;

    private DatabaseAccessObject dao;

    public BizExecution(DatabaseAccessObject dao) {
        this.dao = dao;
    }

    @Transactional
    public String createSession() {
        int[][] gridRep = new int[row][col];
        String sessionId = UUID.randomUUID().toString();
        Connect4GameData data = Connect4GameData.builder()
                .sessionId(sessionId)
                .data(gridRep)
                .nextPlayer(Player.YELLOW)
                .col(col)
                .row(row)
                .num(num)
                .currMove(0)
                .isSessionOver(false)
                .build();
        this.dao.persist(data);
        return sessionId;
    }

    @Transactional
    public String makeMove(GameMoveReq moveReq) {
        Optional<Connect4GameData> session = this.dao.fetch(moveReq.getSessionId(), new Connect4GameData());
        if (!session.isPresent() || session.get().isSessionOver())
            throw new InvalidSessionIdException();
        Connect4GameData game = session.get();
        if (game.getNextPlayer() != moveReq.getPlayer())
            throw new IllegalMoveException();
        Grid grid = Grid.loadGame(game.getData());
        int insertRow = grid.fill(moveReq.getPlayer(), moveReq.getColumn());
        if (insertRow < 0) return GameMessage.INVALID_MOVE;
        TrackVisit trackVisit = new TrackVisit(game.getRow(), game.getCol(), game.getNum(), moveReq.getPlayer());
        visit(grid, trackVisit, new Coordinate(insertRow, moveReq.getColumn(), game.getRow(), game.getCol()), Direction.CENTER);
        if (trackVisit.championFound()) {
            game.setSessionOver(true);
            if (trackVisit.getPlayer() == Player.RED) return GameMessage.RED_WINS;
            if (trackVisit.getPlayer() == Player.YELLOW) return GameMessage.YELLOW_WINS;
        }
        if (log.isDebugEnabled()) {
            LogUtil.prettyPrint(grid.getRep());
            log.info("-------------");
        }
        game.setNextPlayer(Player.otherPlayer(moveReq.getPlayer()));
        game.setCurrMove(game.getCurrMove() + 1);
        if (game.getCurrMove() >= game.getMaxMoves()) {
            game.setSessionOver(true);
            this.dao.merge(game);
            throw new MaxMovesReachedException();
        }
        Connect4MatchData matchData = new Connect4MatchData();
        matchData.setCol(moveReq.getColumn());
        matchData.setRow(insertRow);
        matchData.setMove(game.getCurrMove());
        matchData.setPlayer(moveReq.getPlayer());
        matchData.setGameData(game);
        this.dao.persist(matchData);
        this.dao.merge(game);
        return GameMessage.VALID_MOVE;
    }

    @Transactional
    public List<Move> fetchGameStatus(GameStatusReq statusReq) {
        Optional<Connect4GameData> session = this.dao.fetch(statusReq.getSessionId(), new Connect4GameData());
        if (!session.isPresent())
            throw new InvalidSessionIdException();
        Connect4GameData game = session.get();
        List<Connect4MatchData> matchData = game.getMatchData();
        matchData.sort((d1, d2) -> d1.getMove() - d2.getMove());
        List<Move> moves = new ArrayList<>();
        for (Connect4MatchData mData : matchData) {
            Move move = new Move();
            move.setCoordinate(new Coordinate(mData.getRow(), mData.getCol(), game.getRow(), game.getCol()));
            move.setMove(mData.getMove());
            move.setPlayer(mData.getPlayer());
            moves.add(move);
        }
        return moves;
    }

    public void visit(Grid grid, TrackVisit trackVisit, Coordinate c, Direction dir) {
        if (trackVisit.fwd() || trackVisit.championFound()) return;
        trackVisit.visit(c);
        if (c.isUpLeftPossible() && trackVisit.shouldVisit(c.moveUp(), grid) && dir.isU()) {
            visit(grid, trackVisit, c.moveUp(), Direction.U);
        }

        if (c.isDownPossible() && trackVisit.shouldVisit(c.moveDown(), grid) && dir.isD()) {
            visit(grid, trackVisit, c.moveDown(), Direction.D);
        }

        if (c.isLeftPossible() && trackVisit.shouldVisit(c.moveLeft(), grid) && dir.isL()) {
            visit(grid, trackVisit, c.moveLeft(), Direction.L);
        }

        if (c.isRightPossible() && trackVisit.shouldVisit(c.moveRight(), grid) && dir.isR()) {
            visit(grid, trackVisit, c.moveRight(), Direction.R);
        }

        if (c.isUpLeftPossible() && trackVisit.shouldVisit(c.moveUpLeft(), grid) && dir.isLU()) {
            visit(grid, trackVisit, c.moveUpLeft(), Direction.LU);
        }

        if (c.isDownRightPossible() && trackVisit.shouldVisit(c.moveDownRight(), grid) && dir.isRD()) {
            visit(grid, trackVisit, c.moveDownRight(), Direction.RD);
        }

        if (c.isDownLeftPossible() && trackVisit.shouldVisit(c.moveDownLeft(), grid) && dir.isLD()) {
            visit(grid, trackVisit, c.moveDownLeft(), Direction.LD);
        }

        if (c.isUpRightPossible() && trackVisit.shouldVisit(c.moveUpRight(), grid) && dir.isRU()) {
            visit(grid, trackVisit, c.moveUpRight(), Direction.RU);
        }
        trackVisit.bwd();
    }
}
