package com.innovationchef.entity;

import com.innovationchef.constant.Player;
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
@Table(name = "CONNECT4_MATCH_DATA")
public class Connect4MatchData implements Serializable {

    @Id
    @Column(name = "TID")
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
    private int move;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "SESSION_ID")
    private Connect4GameData gameData;
}
