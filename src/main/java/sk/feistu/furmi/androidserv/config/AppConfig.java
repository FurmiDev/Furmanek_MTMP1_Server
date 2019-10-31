package sk.feistu.furmi.androidserv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.feistu.furmi.androidserv.model.Projectile;

@Configuration
public class AppConfig {

    @Bean
    public Projectile projectile() {
        return new Projectile();
    }
}
