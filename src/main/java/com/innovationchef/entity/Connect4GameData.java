package com.innovationchef.entity;

import com.innovationchef.constant.DBConstant;
import com.innovationchef.constant.Player;
import com.innovationchef.support.BooleanConverter;
import com.innovationchef.support.PlayerConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = DBConstant.CONNECT4_GAME_DATA_TBL)
public class Connect4GameData implements Serializable {

    @Id
    @Column(name = DBConstant.CONNECT4_DB_KEY, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int tid;

    @NaturalId
    @Column(name = "SESSION_ID", updatable = false)
    private String sessionId;

    @Column(name = "NEXT_PLAYER")
    @Convert(converter = PlayerConverter.class)
    private Player nextPlayer;

    @Formula("ROWN * COLN")
    private int maxMoves;

    @Column(name = "ROWN", updatable = false)
    private int row;

    @Column(name = "COLN", updatable = false)
    private int col;

    @Column(name = "NUM", updatable = false)
    private int num;

    @Column(name = "CURR_MOVE")
    private int currMove;

    @Column(name = "IS_SESSION_OVER")
    @Convert(converter = BooleanConverter.class)
    private boolean isSessionOver;

    @Lob
    @Column(name = "DATA")
    private int[][] data;

    @OneToMany(mappedBy = "gameData", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<Connect4MatchData> matchData = new ArrayList<>();

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNum() {
        return num;
    }

    public int[][] getData() {
        return data;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean exceedsMaxMoves() {
        return this.currMove >= this.maxMoves;
    }

    public Connect4GameData togglePlayer() {
        this.nextPlayer = Player.otherPlayer(nextPlayer);
        return this;
    }

    public Connect4GameData incrementMove() {
        this.currMove++;
        return this;
    }

    public String getSessionId() {
        return sessionId;
    }

    public List<Connect4MatchData> getMatchData() {
        return matchData;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public int getCurrMove() {
        return currMove;
    }

    public boolean isSessionOver() {
        return isSessionOver;
    }

    public void setSessionOver(boolean sessionOver) {
        isSessionOver = sessionOver;
    }

    /*
     * Example of Builder Design Pattern from Gang of Four
     */
    public static class Builder {
        private String sessionId;
        private Player nextPlayer;
        private int maxMoves;
        private int row;
        private int col;
        private int num;
        private int currMove;
        private boolean isSessionOver;
        private int[][] data;

        public Builder initializeBoard(int row, int col, int num) {
            this.row = row;
            this.col = col;
            this.num = num;
            this.data = new int[row][col];
            this.currMove = 0;
            this.isSessionOver = false;
            this.maxMoves = row * col;
            this.sessionId = UUID.randomUUID().toString();
            return this;
        }

        public Builder withPlayer(Player initPlayer) {
            this.nextPlayer = initPlayer;
            return this;
        }

        public Connect4GameData build() {
            Connect4GameData data = new Connect4GameData();
            data.num = this.num;
            data.col = this.col;
            data.row = this.row;
            data.currMove = this.currMove;
            data.isSessionOver = this.isSessionOver;
            data.maxMoves = this.maxMoves;
            data.data = this.data;
            data.nextPlayer = this.nextPlayer;
            data.sessionId = this.sessionId;
            return data;
        }
    }
}
