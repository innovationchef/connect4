package com.innovationchef.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GameStatusRes extends ApiResponse {

    private List<Move> moves = new ArrayList<>();
    private String sessionId;

    public GameStatusRes(boolean status, String message) {
        super(status, message);
    }
}
