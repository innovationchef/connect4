package com.innovationchef.entity;

import com.innovationchef.constant.Player;
import com.innovationchef.support.BooleanConverter;
import com.innovationchef.support.PlayerConverter;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONNECT4_GAME_DATA")
public class Connect4GameData implements Serializable {

    @Id
    @Column(name = "TID")
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

}
