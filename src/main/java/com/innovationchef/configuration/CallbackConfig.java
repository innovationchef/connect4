package com.innovationchef.configuration;

import com.innovationchef.callback.ActivityCallback;
import com.innovationchef.callback.Callback;
import com.innovationchef.callback.Listener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CallbackConfig {

    @Bean
    public Callback publisher(List<Listener> listeners) {
        Callback callback = new ActivityCallback();
        for (Listener listener : listeners) {
            callback.attach(listener);
        }
        return callback;
    }
}


