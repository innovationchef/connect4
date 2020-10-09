package com.innovationchef.constant;

public enum Direction {
    L, R, U, D, LU, LD, RU, RD, CENTER;

    public boolean isCU() {
        return isC() || this == U;
    }

    public boolean isCR() {
        return isC() || this == R;
    }

    public boolean isCL() {
        return isC() || this == L;
    }

    public boolean isCD() {
        return isC() || this == D;
    }

    public boolean isCRU() {
        return isC() || this == RU;
    }

    public boolean isCRD() {
        return isC() || this == RD;
    }

    public boolean isCLU() {
        return isC() || this == LU;
    }

    public boolean isCLD() {
        return isC() || this == LD;
    }

    public boolean isC() {
        return this == CENTER;
    }
}
