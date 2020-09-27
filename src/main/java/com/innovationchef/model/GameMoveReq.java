package com.innovationchef.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.innovationchef.constant.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
public class GameMoveReq {

    @NotNull
    @NotEmpty
    @JsonProperty("sessionId")
    private String sessionId;

    @NotNull
    private Player player;

    @NotNull
    @PositiveOrZero
    @JsonProperty("position")
    private int column;
}
