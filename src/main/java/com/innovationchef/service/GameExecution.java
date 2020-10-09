package com.innovationchef.service;

import com.innovationchef.constant.Player;
import com.innovationchef.entity.Connect4GameData;
import com.innovationchef.entity.Connect4MatchData;
import com.innovationchef.exception.IllegalMoveException;
import com.innovationchef.exception.InvalidSessionIdException;
import com.innovationchef.gameplay.Play;
import com.innovationchef.model.GameMoveReq;
import com.innovationchef.model.GameStatusReq;
import com.innovationchef.model.Move;
import com.innovationchef.repository.DatabaseAccessObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class GameExecution {

    @Value("${connect4.grid.row}")
    private int row;

    @Value("${connect4.grid.col}")
    private int col;

    @Value("${connect4.grid.num}")
    private int num;

    private Play gamePlay;
    private DatabaseAccessObject dao;

    public GameExecution(DatabaseAccessObject dao, Play gamePlay) {
        this.dao = dao;
        this.gamePlay = gamePlay;
    }

    @Transactional
    public String createSession() {
        Connect4GameData data = Connect4GameData.builder()
                .initializeBoard(row, col, num)
                .withPlayer(Player.YELLOW)
                .build();
        this.dao.persist(data);
        return data.getSessionId();
    }

    @Transactional
    public List<Move> fetchGameStatus(GameStatusReq statusReq) {
        Connect4GameData session = this.dao.fetch(statusReq.getSessionId(), new Connect4GameData())
                .orElseThrow(InvalidSessionIdException::new);
        return session.getMatchData()
                .stream()
                .sorted(Comparator.comparingInt(Connect4MatchData::getMoveNo))
                .map(Connect4MatchData::getMove)
                .collect(Collectors.toList());
    }

    @Transactional
    public String makeMove(GameMoveReq moveReq) {
        Connect4GameData game = this.dao.fetch(moveReq.getSessionId(), new Connect4GameData())
                .filter(g -> !g.isSessionOver())
                .orElseThrow(InvalidSessionIdException::new);
        if (game.getNextPlayer() != moveReq.getPlayer()) throw new IllegalMoveException();
        return this.gamePlay.play(game, moveReq.getPlayer(), moveReq.getColumn());
    }
}
