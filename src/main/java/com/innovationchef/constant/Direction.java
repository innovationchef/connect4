package com.innovationchef.constant;

public enum Direction {
    L, R, U, D, LU, LD, RU, RD, CENTER;

    public boolean isU() {
        return isCenter() || this == U;
    }

    public boolean isR() {
        return isCenter() || this == R;
    }

    public boolean isL() {
        return isCenter() || this == L;
    }

    public boolean isD() {
        return isCenter() || this == D;
    }

    public boolean isRU() {
        return isCenter() || this == RU;
    }

    public boolean isRD() {
        return isCenter() || this == RD;
    }

    public boolean isLU() {
        return isCenter() || this == LU;
    }

    public boolean isLD() {
        return isCenter() || this == LD;
    }

    public boolean isCenter() {
        return this == CENTER;
    }
}
