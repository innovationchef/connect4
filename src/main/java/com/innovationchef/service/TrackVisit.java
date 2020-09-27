package com.innovationchef.service;

import com.innovationchef.constant.Player;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.Grid;

public class TrackVisit {

    private int stepsTaken;
    private boolean[][] visited;
    private Player player;
    private int connectMax;
    private boolean playerWon;

    public TrackVisit(int row, int col, int connectMax, Player p) {
        this.player = p;
        this.stepsTaken = 0;
        this.playerWon = false;
        this.connectMax = connectMax;
        this.visited = new boolean[row][col];
    }

    public Boolean shouldVisit(Coordinate coordinate, Grid grid) {
        return !this.visited[coordinate.i()][coordinate.j()] && grid.getPlayer(coordinate) == this.player;
    }

    public void visit(Coordinate c) {
        visited[c.i()][c.j()] = true;
    }

    public boolean fwd() {
        this.stepsTaken++;
        if(this.stepsTaken == connectMax) {
            playerWon = true;
            return true;
        }
        return false;
    }

    public void bwd() {
        this.stepsTaken--;
    }

    public int step() {
        return this.stepsTaken;
    }

    public boolean championFound() {
        return playerWon;
    }

    public Player getPlayer() {
        return player;
    }
}
