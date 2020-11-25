package com.innovationchef.strategy;

import com.innovationchef.constant.Direction;
import com.innovationchef.gameplay.TrackVisit;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.Grid;
import lombok.ToString;

import java.util.Comparator;
import java.util.stream.Stream;

/**
 * If the player forms a square formation on the grid, he wins
 * Eg: If two players are X and M, in the following grid, X wins
 *     O O O O O O O
 *     O O O O O O O
 *     O O O O X O O
 *     O O O O X O O
 *     O M O O X O O
 *     M M M O X O O
 *
 *     O O O O O O O
 *     O O O O O O O
 *     O X O O O O O
 *     O O X O O O O
 *     O M O X O O O
 *     M M M O X O O
 *
 */
public class ArrayMatchStrategy implements GameStrategy {

    public final Grid grid;
    public final TrackVisit trackVisit;

    public ArrayMatchStrategy(final Grid grid, final TrackVisit trackVisit) {
        this.grid = grid;
        this.trackVisit = trackVisit;
    }

    @Override
    public void verifyBoard(final Coordinate c) {
        MatchPerDirection matchPerDirection = new MatchPerDirection();
        visit(this.grid, this.trackVisit, c, Direction.CENTER, matchPerDirection);
        int max = Stream.of(matchPerDirection.L + matchPerDirection.R,
                matchPerDirection.U + matchPerDirection.D,
                matchPerDirection.LU + matchPerDirection.RD,
                matchPerDirection.RU + matchPerDirection.LD).max(Comparator.naturalOrder()).get();
        if (max + 1 >= this.trackVisit.getConnectMax()) {
            this.trackVisit.championFound(true);
        }
    }

    public void visit(Grid grid, TrackVisit trackVisit, Coordinate c, Direction dir, MatchPerDirection matchPerDirection) {
        if (dir != Direction.CENTER) {
            matchPerDirection.add(dir);
        }
        if (trackVisit.fwd()) {
            trackVisit.bwd();
            return;
        }
        trackVisit.visit(c);
        if (c.isUpLeftPossible() && trackVisit.shouldVisit(c.moveUp(), grid) && dir.isCU()) {
            visit(grid, trackVisit, c.moveUp(), Direction.U, matchPerDirection);
        }

        if (c.isDownPossible() && trackVisit.shouldVisit(c.moveDown(), grid) && dir.isCD()) {
            visit(grid, trackVisit, c.moveDown(), Direction.D, matchPerDirection);
        }

        if (c.isLeftPossible() && trackVisit.shouldVisit(c.moveLeft(), grid) && dir.isCL()) {
            visit(grid, trackVisit, c.moveLeft(), Direction.L, matchPerDirection);
        }

        if (c.isRightPossible() && trackVisit.shouldVisit(c.moveRight(), grid) && dir.isCR()) {
            visit(grid, trackVisit, c.moveRight(), Direction.R, matchPerDirection);
        }

        if (c.isUpLeftPossible() && trackVisit.shouldVisit(c.moveUpLeft(), grid) && dir.isCLU()) {
            visit(grid, trackVisit, c.moveUpLeft(), Direction.LU, matchPerDirection);
        }

        if (c.isDownRightPossible() && trackVisit.shouldVisit(c.moveDownRight(), grid) && dir.isCRD()) {
            visit(grid, trackVisit, c.moveDownRight(), Direction.RD, matchPerDirection);
        }

        if (c.isDownLeftPossible() && trackVisit.shouldVisit(c.moveDownLeft(), grid) && dir.isCLD()) {
            visit(grid, trackVisit, c.moveDownLeft(), Direction.LD, matchPerDirection);
        }

        if (c.isUpRightPossible() && trackVisit.shouldVisit(c.moveUpRight(), grid) && dir.isCRU()) {
            visit(grid, trackVisit, c.moveUpRight(), Direction.RU, matchPerDirection);
        }
        trackVisit.bwd();
    }

    @ToString
    private class MatchPerDirection {
        public int L;
        public int R;
        public int U;
        public int D;
        public int LU;
        public int LD;
        public int RU;
        public int RD;

        public void add(Direction d) {
            switch (d) {
                case L:
                    this.L++;
                    return;
                case R:
                    this.R++;
                    return;
                case U:
                    this.U++;
                    return;
                case D:
                    this.D++;
                    return;
                case LU:
                    this.LU++;
                    return;
                case LD:
                    this.LD++;
                    return;
                case RU:
                    this.RU++;
                    return;
                case RD:
                    this.RD++;
            }
        }
    }
}
