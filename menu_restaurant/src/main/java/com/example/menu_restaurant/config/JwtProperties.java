package com.example.menu_restaurant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private Long refresh;

    public Long getRefresh() {
        return refresh;
    }

    public void setRefresh(Long refresh) {
        this.refresh = refresh;
    }
}
