package com.innovationchef.model;

import com.innovationchef.constant.Player;
import com.innovationchef.exception.InvalidMoveException;

public class Grid {
    private int[][] representation;
    private int row, col;

    public Grid(int row, int col) {
        this.row = row;
        this.col = col;
        this.representation = new int[row][col];
    }

    public Grid(int row, int col, int[][] rep) {
        this.row = row;
        this.col = col;
        this.representation = rep;
    }

    public static Grid loadGame(int[][] preloadedRep) {
        int row = preloadedRep.length;
        int col = preloadedRep[0].length;
        return new Grid(row, col, preloadedRep);
    }

    public int dropCoin(Player player, int j) {
        if (j < 0 || j > this.col) throw new InvalidMoveException();
        for (int i = 0; i < this.row; i++) {
            if (this.representation[i][j] == 0) {
                this.representation[i][j] = player.val();
                return i;
            }
        }
        throw new InvalidMoveException();
    }

    public int[][] getRep() {
        return this.representation;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Player getPlayer(Coordinate coordinate) {
        return Player.from(this.representation[coordinate.i()][coordinate.j()]);
    }
}
