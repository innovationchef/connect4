package com.innovationchef.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.innovationchef.exception.PayloadFormatException;

public enum Player {
    RED(1), YELLOW(-1), EMPTY(0);

    private int val;

    Player(int val) {
        this.val = val;
    }

    public static Player from(int i) {
        switch (i) {
            case 1:
                return RED;
            case -1:
                return YELLOW;
            case 0:
                return EMPTY;
            default:
                throw new IllegalArgumentException();
        }
    }

    @JsonCreator
    public static Player fromString(String value) {
        switch (value.toUpperCase()) {
            case "RED":
                return RED;
            case "YELLOW":
                return YELLOW;
            default:
                throw new PayloadFormatException();
        }
    }

    @JsonValue
    public String getVal() {
        switch (val) {
            case 1:
                return "RED";
            case -1:
                return "YELLOW";
            default:
                throw new IllegalArgumentException();
        }
    }

    public int val() {
        return val;
    }

    public static Player otherPlayer(Player currPlayer) {
        if (currPlayer == YELLOW) return RED;
        else return YELLOW;
    }
}
