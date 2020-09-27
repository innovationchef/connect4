package com.innovationchef.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ApiResponse {

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;
}
