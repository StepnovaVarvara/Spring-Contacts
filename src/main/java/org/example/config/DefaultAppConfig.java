package org.example.config;

import org.example.env.DefaultEnvWorker;
import org.example.env.EnvWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {
    @Bean
    public EnvWorker envWorker() {
        return new DefaultEnvWorker();
    }
}
