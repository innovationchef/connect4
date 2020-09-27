package com.innovationchef.model;

import com.innovationchef.constant.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Move {

    Integer move;
    Coordinate coordinate;
    Player player;
}
