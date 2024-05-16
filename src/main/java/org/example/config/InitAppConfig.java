package org.example.config;

import org.example.env.EnvWorker;
import org.example.env.InitEnvWorker;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-init.properties")
@Profile("init")
public class InitAppConfig {
    @Bean
    public EnvWorker envWorker() {
        return new InitEnvWorker();
    }

}
