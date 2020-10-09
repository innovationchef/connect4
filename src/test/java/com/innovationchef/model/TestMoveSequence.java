package com.innovationchef.model;

import com.innovationchef.constant.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;

@Getter
@Setter
@ToString
public class TestMoveSequence {
    public Player beginner;
    public Player winner;
    public LinkedList<Integer> p1Moves = new LinkedList<>();
    public LinkedList<Integer> p2Moves = new LinkedList<>();
}
