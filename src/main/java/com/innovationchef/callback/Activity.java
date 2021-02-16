package com.innovationchef.callback;

import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.Instant;

public class Activity {

    private Instant startTime;
    private Instant endTime;
    private Duration duration;
    private String requestPath;
    private HttpStatus responseStatus;

    private Activity() {
        this.startTime = Instant.now();
    }

    public static Activity register() {
        return new Activity();
    }

    public Activity finished() {
        this.endTime = Instant.now();
        this.duration = Duration.between(this.startTime, this.endTime);
        return this;
    }

    public Activity requestedEndpoint(String path) {
        this.requestPath = path;
        return this;
    }

    public Activity responseStatus(HttpStatus status) {
        this.responseStatus = status;
        return this;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", requestPath='" + requestPath + '\'' +
                ", responseStatus=" + responseStatus +
                '}';
    }
}
