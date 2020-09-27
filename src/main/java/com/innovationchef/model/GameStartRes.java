package com.innovationchef.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameStartRes extends ApiResponse {

    @JsonProperty("token")
    private String token;

    public GameStartRes(boolean success, String message) {
        super(success, message);
    }
}
