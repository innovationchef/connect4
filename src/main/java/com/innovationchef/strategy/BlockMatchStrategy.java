package com.innovationchef.strategy;

import com.innovationchef.gameplay.TrackVisit;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.Grid;

/**
 * If the player forms a square formation on the grid, he wins
 * Eg: If two players are X and M, in the following grid, X wins
 *     O O O O O O O
 *     O O O O O O O
 *     O O O O O O O
 *     O O O O O O O
 *     O M O X X O O
 *     M M M X X O O
 *
 */
public class BlockMatchStrategy implements GameStrategy {

    public Grid grid;
    public TrackVisit trackVisit;

    public BlockMatchStrategy(Grid grid, TrackVisit trackVisit) {
        this.grid = grid;
        this.trackVisit = trackVisit;
    }

    // To be implemented
    @Override
    public void verifyBoard(Coordinate c) {

    }
}
