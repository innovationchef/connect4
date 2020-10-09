package com.innovationchef.entity;

import com.innovationchef.constant.DBConstant;
import com.innovationchef.constant.Player;
import com.innovationchef.support.BooleanConverter;
import com.innovationchef.support.PlayerConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = DBConstant.CONNECT4_GAME_DATA_TBL)
public class Connect4GameData implements Serializable {

    @Id
    @Column(name = DBConstant.CONNECT4_DB_KEY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int tid;

    @NaturalId
    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "NEXT_PLAYER")
    @Convert(converter = PlayerConverter.class)
    private Player nextPlayer;

    @Formula("ROWN * COLN")
    private int maxMoves;

    @Column(name = "ROWN")
    private int row;

    @Column(name = "COLN")
    private int col;

    @Column(name = "NUM")
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

    public static Builder builder() {
        return new Builder();
    }

    public boolean exceedsMaxMoves() {
        return this.getCurrMove() >= this.getMaxMoves();
    }

    public Connect4GameData togglePlayer() {
        this.nextPlayer = Player.otherPlayer(nextPlayer);
        return this;
    }

    public Connect4GameData incrementMove() {
        this.currMove++;
        return this;
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
            data.setNum(this.num);
            data.setCol(this.col);
            data.setRow(this.row);
            data.setCurrMove(this.currMove);
            data.setSessionOver(this.isSessionOver);
            data.setMaxMoves(this.maxMoves);
            data.setData(this.data);
            data.setNextPlayer(this.nextPlayer);
            data.setSessionId(this.sessionId);
            return data;
        }
    }
}
