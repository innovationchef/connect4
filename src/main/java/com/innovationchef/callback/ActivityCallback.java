package com.innovationchef.callback;

import java.util.LinkedList;
import java.util.List;

public class ActivityCallback implements Callback {

    private List<Listener> listeners = new LinkedList<>();

    @Override
    public void attach(Listener l) {
        this.listeners.add(l);
    }

    @Override
    public void publish(Activity a) {
        for (Listener listener : this.listeners) {
            listener.act(a);
        }
    }
}
