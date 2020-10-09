package com.innovationchef.strategy;

import com.innovationchef.model.Coordinate;

/**
 * Example for Strategy Design Pattern from the Gang of Four book
 */
public interface GameStrategy {

    void verifyBoard(Coordinate c);
}
