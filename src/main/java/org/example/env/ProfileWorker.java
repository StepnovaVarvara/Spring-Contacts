package org.example.env;

import org.springframework.stereotype.Component;

@Component
public class ProfileWorker {
    private final EnvWorker envWorker;
    public ProfileWorker(EnvWorker envWorker) {
        this.envWorker = envWorker;
    }
    public void setup() {
        envWorker.initContactList();
    }
}
