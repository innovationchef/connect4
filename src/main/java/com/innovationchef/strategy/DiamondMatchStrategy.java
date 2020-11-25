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
 *     O O O O X O O
 *     O O O X M X O
 *     M O O M X M O
 *
 */
public class DiamondMatchStrategy implements GameStrategy {

    public Grid grid;
    public TrackVisit trackVisit;

    public DiamondMatchStrategy(Grid grid, TrackVisit trackVisit) {
        this.grid = grid;
        this.trackVisit = trackVisit;
    }

    // TODO: To be implemented
    @Override
    public void verifyBoard(final Coordinate c) {

    }
}
