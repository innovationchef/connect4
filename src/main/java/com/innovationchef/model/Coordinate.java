package com.innovationchef.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinate {

    private int i, j;

    @JsonIgnore
    private int row, col;

    public Coordinate(int i, int j, int row, int col) {
        this.i = i;
        this.j = j;
        this.row = row;
        this.col = col;
    }

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @JsonProperty
    public int i() {
        return i;
    }

    @JsonProperty
    public int j() {
        return j;
    }

    @JsonIgnore
    public boolean isLeftPossible() {
        return this.j - 1 >= 0;
    }

    @JsonIgnore
    public boolean isRightPossible() {
        return this.j + 1 < this.col;
    }

    @JsonIgnore
    public boolean isUpPossible() {
        return this.i - 1 >= 0;
    }

    @JsonIgnore
    public boolean isDownPossible() {
        return this.i + 1 < this.row;
    }

    @JsonIgnore
    public boolean isUpLeftPossible() {
        return this.isUpPossible() && this.isLeftPossible();
    }

    @JsonIgnore
    public boolean isUpRightPossible() {
        return this.isUpPossible() && this.isRightPossible();
    }

    @JsonIgnore
    public boolean isDownLeftPossible() {
        return this.isDownPossible() && this.isLeftPossible();
    }

    @JsonIgnore
    public boolean isDownRightPossible() {
        return this.isDownPossible() && this.isRightPossible();
    }

    @JsonIgnore
    public Coordinate moveLeft() {
        return new Coordinate(this.i, this.j - 1, row, col);
    }

    @JsonIgnore
    public Coordinate moveRight() {
        return new Coordinate(this.i, this.j + 1, row, col);
    }

    @JsonIgnore
    public Coordinate moveUp() {
        return new Coordinate(this.i - 1, this.j, row, col);
    }

    @JsonIgnore
    public Coordinate moveDown() {
        return new Coordinate(this.i + 1, this.j, row, col);
    }

    @JsonIgnore
    public Coordinate moveUpLeft() {
        return new Coordinate(this.i - 1, this.j - 1, row, col);
    }

    @JsonIgnore
    public Coordinate moveUpRight() {
        return new Coordinate(this.i - 1, this.j + 1, row, col);
    }

    @JsonIgnore
    public Coordinate moveDownLeft() {
        return new Coordinate(this.i + 1, this.j - 1, row, col);
    }

    @JsonIgnore
    public Coordinate moveDownRight() {
        return new Coordinate(this.i + 1, this.j + 1, row, col);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
