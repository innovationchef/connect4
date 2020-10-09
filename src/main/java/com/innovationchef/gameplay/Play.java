package com.innovationchef.gameplay;

import com.innovationchef.constant.GameMessage;
import com.innovationchef.constant.Player;
import com.innovationchef.entity.Connect4GameData;
import com.innovationchef.entity.Connect4MatchData;
import com.innovationchef.exception.MaxMovesReachedException;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.Grid;
import com.innovationchef.repository.DatabaseAccessObject;
import com.innovationchef.strategy.ArrayMatchStrategy;
import com.innovationchef.strategy.BlockMatchStrategy;
import com.innovationchef.strategy.DiamondMatchStrategy;
import com.innovationchef.strategy.GameStrategy;
import com.innovationchef.utils.LogUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class Play {

    private DatabaseAccessObject dao;

    public Play(DatabaseAccessObject dao) {
        this.dao = dao;
    }

    public String play(Connect4GameData game, Player currPlayer, int dropCol) {
        Grid grid = Grid.loadGame(game.getData());
        int dropRow = grid.dropCoin(currPlayer, dropCol);

        // See the impact of this coin drop on the board
        TrackVisit trackVisit = new TrackVisit(game.getRow(), game.getCol(), game.getNum(), currPlayer);

        // Choose a strategy for the game. On this part of the code changes.
        // One can define as many strategies as one wants.
        GameStrategy strategy = new ArrayMatchStrategy(grid, trackVisit);
        // GameStrategy strategy = new BlockMatchStrategy(grid, trackVisit);
        // GameStrategy strategy = new DiamondMatchStrategy(grid, trackVisit);
        strategy.verifyBoard(new Coordinate(dropRow, dropCol, game.getRow(), game.getCol()));

        // Check the the move made is valid
        if (isChampionFound(trackVisit, game)) {
            return trackVisit.getPlayer() == Player.RED
                    ? GameMessage.RED_WINS
                    : GameMessage.YELLOW_WINS;
        }

        if (log.isDebugEnabled()) {
            LogUtil.prettyPrint(game.getData());
        }
        // if move is valid, save it in DB
        Connect4MatchData matchData = Connect4MatchData.builder()
                .coinDroppedAt(dropRow, dropCol)
                .byPlayer(currPlayer)
                .asMove(game.getCurrMove())
                .forGame(game)
                .build();
        this.dao.persist(matchData);
        this.dao.merge(game);
        return GameMessage.VALID_MOVE;
    }

    private boolean isChampionFound(TrackVisit trackVisit, Connect4GameData game) {
        game.togglePlayer().incrementMove();
        if (trackVisit.championFound() || game.exceedsMaxMoves()) {
            game.setSessionOver(true);
            this.dao.merge(game);
            if (game.exceedsMaxMoves())
                throw new MaxMovesReachedException();
        }
        return game.isSessionOver();
    }
}
