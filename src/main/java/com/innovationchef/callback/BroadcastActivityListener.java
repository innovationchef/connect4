package com.innovationchef.callback;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@ConditionalOnProperty(name = "connect4.callback.broadcast.enabled", havingValue = "true")
public class BroadcastActivityListener implements Listener {

    @Override
    public void act(Activity a) {
        // Call some rest service to register activity
        System.out.println(a.toString());
    }
}
