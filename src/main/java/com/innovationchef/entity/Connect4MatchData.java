package com.innovationchef.entity;

import com.innovationchef.constant.DBConstant;
import com.innovationchef.constant.Player;
import com.innovationchef.model.Coordinate;
import com.innovationchef.model.Move;
import com.innovationchef.support.PlayerConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = DBConstant.CONNECT4_MATCH_DATA_TBL)
public class Connect4MatchData implements Serializable {

    @Id
    @Column(name = DBConstant.CONNECT4_DB_KEY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int tid;

    @Column(name = "PLAYER")
    @Convert(converter = PlayerConverter.class)
    private Player player;

    @Column(name = "ROWN")
    private int row;

    @Column(name = "COLN")
    private int col;

    @Column(name = "MOVE")
    private int moveNo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "SESSION_ID")
    private Connect4GameData gameData;

    public static Builder builder() {
        return new Builder();
    }

    public Move getMove() {
        Move move = new Move();
        move.setCoordinate(new Coordinate(this.getRow(), this.getCol(), gameData.getRow(), gameData.getCol()));
        move.setMove(this.moveNo);
        move.setPlayer(this.player);
        return move;
    }

    /*
     * Example of Builder Design Pattern from Gang of Four
     */
    public static class Builder {
        private int row;
        private int col;
        private int moveNo;
        private Player player;
        private Connect4GameData gameData;

        public Builder coinDroppedAt(int row, int col) {
            this.row = row;
            this.col = col;
            return this;
        }

        public Builder byPlayer(Player player) {
            this.player = player;
            return this;
        }

        public Builder asMove(int moveNo) {
            this.moveNo = moveNo;
            return this;
        }

        public Builder forGame(Connect4GameData gameData) {
            this.gameData = gameData;
            return this;
        }

        public Connect4MatchData build() {
            Connect4MatchData data = new Connect4MatchData();
            data.gameData = this.gameData;
            data.moveNo = this.moveNo;
            data.row = this.row;
            data.col = this.col;
            data.player = this.player;
            return data;
        }
    }
}
