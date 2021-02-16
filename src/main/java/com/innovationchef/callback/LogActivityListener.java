package com.innovationchef.callback;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@ConditionalOnProperty(name = "connect4.callback.log.enabled", havingValue = "true")
public class LogActivityListener implements Listener {

    @Override
    public void act(Activity a) {
       log.info("Connect4 API callback: {}", a.toString());
    }
}
