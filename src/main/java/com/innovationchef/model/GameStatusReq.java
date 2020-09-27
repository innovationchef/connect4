package com.innovationchef.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class GameStatusReq {

    @NotNull
    @NotEmpty
    @JsonProperty("sessionId")
    private String sessionId;
}
